package pe.edu.upc.matchevent.request.interfaces.rest.transform;

import pe.edu.upc.matchevent.request.domain.model.commands.CreateEventDateRangeCommand;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateEventDateRangeResource;

public class CreateEventDateRangeCommandFromResourceAssembler {
    public static CreateEventDateRangeCommand toCommandFromResource(CreateEventDateRangeResource resource) {
        return new CreateEventDateRangeCommand(resource.startDate(), resource.endDate());
    }
}
