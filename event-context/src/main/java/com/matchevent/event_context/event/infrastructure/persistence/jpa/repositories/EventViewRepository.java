package com.matchevent.event_context.event.infrastructure.persistence.jpa.repositories;

import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.event.domain.model.projections.EventView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventViewRepository extends JpaRepository<Event, Long> {

    Optional<EventView> findProjectedById(Long id);
}
