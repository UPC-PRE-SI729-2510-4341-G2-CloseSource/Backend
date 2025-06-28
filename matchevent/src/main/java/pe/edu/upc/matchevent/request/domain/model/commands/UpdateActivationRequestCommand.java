package pe.edu.upc.matchevent.request.domain.model.commands;

public record UpdateActivationRequestCommand(
        Long id,
        Long companyId,
        String eventTitle,
        String eventDescription,
        String location,
        String startDate,
        String endDate,
        String status
) {}
