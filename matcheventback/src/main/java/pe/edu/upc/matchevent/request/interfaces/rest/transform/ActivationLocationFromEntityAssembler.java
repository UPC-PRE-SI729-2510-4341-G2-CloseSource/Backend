package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.entities.ActivationLocation;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.ActivationLocationResource;

public class ActivationLocationFromEntityAssembler {
    public static ActivationLocationResource toResourceFromEntity(ActivationLocation entity) {
        if (entity == null) return null;

        return new ActivationLocationResource(
                entity.getAddress(),
                entity.getCoordinates(),
                entity.getCapacity(),
                entity.getImageUrl()
        );
    }
}

