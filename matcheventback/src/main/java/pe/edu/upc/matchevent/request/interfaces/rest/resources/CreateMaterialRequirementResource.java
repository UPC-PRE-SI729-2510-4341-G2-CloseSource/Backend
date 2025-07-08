package pe.edu.upc.matchevent.request.interfaces.rest.resources;

public record CreateMaterialRequirementResource(
        String name,
        int quantity,
        String specification,
        boolean providedByCompany
) {
}
