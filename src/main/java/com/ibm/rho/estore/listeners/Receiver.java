package com.ibm.rho.estore.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.rho.estore.model.cdc.CDCEventData;
import com.ibm.rho.estore.model.cdc.CDCEventKey;

@Component
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@Autowired
	private CacheSyncService cacheService;

	private ObjectMapper mapper = new ObjectMapper();

	@KafkaListener(topics = "${spring.kafka.topics.cache-updates}")
	public void receive(@Payload ConsumerRecord<String, String> record, @Headers MessageHeaders headers)
			throws Exception {

		LOGGER.info("KakfaListener received key-json  ='{}'", record.key());
		LOGGER.info("KakfaListener received data-json ='{}'", record.value());

		CDCEventKey key = mapper.readValue(record.key(), CDCEventKey.class);
		CDCEventData data = mapper.readValue(record.value(), CDCEventData.class);

		LOGGER.info("KakfaListener received key-pojo  ='{}'", key);
		LOGGER.info("KakfaListener received data-pojo ='{}'", data);

		// List<Product> allProducts = mapper.readValue(message, List.class);
		// log.info("allProducts read from the topic = {}",
		// ReflectionToStringBuilder.toString(allProducts));

		cacheService.updateCache(key, data);
	}
}
