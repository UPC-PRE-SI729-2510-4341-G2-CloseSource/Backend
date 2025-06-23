package com.matchevent.event_context.event.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.matchevent.event_context.event.domain.model.entities.GalleryItem;

public interface GalleryItemRepository extends JpaRepository<GalleryItem, Long> { }
