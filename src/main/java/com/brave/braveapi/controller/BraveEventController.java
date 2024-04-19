package com.brave.braveapi.controller;

import com.brave.braveapi.dto.EventDTO;
import com.brave.braveapi.dto.EventDateDTO;
import com.brave.braveapi.ports.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:8100")
public class BraveEventController {

    @Autowired
    private IEventService service;

    @PostMapping
    public ResponseEntity<EventDTO> insert(
            @RequestBody(required = true) EventDTO body
    ) {
        return ResponseEntity.ok(service.insert(body));
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventDTO> findAllByRange(
            @PathVariable(name = "eventId", required = true) Long eventId
    ) {
        return ResponseEntity.ok(service.findById(eventId));
    }

    @GetMapping("find-all-by-range")
    public ResponseEntity<List<EventDateDTO>> findAllByRange(
            @RequestParam(name = "start", required = true) Long startDate,
            @RequestParam(name = "end", required = true) Long endDate
    ) {
        return ResponseEntity.ok(service.findAllByRangeDate(startDate, endDate));
    }
}
