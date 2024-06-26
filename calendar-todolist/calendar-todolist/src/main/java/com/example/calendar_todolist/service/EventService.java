package com.example.calendar_todolist.service;

import com.example.calendar_todolist.model.Event;
import com.example.calendar_todolist.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsForDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}