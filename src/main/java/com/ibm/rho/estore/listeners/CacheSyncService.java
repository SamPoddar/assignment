package com.ibm.rho.estore.listeners;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.rho.estore.model.cdc.CDCEventData;
import com.ibm.rho.estore.model.cdc.CDCEventKey;
import com.ibm.rho.estore.model.cdc.ProductJSON;

@Component
public class CacheSyncService {

	private static final Logger log = LoggerFactory.getLogger(CacheSyncService.class);

	@Value("${spring.cache.product-list}")
	private String redisProductCacheSet;

	@Autowired
	private RedisTemplate<String, String> template;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * 
	 * @param key
	 * @param data
	 * @throws Exception
	 */
	public void updateCache(CDCEventKey key, CDCEventData data) throws Exception {

		if (data == null) {

			// Update cache for DELETED Object
			String originalProductString = getOriginalProductString(key.getPayload().getId());

			if (!StringUtils.isEmpty(originalProductString)) {
				template.opsForSet().remove(redisProductCacheSet, originalProductString);
				log.info("DELETION :: Removed from cache : {}", originalProductString);
			}

			return;

		} else {

			String dataInserted = data.getPayload().getAfter();
			String dataUpdated = data.getPayload().getPatch();

			log.info("dataInserted = {}, dataUpdated = {}", dataInserted, dataUpdated);
			log.info("dataDeleted = {}", dataInserted == null && dataUpdated == null);

			if (dataInserted == null && dataUpdated == null) {

				// Update cache for DELETED Object
				String originalProductString = getOriginalProductString(key.getPayload().getId());
				if (!StringUtils.isEmpty(originalProductString)) {
					template.opsForSet().remove(redisProductCacheSet, originalProductString);
					log.info("DELETION :: Removed from cache : {}", originalProductString);
				}

			} else if (!StringUtils.isEmpty(dataInserted)) {

				// Update cache with NEW RECORD
				// productString = mapper.writeValueAsString(dataInserted);

				// Insert the updated product into cache
				template.opsForSet().add(redisProductCacheSet, dataInserted);
				log.info("ADDITION :: Added into cache : {}", dataInserted);

			} else if (!StringUtils.isEmpty(dataUpdated)) {

				// Update cache with UPDATED RECORD
				// productString = mapper.writeValueAsString(dataUpdated);

				// Remove the original product from cache
				String originalProductString = getOriginalProductString(key.getPayload().getId());
				template.opsForSet().remove(redisProductCacheSet, originalProductString);
				log.info("UPDATION :: Removed from cache : {}", originalProductString);

				// Insert the updated product into cache
				template.opsForSet().add(redisProductCacheSet, dataUpdated);
				log.info("UPDATION :: Added into cache : {}", dataUpdated);
			}
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private String getOriginalProductString(String id) {

		id = id.replaceAll("\"", StringUtils.EMPTY); // remove pre and post quotes

		log.info("Searching cache for record with id = {}", id);

		Set<String> values = template.opsForSet().members(redisProductCacheSet);

		String[] productArr = (String[]) values.toArray(new String[values.size()]);

		for (String productString : productArr) {

			ProductJSON product = null;
			try {
				product = mapper.readValue(productString, ProductJSON.class);
				log.info("Current product in cache id = {}", product.getProductId());

				if (id.equals(product.getProductId())) {
					log.info("FOUND product in cache with id = {}", product.getProductId());
					return productString;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return null;
	}

	/**
	 * Delete everything from cache before refreshing it.
	 */
	private void deleteAllEntriesFromCache() {

		Set<String> values = template.opsForSet().members(redisProductCacheSet);
		values.forEach(value -> {
			template.opsForSet().remove(redisProductCacheSet, value);
		});
	}
}
