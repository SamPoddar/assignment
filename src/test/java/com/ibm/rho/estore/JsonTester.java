package com.ibm.rho.estore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.rho.estore.controller.CacheAPI;
import com.ibm.rho.estore.model.cdc.CDCEvent;
import com.ibm.rho.estore.model.cdc.CDCEventData;
import com.ibm.rho.estore.model.cdc.CDCEventKey;
import com.ibm.rho.estore.model.cdc.ProductJSON;

/**
 * 
 * @author AmitANikam
 *
 */
public class JsonTester {

	private static final Logger log = LoggerFactory.getLogger(CacheAPI.class);

	private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		new JsonTester().test();
	}

	public void test() throws Exception {

		CDCEventData cdcEventData = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("sample-cdc-new-event-data.json"),
				CDCEventData.class);

		log.info("ProductCDCEventData = {}", cdcEventData);

		// --------------------------------------------------------------------------------------------------
		CDCEventKey cdcEventkey = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("sample-cdc-new-event-key.json"),
				CDCEventKey.class);

		log.info("ProductCDCEventKey = {}", cdcEventkey);

		// --------------------------------------------------------------------------------------------------
		ProductJSON productJson = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("sample-cdc-product.json"), ProductJSON.class);
		log.info("productJson = {}", productJson);

		// --------------------------------------------------------------------------------------------------
		CDCEvent cdcEvent = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("sample-full-cdc-event.json"), CDCEvent.class);
		log.info("CDCEvent = {}", cdcEvent);

	}
}
