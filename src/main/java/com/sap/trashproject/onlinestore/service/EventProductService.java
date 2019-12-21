package com.sap.trashproject.onlinestore.service;

import com.sap.trashproject.onlinestore.entity.Event;
import com.sap.trashproject.onlinestore.entity.EventProduct;
import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.exception.EventNotFoundException;
import com.sap.trashproject.onlinestore.exception.ProductNotFoundException;
import com.sap.trashproject.onlinestore.repository.EventRepository;
import com.sap.trashproject.onlinestore.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

public class EventProductService {
    private final ProductRepository productRepository;
    private final EventRepository eventRepository;

    public EventProductService(ProductRepository productRepository,
                               EventRepository eventRepository) {
        this.productRepository = productRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public EventProduct addEventProduct(Long eventId, Long productId, Double price)
            throws ProductNotFoundException, EventNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product " + productId + " not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event " + eventId + " not found"));

        EventProduct eventProduct = new EventProduct(event, product, price);
        product.getEventAssoc().add(eventProduct);
        return eventProduct;
    }

}
