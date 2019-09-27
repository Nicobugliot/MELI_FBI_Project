package com.example.project.controller;

import com.example.project.repository.EventRepository;
import com.example.project.service.EventService;
import com.example.project.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.project.model.Event;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> list(){
        return eventService.listEvent();
    }

    @GetMapping("/events/{user_id}")
    public List<Event> listEventById(@PathVariable Long user_id){
        return eventService.findEventByUserId(user_id);
    }

    @GetMapping("/events/")
    public List<Event> listEventByIdAndMonth(@PathVariable Long user_id, @PathVariable Integer month){
        return eventService.findEventByUserIdAndMonth(user_id, month);
    }

    @PostMapping("/events")
    public void insertEvent(@Valid @RequestBody Event event){
        eventService.saveEvent(event);
    }

    /*
    @PutMapping
    public Event modify(@RequestBody Event event){
        return repository.save(event);
    }

    @DeleteMapping(value = "/{event_id}")
    public void delete(@PathVariable("event_id") Long id){
        repository.deleteById(id);
    }
     */

}
