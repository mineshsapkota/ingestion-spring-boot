package com.assignment.product.validation;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.assignment.product.exception.BadRequestException;
import com.assignment.product.model.Product;

@Component
public class ProductValidation {
	
	Logger logger = LoggerFactory.getLogger(ProductValidation.class);
	
	public void validateProduct(Product product) throws BadRequestException {
		
		logger.info("Inside product validation");
		
		validateProductId(product.getProductId());
		validateProductDescription(product.getDescription());
		validateProductUom(product.getUom());	
		
	}
	
	public void validateProductId(String productId)throws BadRequestException {
		if(productId == null || productId.isEmpty()) {
			logger.error("Product Id cannot be null");
			throw new BadRequestException("Product Id cannot be null");
		}
	}
	
	public void validateProductDescription(String description) throws BadRequestException {
		
		if(description == null || description.isEmpty()) {
			
			logger.error("Product Description cannot be null");
			throw new BadRequestException("Description cannot be null");
			
		}
	}
		
		public void validateProductUom(String uom) throws BadRequestException{
			
			if(uom == null || uom.isEmpty()) {
				
				logger.error("Product Uom cannot be null");
				throw new BadRequestException("Product uom cannot be null");
				
			}
			
		}
		

}
