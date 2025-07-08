package pe.edu.upc.matchevent.request.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.LocationCoordinates;
import pe.edu.upc.matchevent.shared.domain.model.entities.AuditableModel;

@Getter
@Entity
@Table(name = "activation_locations")
public class ActivationLocation extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    @Embedded
    private LocationCoordinates coordinates;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "image_url")
    private String imageUrl;

    public ActivationLocation() {}

    public ActivationLocation(String address, LocationCoordinates coordinates, int capacity, String imageUrl) {
        this.address = address;
        this.coordinates = coordinates;
        this.capacity = capacity;
        this.imageUrl = imageUrl;
    }
}
