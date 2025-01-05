package com.infosys.module4.service;

import com.infosys.module4.dto.EventDto;
import com.infosys.module4.exception.EventException;
import com.infosys.module4.model.Event;
import com.infosys.module4.response.EventResponse;

import java.util.List;

public interface EventService {
    public EventResponse addEvent(String jwt, EventDto eventDto) throws EventException;
    public List<Event> getAllEvents() throws EventException;
    public EventResponse updateEvent(Long eventId,EventDto eventDto) throws EventException;
    public EventResponse deleteEvent(Long eventId) throws EventException;
    public Event getEventById(Long eventId) throws EventException;
}
