package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.Event;
import com.sap.trashproject.onlinestore.entity.EventProduct;
import com.sap.trashproject.onlinestore.entity.StoreAccount;
import com.sap.trashproject.onlinestore.exception.EventNotFoundException;
import com.sap.trashproject.onlinestore.exception.ProductNotFoundException;
import com.sap.trashproject.onlinestore.service.EventProductService;
import com.sap.trashproject.onlinestore.service.EventService;
import com.sap.trashproject.onlinestore.service.StoreAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StoreAccountController {

    private final StoreAccountService storeAccountService;

    public StoreAccountController(StoreAccountService storeAccountService) {
        this.storeAccountService = storeAccountService;
    }

    @GetMapping("/public/account")
    public List<StoreAccount> getAccountInterval(@RequestBody Date from, @RequestBody Date to) {
        System.out.println(from + " " + to);
        return storeAccountService.findInterval(from, to);
    }
}