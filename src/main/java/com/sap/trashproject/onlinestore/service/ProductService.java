package com.sap.trashproject.onlinestore.service;

//import com.sap.trashproject.onlinestore.entity.EventPrice;
import com.sap.trashproject.onlinestore.entity.Product;
        import com.sap.trashproject.onlinestore.exception.ProductNotFoundException;
        import com.sap.trashproject.onlinestore.repository.ProductRepository;
        import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Long count() {
        return productRepository.count();
    }

    @Transactional
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public Product save(Product product) {
        Optional<Product> ev = productRepository.findProductByName(product.getName());
        if (!ev.isPresent())
            productRepository.save(product);
        return product;
    }

    @Transactional
    public Product loadProductByProductName(String productName) throws ProductNotFoundException {
        return productRepository.findProductByName(productName)
                .orElseThrow(() -> new ProductNotFoundException("Product: " + productName + " not found"));
    }

    @Transactional
    public Product getProductById(Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product " + productId + " not found"));
    }
}
