package com.brave.braveapi.ports;

import com.brave.braveapi.dto.EventDTO;
import com.brave.braveapi.dto.EventDateDTO;

import java.util.List;

public interface IEventService {

    EventDTO findById(Long id);

    public EventDTO insert(EventDTO event);

    List<EventDateDTO> findAllByRangeDate(Long startDate, Long endDate);
}
