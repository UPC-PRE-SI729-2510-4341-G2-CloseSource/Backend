package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.commands.CreateAudienceProfileCommand;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateAudienceProfileResource;

public class CreateAudienceProfileCommandFromResourceAssembler {
    public static CreateAudienceProfileCommand toCommandFromResource(CreateAudienceProfileResource resource) {
        return new CreateAudienceProfileCommand(
                resource.ageRange(),
                resource.genderTarget(),
                resource.interests()
        );
    }
}
