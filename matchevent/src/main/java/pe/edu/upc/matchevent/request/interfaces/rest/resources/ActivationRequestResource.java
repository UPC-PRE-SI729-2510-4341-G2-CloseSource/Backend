package pe.edu.upc.matchevent.request.interfaces.rest.resources;

public record ActivationRequestResource(
        Long id,
        Long companyId,
        String eventTitle,
        String eventDescription,
        String location,
        String startDate,
        String endDate,
        String status
) {}
