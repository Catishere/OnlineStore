package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findAll();

    public long count();

    public void deleteById(Long id);

    @SuppressWarnings("unchecked")
    public User save(User user);

    public User findByUsername(String username);
}
