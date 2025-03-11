package it.kit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.kit.binding.EventForm;
import it.kit.entity.EventEntity;

public interface EventService {

    public String createEvent(EventForm Event);
	
    EventEntity updateEvent(EventEntity event, Integer id);

	public String deleteEventById(Integer  eventid);
	
	public Page<EventEntity> getAllEvents(String name,  Pageable pageable);
	
}
