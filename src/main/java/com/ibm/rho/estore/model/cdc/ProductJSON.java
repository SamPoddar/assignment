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

/**
 * Product
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "productName", "partNo", "imageLink", "productShortDesc" })
public class ProductJSON {

	@JsonProperty("_id")
	@JsonIgnore
	private Id id;

	@JsonProperty("productId")
	private String productId;

	@JsonProperty("productName")
	private String productName;

	@JsonProperty("partNo")
	private String partNo;

	@JsonProperty("imageLink")
	private String imageLink;

	@JsonProperty("productShortDesc")
	private String productShortDesc;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//	@JsonProperty("_id")
//	public Id getId() {
//		return id;
//	}
//
//	@JsonProperty("_id")
//	public void setId(Id id) {
//		this.id = id;
//	}

	@JsonProperty("productId")
	public String getProductId() {
		return productId;
	}

	@JsonProperty("productId")
	public void setProductId(String productId) {
		this.productId = productId;
	}

	@JsonProperty("productName")
	public String getProductName() {
		return productName;
	}

	@JsonProperty("productName")
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@JsonProperty("partNo")
	public String getPartNo() {
		return partNo;
	}

	@JsonProperty("partNo")
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	@JsonProperty("imageLink")
	public String getImageLink() {
		return imageLink;
	}

	@JsonProperty("imageLink")
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@JsonProperty("productShortDesc")
	public String getProductShortDesc() {
		return productShortDesc;
	}

	@JsonProperty("productShortDesc")
	public void setProductShortDesc(String productShortDesc) {
		this.productShortDesc = productShortDesc;
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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
