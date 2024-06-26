package com.example.calendar_todolist.controller;

import com.example.calendar_todolist.model.Event;
import com.example.calendar_todolist.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CalendarController {
    @Autowired
    private EventService eventService;

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(value = "date", required = false) String date, Model model) {
        LocalDate selectedDate = date != null ? LocalDate.parse(date) : LocalDate.now();
        List<Event> events = eventService.getEventsForDate(selectedDate);
        model.addAttribute("selectedDate", selectedDate);
        model.addAttribute("events", events);
        return "calendar";
    }

    @PostMapping("/calendar")
    public String addEvent(@RequestParam String title, @RequestParam String date) {
        Event event = new Event();
        event.setTitle(title);
        event.setDate(LocalDate.parse(date));
        eventService.addEvent(event);
        return "redirect:/calendar?date=" + date;
    }
}
