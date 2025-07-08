package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.commands.CreateActivationRequestCommand;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.CompanyId;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateActivationRequestResource;

public class CreateActivationRequestCommandFromResourceAssembler {

    public static CreateActivationRequestCommand toCommandFromResource(CreateActivationRequestResource resource) {
        return new CreateActivationRequestCommand(
                resource.title(),
                resource.description(),
                new CompanyId(resource.companyId()),
                resource.status()
        );
    }
}
