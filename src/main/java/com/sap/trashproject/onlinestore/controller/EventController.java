package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.Event;
import com.sap.trashproject.onlinestore.entity.EventProduct;
import com.sap.trashproject.onlinestore.exception.EventNotFoundException;
import com.sap.trashproject.onlinestore.exception.ProductNotFoundException;
import com.sap.trashproject.onlinestore.service.EventProductService;
import com.sap.trashproject.onlinestore.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EventController {

    private final EventProductService eventProductService;
    private final EventService eventService;

    public EventController(EventProductService eventProductService, EventService eventService) {
        this.eventProductService = eventProductService;
        this.eventService = eventService;
    }

    @DeleteMapping("/admin/events/{id}")
    public ResponseEntity deleteEvent(@PathVariable( "id" ) Long id) {
        eventService.deleteById(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/public/events/{id}")
    public Event getEvent(@PathVariable( "id" ) Long id) throws EventNotFoundException {
        return eventService.getEventById(id);
    }

    @GetMapping("/public/events")
    public List<Event> getEvents() {
        return eventService.findAll();
    }

    @PostMapping("/admin/events")
    public Event addEvent(@RequestBody Event event) {
        eventService.save(event);
        return event;
    }

    @PostMapping("/admin/events/{eventId}/products/{productId}")
    public EventProduct addEventProduct(@PathVariable( "productId" ) Long productId,
                                        @PathVariable( "eventId" ) Long eventId,
                                        @RequestBody Double price)
            throws ProductNotFoundException, EventNotFoundException {
        return eventProductService.addEventProduct(productId, eventId, price);
    }
}