package com.matchevent.event_context.event.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.matchevent.event_context.event.domain.model.aggregates.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> { }
