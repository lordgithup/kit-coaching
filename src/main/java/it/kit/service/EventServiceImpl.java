package it.kit.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import it.kit.binding.EventForm;
import it.kit.entity.EventEntity;
import it.kit.exception.EventNotFoundException;
import it.kit.repository.EventRepository;


@Service
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepository eventRepo;
	
	@Override
	public String createEvent(EventForm Event) {
		// TODO Auto-generated method stub
				EventEntity master = new EventEntity();
				BeanUtils.copyProperties(Event, master);
				
				EventEntity savedEvent= eventRepo.save(master);				
					return savedEvent != null? "event is registered" : "Problem is event registration";
			}



	  @Override
	    public EventEntity updateEvent(EventEntity event, Integer id) {
	        return eventRepo.findById(id).map(existing -> {
	            existing.setName(event.getName());
	            return eventRepo.save(existing);
	        }).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
	    }

	@Override
	public String deleteEventById(Integer eventid) {
		// TODO Auto-generated method stub
		Optional<EventEntity>opt= eventRepo.findById(eventid);
		if(opt.isPresent()) {
			eventRepo.deleteById(eventid);
			return "event  is deleted";
		}
		 throw new EventNotFoundException("Event not found with ID: ");
	}



	@Override
	public Page<EventEntity> getAllEvents(String name, Pageable pageable) {
		// TODO Auto-generated method stub

		 if (name != null && !name.isEmpty()) {
	            return eventRepo.findByName(name, pageable);
	        }
	        
	        // If no filters are provided, return all records
	        else {
	            return eventRepo.findAll(pageable);
	        }
	}
	

	
}
