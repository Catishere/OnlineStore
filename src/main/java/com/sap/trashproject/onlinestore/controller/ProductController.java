package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.EventProduct;
import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.exception.EventNotFoundException;
import com.sap.trashproject.onlinestore.exception.ProductNotFoundException;
//import com.sap.trashproject.onlinestore.service.EventPriceService;
import com.sap.trashproject.onlinestore.service.EventProductService;
import com.sap.trashproject.onlinestore.service.EventService;
import com.sap.trashproject.onlinestore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    private final ProductService productService;
    private final EventProductService eventProductService;

    public ProductController(ProductService productService,
                             EventProductService eventProductService) {
        this.productService = productService;
        this.eventProductService = eventProductService;
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable( "id" ) Long id) {
        System.out.println("DELETED ID ");
        productService.deleteById(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/public/products/{id}")
    public Product getProduct(@PathVariable( "id" ) Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }
    @GetMapping("/public/products")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @PostMapping("/admin/products")
    public Product addProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    @PostMapping("/admin/products/{productId}/events/{eventId}")
    public EventProduct addEventProduct(@PathVariable( "productId" ) Long productId,
                                        @PathVariable( "eventId" ) Long eventId,
                                        @RequestBody Double price)
            throws ProductNotFoundException, EventNotFoundException {
        return eventProductService.addEventProduct(productId, eventId, price);
    }
}