package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.StoreAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreAccountRepository extends CrudRepository<StoreAccount, Long> {

    public List<StoreAccount> findAll();

    public long count();

    public void deleteById(Long id);

    @SuppressWarnings("unchecked")
    public StoreAccount save(StoreAccount record);

    public Optional<StoreAccount> findStoreAccountById(Long id);

    public List<StoreAccount> findByDateBetween(Date date1, Date date2);
}
