package com.XMLFeedConsumer2.controller;

import com.XMLFeedConsumer2.model.Product;
import com.XMLFeedConsumer2.service.DescriptionServiceImpl;
import com.XMLFeedConsumer2.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductServiceImpl productService;
    private final DescriptionServiceImpl descriptionService;

    public ProductController(ProductServiceImpl productService, DescriptionServiceImpl descriptionService) {
        this.productService = productService;
        this.descriptionService = descriptionService;
    }

    @GetMapping(path = "/db_id/{id}")
    public String getProductDataByBDId(@PathVariable("id")Long id){
        String data = productService.findById(id).toString() + descriptionService.findById(id);
        return data;
    }

    @GetMapping(path = "/product_id/{id}")
    public String getProductDataByProductId(@PathVariable("id")Long id){
        String data = productService.findByProductId(id);
        return data;
    }
}
