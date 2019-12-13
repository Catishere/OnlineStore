package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.Event;
import com.sap.trashproject.onlinestore.entity.Event;
import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.exception.EventNotFoundException;
import com.sap.trashproject.onlinestore.service.EventService;
import com.sap.trashproject.onlinestore.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity deleteEvent(@PathVariable( "id" ) Long id) {
        eventService.deleteById(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable( "id" ) Long id) throws EventNotFoundException {
        return eventService.getEventById(id);
    }

    @PostMapping("/events/{eventId}")
    public Event addEventProduct(@PathVariable( "eventId" ) Long eventId, @RequestBody Product product)
            throws EventNotFoundException {
        return eventService.saveEventProduct(eventId, product);
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.findAll();
    }

    @PostMapping("/events")
    public Event addEventEvent(@RequestBody Event event) {
        eventService.save(event);
        return event;
    }

}