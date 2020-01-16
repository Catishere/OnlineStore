package com.sap.trashproject.onlinestore.config;

import com.sap.trashproject.onlinestore.repository.EventRepository;
import com.sap.trashproject.onlinestore.repository.ProductRepository;
import com.sap.trashproject.onlinestore.repository.StoreAccountRepository;
import com.sap.trashproject.onlinestore.repository.UserRepository;
import com.sap.trashproject.onlinestore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private EventRepository eventRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private StoreAccountRepository storeAccountRepository;

    @Bean
    public StoreAccountService storeAccountService(StoreAccountRepository storeAccountRepository) {
        return new StoreAccountService(storeAccountRepository);
    }

    @Bean
    public EventProductService eventProductService(EventRepository eventRepository,
                                                   ProductRepository productRepository) {
        return new EventProductService(productRepository, eventRepository);
    }

    @Bean
    public UserDetailsServiceImpl userDetailsServiceImpl(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    public EventService eventService(EventRepository eventRepository) {
        return new EventService(eventRepository);
    }


    @Autowired
    public void eventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void userRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void userRepository(StoreAccountRepository storeAccountRepository) {
        this.storeAccountRepository = storeAccountRepository;
    }

}
