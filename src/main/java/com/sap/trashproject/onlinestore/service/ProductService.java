package com.sap.trashproject.onlinestore.service;

import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.exception.ProductNotFoundException;
import com.sap.trashproject.onlinestore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class ProductService {

    private ProductRepository productRep;

    public ProductService() {
        this.productRep = new ProductRepository();
    }

    public List<Product> findAll() {
        productRep.openCurrentSessionWithTransaction();
        List<Product> products =  productRep.findAll();
        productRep.closeCurrentSessionWithTransaction();
        return products;
    }

    public Long count() {
        productRep.openCurrentSessionWithTransaction();
        Long count = productRep.count();
        productRep.closeCurrentSessionWithTransaction();
        return count;
    }

    public Product deleteById(Long productId) {
        productRep.openCurrentSessionWithTransaction();
        Product product = productRep.get(productId);
        if (product != null)
            productRep.delete(product);
        productRep.closeCurrentSessionWithTransaction();
        return product;
    }
    public void save(Product product) {
        productRep.openCurrentSessionWithTransaction();
        productRep.save(product);
        productRep.closeCurrentSessionWithTransaction();
    }

    public Product loadProductByProductName(String productName) throws ProductNotFoundException {
        productRep.openCurrentSessionWithTransaction();
        Product product = productRep.findByProductName(productName)
                .orElseThrow(() -> new ProductNotFoundException("Product: " + productName + " not found"));
        productRep.closeCurrentSessionWithTransaction();

        return product;
    }
}
