package com.example.project.service;

import com.example.project.exception.InvalidCurrencyException;
import com.example.project.model.Event;
import com.example.project.repository.EventRepository;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;


    @Override
    public List<Event> listEvent(){
        return repository.findAll();
    }

    @Override
    public List<Event> findEventByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public List<Event> findEventByUserIdAndMonth(Long id, Integer month) {
        return repository.findByUserIdAndMonth(id, month);
    }

    @Override
    public void saveEvent(Event event){
        if (UtilValidator.validateCurrency(event)){
            throw new InvalidCurrencyException("Currency is wrong, the values accepted are USD or AR");
        }
        repository.save(event);
    }

}
