
package com.ibm.rho.estore.model.cdc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "payload" })
public class CDCEvent {

	@JsonProperty("key")
	private CDCEventKey key;

	@JsonProperty("value")
	private CDCEventData value;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@JsonProperty("key")
	public CDCEventKey getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(CDCEventKey key) {
		this.key = key;
	}

	@JsonProperty("value")
	public CDCEventData getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(CDCEventData value) {
		this.value = value;
	}
}
