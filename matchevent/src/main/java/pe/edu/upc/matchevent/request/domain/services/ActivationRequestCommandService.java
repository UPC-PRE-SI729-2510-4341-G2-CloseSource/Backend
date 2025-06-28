package pe.edu.upc.matchevent.request.domain.services;

import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.commands.CreateActivationRequestCommand;
import pe.edu.upc.matchevent.request.domain.model.commands.UpdateActivationRequestCommand;

public interface ActivationRequestCommandService {
    ActivationRequest handle(CreateActivationRequestCommand command);
    ActivationRequest handle(UpdateActivationRequestCommand command);
    void deleteById(Long id);
}
