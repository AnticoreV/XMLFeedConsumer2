package com.XMLFeedConsumer2.service;

import com.XMLFeedConsumer2.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Product update(Product product);
    String findByProductId(Long id);
}
