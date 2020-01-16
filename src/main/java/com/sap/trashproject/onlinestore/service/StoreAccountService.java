package com.sap.trashproject.onlinestore.service;

import com.sap.trashproject.onlinestore.entity.StoreAccount;
import com.sap.trashproject.onlinestore.repository.StoreAccountRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class StoreAccountService {

    private final StoreAccountRepository storeAccountRepository;

    public StoreAccountService(StoreAccountRepository storeAccountRepository) {
        this.storeAccountRepository = storeAccountRepository;
    }

    @Transactional
    public List<StoreAccount> findAll() {
        return storeAccountRepository.findAll();
    }

    @Transactional
    public Long count() {
        return storeAccountRepository.count();
    }

    @Transactional
    public void deleteById(Long recordId) {
        storeAccountRepository.deleteById(recordId);
    }

    @Transactional
    public StoreAccount save(StoreAccount record) {
        storeAccountRepository.save(record);
        return record;
    }

    @Transactional
    public void update(StoreAccount record) {
        storeAccountRepository.save(record);
    }

    @Transactional
    public List<StoreAccount> findInterval(Date date1, Date date2) {
        return storeAccountRepository.findByDateBetween(date1, date2);
    }
}
