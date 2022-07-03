package com.assignment.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.product.exception.ResourceNotFoundException;
import com.assignment.product.model.Product;
import com.assignment.product.repository.ProductRepository;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	 Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	/*
	 * Get all products or get product based on UOM
	 * As UOM is optional; if it doesn't have values it will return all the products else it will return based on UOM
	 */
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String uom) {
		
			List<Product> products = new ArrayList<>();
			
			
			if(uom == null) {
				
				logger.info("Get all products");
				
				products = productRepository.findAll();
				 
			}else {
				
				logger.info("Get product by uom");
				products = productRepository.findByUom(uom);
			}
			
			return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createTutorial(@Valid @RequestBody Product product) {
		
		logger.info("Request to save product data");
			Product savedProduct = productRepository
					.save(new Product(product.getProductId(), product.getDescription(), product.getUom()));
		logger.info("Product {} saved"+savedProduct);
			return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return new ResponseEntity<>(product, HttpStatus.OK);
	}	

}
