package com.brave.braveapi.persistence;

import com.brave.braveapi.entidades.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Long> {

    Optional<List<Event>> findAllByEventDateBetween(Date startDate, Date endDate);
}
