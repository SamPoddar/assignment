package com.ibm.rho.estore.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Sender<K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private KafkaTemplate<K, V> kafkaTemplate;

	public void send(String topic, V data) {
		LOGGER.info("sending message (k, v) = null ~~ {}", data);
		kafkaTemplate.send(topic, null, data);
	}

	public void send(String topic, K key, V data) {
		LOGGER.info("sending message (k, v) = {} ~~ {}", data);
		kafkaTemplate.send(topic, key, data);
	}

	public void sendRaw(V data, String topic) {

		Message<V> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.MESSAGE_KEY, "k1").build();

		LOGGER.info("sending message='{}'", data.toString());
		kafkaTemplate.send(message);
	}
}
