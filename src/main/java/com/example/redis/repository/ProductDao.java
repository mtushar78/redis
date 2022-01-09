package com.example.redis.repository;

import com.example.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDao {
    public static final String HASH_KEY = "product";

    @Qualifier("template")
    @Autowired
    RedisTemplate template;

    @Autowired
    ProductRepo repo;

    public Product save(Product product) {
        repo.save(product);
        return product;
    }

    public Iterable<Product> findAllProduct() {
        return repo.findAll();
    }

    public Product findProductById(int id) {
        return (Product) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product removed";
    }

    public Product saveLocal() {
        Product product1 = new Product(1, "Car", 2, 2500);
        repo.save(product1);
        return product1;
    }
}
