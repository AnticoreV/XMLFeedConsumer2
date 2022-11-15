package com.XMLFeedConsumer2.controller;

import com.XMLFeedConsumer2.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping
    public Product getProductDataById(){
        return null;
    }
}
