package com.sap.trashproject.onlinestore.service;

import com.sap.trashproject.onlinestore.entity.Event;
import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.exception.EventNotFoundException;
import com.sap.trashproject.onlinestore.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional
    public Long count() {
        return eventRepository.count();
    }

    @Transactional
    public void deleteById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Transactional
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public Event loadEventByEventName(String eventName) throws EventNotFoundException {
        return eventRepository.findEventByName(eventName)
                .orElseThrow(() -> new EventNotFoundException("No event with such name"));
    }

    @Transactional
    public Event getEventById(Long eventId) throws EventNotFoundException {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("No event with such id"));
    }

    @Transactional
    public Event saveEventProduct(Long eventId, Product product) throws EventNotFoundException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("No event with such id"));
        event.getProducts().add(product);
        eventRepository.save(event);
        return event;
    }
}
