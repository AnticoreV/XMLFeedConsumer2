package com.XMLFeedConsumer2.service;

import com.XMLFeedConsumer2.model.Product;
import com.XMLFeedConsumer2.repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public String findByProductId(Long id) {
        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        Query query = session.createQuery("FROM Product WHERE product_id = '"+id+"'");
        return query.getResultList().get(0).toString();
    }
}
