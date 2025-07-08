package pe.edu.upc.matchevent.request.interfaces.rest.transform;
import pe.edu.upc.matchevent.request.domain.model.commands.CreateMaterialRequirementCommand;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateMaterialRequirementResource;

public class CreateMaterialRequirementCommandFromResourceAssembler {
    public static CreateMaterialRequirementCommand toCommandFromResource(CreateMaterialRequirementResource resource) {
        return new CreateMaterialRequirementCommand(
                resource.name(),
                resource.quantity(),
                resource.specification(),
                resource.providedByCompany()
        );
    }
}
