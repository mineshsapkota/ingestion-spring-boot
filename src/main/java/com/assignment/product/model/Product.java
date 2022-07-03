package com.assignment.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name ="products")
public class Product {
	
	@Id
	@Column(name = "product_Id", nullable = false)
	@NotEmpty(message="Product Id cannot be null or empty")
	private String productId;
	@Column(name = "description", nullable = false)
	@NotEmpty(message="Product description cannot be null or empty")
	private String description;
	@Column(name = "uom", nullable = false)
	@NotEmpty(message="Product uom cannot be null or empty")
	private String uom;
	
	public Product() {
		
	}
	
	public Product(String productId, String description, String uom) {
		this.productId = productId;
		this.description=description;
		this.uom=uom;
	}
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
	
	
	

}
