package com.springapp.mvc.dao;

import com.springapp.mvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by adel on 25.05.14.
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
