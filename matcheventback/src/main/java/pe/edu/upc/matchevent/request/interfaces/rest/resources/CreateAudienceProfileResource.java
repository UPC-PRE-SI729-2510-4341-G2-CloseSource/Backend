package pe.edu.upc.matchevent.request.interfaces.rest.resources;

import pe.edu.upc.matchevent.request.domain.model.valueobjects.GenderTarget;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.InterestTag;

import java.util.List;

public record CreateAudienceProfileResource(
        String ageRange,
        GenderTarget genderTarget,
        List<InterestTag> interests
) {
}
