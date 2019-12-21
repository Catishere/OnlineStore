package com.sap.trashproject.onlinestore.service;

import com.sap.trashproject.onlinestore.entity.Event;
import com.sap.trashproject.onlinestore.exception.EventNotFoundException;
import com.sap.trashproject.onlinestore.repository.EventRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class EventService {

    private final EventRepository eventRepository;

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
    public Event save(Event event) {
        Optional<Event> ev = eventRepository.findEventByName(event.getName());
        if (!ev.isPresent())
            eventRepository.save(event);
        return event;
    }

    @Transactional
    public void update(Event event) {
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
}
