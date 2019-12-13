package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.exception.ProductNotFoundException;
import com.sap.trashproject.onlinestore.security.JwtTokenProvider;
import com.sap.trashproject.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable( "id" ) Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable( "id" ) Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        System.out.println("PRODUCT: " + product.getName());
        productService.save(product);
        return product;
    }

}