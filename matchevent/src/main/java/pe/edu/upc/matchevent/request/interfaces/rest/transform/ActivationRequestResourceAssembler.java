package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.ActivationRequestResource;

public class ActivationRequestResourceAssembler {

    public static ActivationRequestResource toResourceFromEntity(ActivationRequest entity) {
        return new ActivationRequestResource(
                entity.getId(),
                entity.getCompanyId(),
                entity.getEventTitle(),
                entity.getEventDescription(),
                entity.getLocation(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus()
        );
    }
}
