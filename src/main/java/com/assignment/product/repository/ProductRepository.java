package com.assignment.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByUom(String uom);

}
