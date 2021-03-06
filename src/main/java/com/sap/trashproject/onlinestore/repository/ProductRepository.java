package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public List<Product> findAll();

    public long count();

    public void deleteById(Long id);

    @SuppressWarnings("unchecked")
    public Product save(Product user);

    public Optional<Product> findProductByName(String name);

    public List<Product> findAllByPriceLessThan(Double price);
}
