package com.ibm.rho.estore.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.annotations.ApiModelProperty;

/**
 * Product
 */

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-18T05:44:56.004Z")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "products")
public class ProductDO {

	//@XmlElement
	//@Field("id")
	//private Long id = null;

	@XmlElement
	@Field("productId")
	@Id
	private String productId = null;

	@XmlElement
	private String productName = null;

	@XmlElement
	private String partNo = null;

	@XmlElement
	private String imageLink = null;

	@XmlElement
	private String productShortDesc = null;

	public ProductDO() {

	}

	public ProductDO productId(String productId) {
		this.productId = productId;
		return this;
	}

	/**
	 * Get productId
	 * 
	 * @return productId
	 **/
	@ApiModelProperty(value = "")

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ProductDO productName(String productName) {
		this.productName = productName;
		return this;
	}

	/**
	 * Get productName
	 * 
	 * @return productName
	 **/
	@ApiModelProperty(value = "")

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductDO partNo(String partNo) {
		this.partNo = partNo;
		return this;
	}

	/**
	 * Get partNo
	 * 
	 * @return partNo
	 **/
	@ApiModelProperty(value = "")

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public ProductDO imageLink(String imageLink) {
		this.imageLink = imageLink;
		return this;
	}

	/**
	 * Get imageLink
	 * 
	 * @return imageLink
	 **/
	@ApiModelProperty(value = "")

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public ProductDO productShortDesc(String productShortDesc) {
		this.productShortDesc = productShortDesc;
		return this;
	}

	/**
	 * Get productShortDesc
	 * 
	 * @return productShortDesc
	 **/
	@ApiModelProperty(value = "")

	public String getProductShortDesc() {
		return productShortDesc;
	}

	public void setProductShortDesc(String productShortDesc) {
		this.productShortDesc = productShortDesc;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductDO product = (ProductDO) o;
		return Objects.equals(this.productId, product.productId)
				&& Objects.equals(this.productName, product.productName) && Objects.equals(this.partNo, product.partNo)
				&& Objects.equals(this.imageLink, product.imageLink)
				&& Objects.equals(this.productShortDesc, product.productShortDesc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, productName, partNo, imageLink, productShortDesc);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Product {\n");

		sb.append(" productId: ").append(toIndentedString(productId)).append("\n");
		sb.append(" productName: ").append(toIndentedString(productName)).append("\n");
		sb.append(" partNo: ").append(toIndentedString(partNo)).append("\n");
		sb.append(" imageLink: ").append(toIndentedString(imageLink)).append("\n");
		sb.append(" productShortDesc: ").append(toIndentedString(productShortDesc)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
