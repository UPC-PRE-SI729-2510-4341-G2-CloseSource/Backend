package com.matchevent.event_context.event.domain.model.aggregates;

import com.matchevent.event_context.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.matchevent.event_context.event.domain.model.entities.GalleryItem;
import com.matchevent.event_context.event.domain.model.valueobjects.EventStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter @Setter @NoArgsConstructor
public class Event extends AuditableAbstractAggregateRoot<Event> {

    /* ------------ atributos propios ------------ */

    @Column(nullable = false)
    private Long proposalId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.SCHEDULED;

    private String description;
    private String location;

    /* ------------ relaciones ------------ */

    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<GalleryItem> galleryItems = new HashSet<>();

    /* ------------ constructores / helpers ------------ */

    public Event(Long proposalId, LocalDateTime startDate,
                 LocalDateTime endDate, String description, String location) {
        this.proposalId  = proposalId;
        this.startDate   = startDate;
        this.endDate     = endDate;
        this.description = description;
        this.location    = location;
    }

    public void changeStatus(EventStatus newStatus) { this.status = newStatus; }

    public void addGalleryItem(GalleryItem item) {
        item.setEvent(this);
        galleryItems.add(item);
    }
}
