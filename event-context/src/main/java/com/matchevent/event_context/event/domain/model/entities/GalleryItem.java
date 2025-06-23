package com.matchevent.event_context.event.domain.model.entities;

import com.matchevent.event_context.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallery_items")
@Getter @Setter @NoArgsConstructor
public class GalleryItem extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private com.matchevent.event_context.event.domain.model.aggregates.Event event;

    public GalleryItem(String imageUrl, String caption) {
        this.imageUrl = imageUrl;
        this.caption  = caption;
    }
}
