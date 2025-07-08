package pe.edu.upc.matchevent.request.domain.services;

import pe.edu.upc.matchevent.request.domain.model.commands.SeedRequestStatusesCommand;

public interface RequestStatusCommandService {
    void handle(SeedRequestStatusesCommand command);
}
