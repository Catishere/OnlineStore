package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    public List<Event> findAll();

    public long count();

    public void deleteById(Long id);

    @SuppressWarnings("unchecked")
    public Event save(Event event);

    public Optional<Event> findEventByName(String name);

    public Optional<Event> findEventById(Long id);
}
