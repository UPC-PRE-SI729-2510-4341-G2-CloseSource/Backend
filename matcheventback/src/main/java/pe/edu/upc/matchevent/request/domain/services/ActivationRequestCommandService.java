package pe.edu.upc.matchevent.request.domain.services;

import pe.edu.upc.matchevent.request.domain.model.commands.CreateActivationRequestCommand;

public interface ActivationRequestCommandService {
    Long handle(CreateActivationRequestCommand command);
}
