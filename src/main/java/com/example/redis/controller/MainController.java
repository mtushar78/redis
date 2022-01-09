package com.example.redis.controller;

import com.example.redis.entity.Product;
import com.example.redis.repository.ProductDao;
import com.example.redis.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class MainController {
    @Autowired
    ProductDao productDao;

    @PostMapping("save")
    public Product save(@RequestBody Product product){
        return productDao.save(product);
    }
    @GetMapping("get/{id}")
    public Product findProduct(@PathVariable int id){
        return productDao.findProductById(id);
    }
    @DeleteMapping("delete/{id}")
    public String remove(@PathVariable int id){
        return productDao.deleteProduct(id);
    }

    @GetMapping()
    public Product saveDemo(){
        return productDao.saveLocal();
    }

    @GetMapping("getAll")
    public Iterable<Product> getAll(){
        return productDao.findAllProduct();
    }
}
