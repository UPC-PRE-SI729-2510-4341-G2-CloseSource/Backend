package pe.edu.upc.matchevent.request.domain.model.commands;

public record CreateMaterialRequirementCommand(
        String name,
        int quantity,
        String specification,
        boolean providedByCompany
) {}
