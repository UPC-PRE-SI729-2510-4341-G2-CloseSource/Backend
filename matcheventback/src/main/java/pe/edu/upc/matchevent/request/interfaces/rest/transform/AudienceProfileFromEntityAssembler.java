package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.entities.AudienceProfile;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.AudienceProfileResource;

import java.util.stream.Collectors;

public class AudienceProfileFromEntityAssembler {
    public static AudienceProfileResource toResourceFromEntity(AudienceProfile entity) {
        return new AudienceProfileResource(
                entity.getAgeRange(),
                entity.getGenderTarget(),
                entity.getInterests()
                        .stream()
                        .map(Enum::name)
                        .collect(Collectors.toList())
        );
    }
}
