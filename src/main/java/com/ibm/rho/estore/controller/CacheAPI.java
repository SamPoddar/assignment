package com.ibm.rho.estore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.rho.estore.listeners.Sender;
import com.ibm.rho.estore.model.ProductDO;
import com.ibm.rho.estore.model.cdc.CDCEvent;
import com.ibm.rho.estore.model.cdc.CDCEventData;
import com.ibm.rho.estore.model.cdc.CDCEventKey;
import com.ibm.rho.estore.model.cdc.ProductJSON;

@Controller(value = "cacheAPI")
public class CacheAPI {

	private static final Logger log = LoggerFactory.getLogger(CacheAPI.class);

	@Value("${spring.kafka.topics.cache-updates}")
	private String topic;

	@Value("${spring.cache.product-list}")
	private String redisProductCacheSet;

	@Autowired
	private RedisTemplate<String, String> template;

	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	private Sender<String, String> sender;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Read all cached products.
	 * 
	 * @param productListName
	 * @return
	 */
	@RequestMapping(value = "/read-all-cached-products/{product-list-name}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductJSON>> readAllProductsFromCache(
			@PathVariable("product-list-name") String productListName) {

		List<ProductJSON> allProducts = new ArrayList<ProductJSON>();

		Set<String> allProductStrings = template.opsForSet().members(productListName);
		allProductStrings.forEach(productJson -> {
			ProductJSON product = null;
			try {
				product = mapper.readValue(productJson, ProductJSON.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			allProducts.add(product);
		});

		return new ResponseEntity<List<ProductJSON>>(allProducts, HttpStatus.OK);
	}

	/**
	 * Add single product to the cache.
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/add-single-product-to-cache", method = RequestMethod.POST)
	public ResponseEntity<Long> addSingleProductToCache(@Valid @RequestBody ProductDO product) throws Exception {

		Long returnvalue = template.opsForSet().add(redisProductCacheSet, mapper.writeValueAsString(product));

		return new ResponseEntity<Long>(returnvalue, HttpStatus.CREATED);
	}

	/**
	 * Add single product to the cache.
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/add-single-product-to-db", method = RequestMethod.POST)
	public ResponseEntity<Void> addSingleProductToDB(@Valid @RequestBody ProductDO product) throws Exception {

		mongoOps.save(product);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Delete everything from cache before refreshing it.
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete-all-entries-from-cache", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllEntriesFromCache() throws Exception {

		Set<String> values = template.opsForSet().members(redisProductCacheSet);
		values.forEach(value -> {
			template.opsForSet().remove(redisProductCacheSet, value);
		});

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * Read all product data from database.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/read-all-database-products", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDO>> readAllProductsFromDB() throws Exception {

		List<ProductDO> allProducts = mongoOps.findAll(ProductDO.class, "products");
		log.info("(change) allProducts from database = {}", ReflectionToStringBuilder.toString(allProducts));

		return new ResponseEntity<List<ProductDO>>(allProducts, HttpStatus.OK);
	}

	/**
	 * Trigger the cache update 'manually'.
	 * 
	 * @param productJsonData
	 * @return
	 */
	@RequestMapping(value = "/load-cache-from-db", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDO>> loadCacheFromDB() throws Exception {

		List<ProductDO> allProducts = readAllProductsFromDB().getBody();
		log.info("allProducts from database = {}", ReflectionToStringBuilder.toString(allProducts));

		deleteAllEntriesFromCache();

		for (ProductDO productDO : allProducts) {
			String productString = mapper.writeValueAsString(productDO);
			template.opsForSet().add(redisProductCacheSet, productString);
		}

		log.info(" --- Added all DB data to cache ");

		return new ResponseEntity<List<ProductDO>>(allProducts, HttpStatus.OK);
	}

	/**
	 * Trigger the cache update 'manually'.
	 * 
	 * @param cdcEvent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/trigger-cache-data", method = RequestMethod.POST)
	public ResponseEntity<Void> triggerCacheUpdation(@Valid @RequestBody CDCEvent cdcEvent) throws Exception {

		CDCEventKey key = cdcEvent.getKey();
		String keyJson = mapper.writeValueAsString(key);

		CDCEventData value = cdcEvent.getValue();
		String valueJson = mapper.writeValueAsString(value);

		sender.send(topic, keyJson, valueJson);

		log.info("Message sent on {}, key = {}, value = {}", topic, keyJson, valueJson);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
