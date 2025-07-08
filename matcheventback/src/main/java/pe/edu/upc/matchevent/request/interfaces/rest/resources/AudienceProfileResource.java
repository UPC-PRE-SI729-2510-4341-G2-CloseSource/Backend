package pe.edu.upc.matchevent.request.interfaces.rest.resources;

import pe.edu.upc.matchevent.request.domain.model.valueobjects.GenderTarget;
import java.util.List;

public record AudienceProfileResource(
        String ageRange,
        GenderTarget genderTarget,
        List<String> interests
) {}
