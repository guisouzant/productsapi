package com.products.productsapi.controller;

import com.products.productsapi.model.Product;
import com.products.productsapi.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        product.setId(UUID.randomUUID().toString());
        productRepository.save(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable String id) {
        return productRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Product updateById(@PathVariable("id") String id, @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        productRepository.deleteById(id);
    }

    @GetMapping
    public List<Product> findByName(@RequestParam(name = "name") String name) {
        return productRepository.findAllByName(name).stream().toList();
    }

    @GetMapping("/all")
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
