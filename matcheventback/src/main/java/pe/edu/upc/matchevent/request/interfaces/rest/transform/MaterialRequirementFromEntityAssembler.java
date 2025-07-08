package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.entities.MaterialRequirement;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.MaterialRequirementResource;

public class MaterialRequirementFromEntityAssembler {
    public static MaterialRequirementResource toResourceFromEntity(MaterialRequirement entity) {
        return new MaterialRequirementResource(
                entity.getName(),
                entity.getQuantity(),
                entity.getSpecification(),
                entity.isProvidedByCompany()
        );
    }
}
