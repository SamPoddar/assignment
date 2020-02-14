
package com.ibm.rho.estore.model.cdc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "version", "connector", "name", "ts_ms", "snapshot", "db", "rs", "collection", "ord", "h" })
public class Source {

	@JsonProperty("version")
	private String version;
	@JsonProperty("connector")
	private String connector;
	@JsonProperty("name")
	private String name;
	@JsonProperty("ts_ms")
	private Long tsMs;
	@JsonProperty("snapshot")
	private String snapshot;
	@JsonProperty("db")
	private String db;
	@JsonProperty("rs")
	private String rs;
	@JsonProperty("collection")
	private String collection;
	@JsonProperty("ord")
	private Long ord;
	@JsonProperty("h")
	private Long h;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonProperty("connector")
	public String getConnector() {
		return connector;
	}

	@JsonProperty("connector")
	public void setConnector(String connector) {
		this.connector = connector;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("ts_ms")
	public Long getTsMs() {
		return tsMs;
	}

	@JsonProperty("ts_ms")
	public void setTsMs(Long tsMs) {
		this.tsMs = tsMs;
	}

	@JsonProperty("snapshot")
	public String getSnapshot() {
		return snapshot;
	}

	@JsonProperty("snapshot")
	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	@JsonProperty("db")
	public String getDb() {
		return db;
	}

	@JsonProperty("db")
	public void setDb(String db) {
		this.db = db;
	}

	@JsonProperty("rs")
	public String getRs() {
		return rs;
	}

	@JsonProperty("rs")
	public void setRs(String rs) {
		this.rs = rs;
	}

	@JsonProperty("collection")
	public String getCollection() {
		return collection;
	}

	@JsonProperty("collection")
	public void setCollection(String collection) {
		this.collection = collection;
	}

	@JsonProperty("ord")
	public Long getOrd() {
		return ord;
	}

	@JsonProperty("ord")
	public void setOrd(Long ord) {
		this.ord = ord;
	}

	@JsonProperty("h")
	public Long getH() {
		return h;
	}

	@JsonProperty("h")
	public void setH(Long h) {
		this.h = h;
	}

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
		return ReflectionToStringBuilder.toString(this);
	}
}
