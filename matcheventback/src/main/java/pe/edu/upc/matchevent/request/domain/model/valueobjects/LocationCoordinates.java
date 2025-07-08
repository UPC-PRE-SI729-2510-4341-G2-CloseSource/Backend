package pe.edu.upc.matchevent.request.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record LocationCoordinates(double latitude, double longitude) {
    public LocationCoordinates {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
    }

    public LocationCoordinates() {
        this(0.0, 0.0);
    }
}
