package pe.edu.upc.matchevent.request.interfaces.rest.resources;

public record MaterialRequirementResource(
        String name,
        int quantity,
        String specification,
        boolean providedByCompany
) {}
