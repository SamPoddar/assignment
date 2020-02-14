package com.ibm.rho.estore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModelProperty;
import redis.clients.jedis.Jedis;

/**
 * 
 * @author AmitANikam
 *
 */
public class RedisTester {

	public static void main(String[] args) {

		List<Product> allProducts = new ArrayList<Product>();

		Jedis jedis = null;

		try {
			jedis = new Jedis("localhost", 6379);

			ObjectMapper mapper = new ObjectMapper();

			Set<String> allproducts = jedis.smembers("all-products");

			for (String productData : allproducts) {

				System.out.println("Main productData = " + productData);

				Product product = mapper.readValue(productData, Product.class);
				allProducts.add(product);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			/// Return the Jedis instance to the pool
			if (jedis != null) {
				jedis.close();
				jedis = null;
			}
		}
	}

}

class Product {

	@XmlElement
	private Long id = null;

	@XmlElement
	private String productId = null;

	@XmlElement
	private String productName = null;

	@XmlElement
	private String partNo = null;

	@XmlElement
	private String imageLink = null;

	@XmlElement
	private String productShortDesc = null;

	public Product() {

	}

	public Product(Long id, String productId, String productName, String partNo, String imageLink,
			String productShortDesc) {

		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.partNo = partNo;
		this.imageLink = imageLink;
		this.productShortDesc = productShortDesc;
	}

	public Product id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product productId(String productId) {
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

	public Product productName(String productName) {
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

	public Product partNo(String partNo) {
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

	public Product imageLink(String imageLink) {
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

	public Product productShortDesc(String productShortDesc) {
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
		Product product = (Product) o;
		return Objects.equals(this.id, product.id) && Objects.equals(this.productId, product.productId)
				&& Objects.equals(this.productName, product.productName) && Objects.equals(this.partNo, product.partNo)
				&& Objects.equals(this.imageLink, product.imageLink)
				&& Objects.equals(this.productShortDesc, product.productShortDesc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productId, productName, partNo, imageLink, productShortDesc);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Product {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
		sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
		sb.append("    partNo: ").append(toIndentedString(partNo)).append("\n");
		sb.append("    imageLink: ").append(toIndentedString(imageLink)).append("\n");
		sb.append("    productShortDesc: ").append(toIndentedString(productShortDesc)).append("\n");
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
