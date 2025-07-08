package pe.edu.upc.matchevent.request.domain.model.commands;

import pe.edu.upc.matchevent.request.domain.model.valueobjects.GenderTarget;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.InterestTag;
import java.util.List;

public record CreateAudienceProfileCommand(
        String ageRange,
        GenderTarget genderTarget,
        List<InterestTag> interests
) {}
