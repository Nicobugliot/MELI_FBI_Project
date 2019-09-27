package com.example.project.service;

import com.example.project.model.Event;

import java.util.List;

public interface EventService {

    List<Event> listEvent();

    List<Event> findEventByUserId(Long id);

    List<Event> findEventByUserIdAndMonth(Long id, Integer month);

    void saveEvent(Event event);

}
