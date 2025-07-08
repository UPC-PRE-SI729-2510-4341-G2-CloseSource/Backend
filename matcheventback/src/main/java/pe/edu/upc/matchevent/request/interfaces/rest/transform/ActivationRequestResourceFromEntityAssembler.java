package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.ActivationRequestResource;

import java.util.stream.Collectors;

public class ActivationRequestResourceFromEntityAssembler {
    public static ActivationRequestResource toResourceFromEntity(ActivationRequest entity) {
        return new ActivationRequestResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCompanyId(),
                entity.getStatus().getStringName(),
                ActivationLocationFromEntityAssembler.toResourceFromEntity(entity.getLocation()),
                entity.getMaterials().stream()
                        .map(MaterialRequirementFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList()),
                entity.getEventDateRange(),
                entity.getAudienceProfiles().stream()
                        .map(AudienceProfileFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList())
        );
    }
}
