package pe.edu.upc.matchevent.request.interfaces.rest.resources;

import pe.edu.upc.matchevent.request.domain.model.valueobjects.LocationCoordinates;

public record ActivationLocationResource(
        String address,
        LocationCoordinates coordinates,
        int capacity,
        String imageUrl
) {}
