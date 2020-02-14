
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
@JsonPropertyOrder({ "id", "after", "patch", "source", "op", "ts_ms" })
public class Payload {

	@JsonProperty("id")
	private String id;
	@JsonProperty("after")
	private String after;
	@JsonProperty("patch")
	private String patch;
	@JsonProperty("source")
	private Source source;
	@JsonProperty("op")
	private String op;
	@JsonProperty("ts_ms")
	private Long tsMs;
	@JsonIgnore
	private Map<String, String> additionalProperties = new HashMap<String, String>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("after")
	public String getAfter() {
		return after;
	}

	@JsonProperty("after")
	public void setAfter(String after) {
		this.after = after;
	}

	@JsonProperty("patch")
	public String getPatch() {
		return patch;
	}

	@JsonProperty("patch")
	public void setPatch(String patch) {
		this.patch = patch;
	}

	@JsonProperty("source")
	public Source getSource() {
		return source;
	}

	@JsonProperty("source")
	public void setSource(Source source) {
		this.source = source;
	}

	@JsonProperty("op")
	public String getOp() {
		return op;
	}

	@JsonProperty("op")
	public void setOp(String op) {
		this.op = op;
	}

	@JsonProperty("ts_ms")
	public Long getTsMs() {
		return tsMs;
	}

	@JsonProperty("ts_ms")
	public void setTsMs(Long tsMs) {
		this.tsMs = tsMs;
	}

	@JsonAnyGetter
	public Map<String, String> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, String value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
