package com.matchevent.event_context.event.domain.model.entities;

import com.matchevent.event_context.event.domain.model.aggregates.Event;
import com.matchevent.event_context.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallery_items")
@Getter @Setter @NoArgsConstructor
public class GalleryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    /** Marcas de auditoría embebidas */
    @Embedded
    private AuditableModel audit = new AuditableModel();

    public GalleryItem(String imageUrl, String caption) {
        this.imageUrl = imageUrl;
        this.caption  = caption;
    }
}
