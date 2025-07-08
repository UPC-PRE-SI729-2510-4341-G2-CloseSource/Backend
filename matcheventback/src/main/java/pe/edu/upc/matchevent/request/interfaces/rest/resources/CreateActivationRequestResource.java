package pe.edu.upc.matchevent.request.interfaces.rest.resources;

public record CreateActivationRequestResource(
        String title,
        String description,
        Long companyId,
        String status
) {
}
