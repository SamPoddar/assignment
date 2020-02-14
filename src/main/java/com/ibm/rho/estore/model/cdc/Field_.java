
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
@JsonPropertyOrder({ "type", "optional", "field", "name", "version", "parameters", "default" })
public class Field_ {

	@JsonProperty("type")
	private String type;
	@JsonProperty("optional")
	private Boolean optional;
	@JsonProperty("field")
	private String field;
	@JsonProperty("name")
	private String name;
	@JsonProperty("version")
	private Integer version;
	@JsonProperty("parameters")
	private Parameters parameters;
	@JsonProperty("default")
	private String _default;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("optional")
	public Boolean getOptional() {
		return optional;
	}

	@JsonProperty("optional")
	public void setOptional(Boolean optional) {
		this.optional = optional;
	}

	@JsonProperty("field")
	public String getField() {
		return field;
	}

	@JsonProperty("field")
	public void setField(String field) {
		this.field = field;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("version")
	public Integer getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(Integer version) {
		this.version = version;
	}

	@JsonProperty("parameters")
	public Parameters getParameters() {
		return parameters;
	}

	@JsonProperty("parameters")
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	@JsonProperty("default")
	public String getDefault() {
		return _default;
	}

	@JsonProperty("default")
	public void setDefault(String _default) {
		this._default = _default;
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
