package com.infosys.module4.controller;


import com.infosys.module4.dto.EventDto;
import com.infosys.module4.exception.EventException;
import com.infosys.module4.model.Event;
import com.infosys.module4.response.EventResponse;
import com.infosys.module4.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EventController {

    @Autowired
    EventService eventService;
    @PostMapping("/addEvent")
    public EventResponse addEvent(@RequestHeader("Authorization") String jwt,@RequestBody EventDto eventDto) throws EventException {
        return eventService.addEvent(jwt, eventDto);
    }

    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents(@RequestHeader("Authorization") String jwt) throws EventException{
        return eventService.getAllEvents();
    }

    @PutMapping("updateEvent/{eventId}")
    public EventResponse updateEvent(@RequestHeader("Authorization") String jwt,@RequestBody EventDto eventDto,@PathVariable Long eventId){
        return eventService.updateEvent(eventId,eventDto);
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public EventResponse deleteEvent(@RequestHeader("Authorization") String jwt,@PathVariable Long eventId){
        return eventService.deleteEvent(eventId);
    }

    @GetMapping("/getEventById/{eventId}")
    public Event getEventByEventId(@RequestHeader("Authorization") String jwt,@PathVariable Long eventId){
        return eventService.getEventById(eventId);
    }
}
