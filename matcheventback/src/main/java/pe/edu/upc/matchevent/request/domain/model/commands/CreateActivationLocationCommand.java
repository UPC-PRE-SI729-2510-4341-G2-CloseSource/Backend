package pe.edu.upc.matchevent.request.domain.model.commands;

import pe.edu.upc.matchevent.request.domain.model.valueobjects.LocationCoordinates;

public record CreateActivationLocationCommand(
        String address,
        LocationCoordinates coordinates,
        int capacity,
        String imageUrl
) {}
