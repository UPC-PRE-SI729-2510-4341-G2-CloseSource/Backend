package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.commands.CreateActivationLocationCommand;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateActivationLocationResource;

public class CreateActivationLocationCommandFromResourceAssembler {
    public static CreateActivationLocationCommand toCommandFromResource(CreateActivationLocationResource resource) {
        return new CreateActivationLocationCommand(
                resource.address(),
                resource.coordinates(),
                resource.capacity(),
                resource.imageUrl()
        );
    }
}
