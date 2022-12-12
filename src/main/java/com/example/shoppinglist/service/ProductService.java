package com.example.shoppinglist.service;

import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product save(String title) {
        Product product = new Product(title);
        productRepository.save(product);
        return product;
    }

    public Product update(Long id, String title, boolean isBought) {
        Product product = new Product(id, title);
        product.setBought(isBought);
        productRepository.save(product);
        return product;
    }

    public long delete(Long id) {
        var product = productRepository.findById(id).get();
        productRepository.delete(product);
        return product.getId();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
