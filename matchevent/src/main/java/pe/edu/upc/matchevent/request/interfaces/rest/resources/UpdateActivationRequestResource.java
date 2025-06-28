package pe.edu.upc.matchevent.request.interfaces.rest.resources;

public record UpdateActivationRequestResource(
        String eventTitle,
        String eventDescription,
        String location,
        String startDate,
        String endDate,
        String status
) {}
