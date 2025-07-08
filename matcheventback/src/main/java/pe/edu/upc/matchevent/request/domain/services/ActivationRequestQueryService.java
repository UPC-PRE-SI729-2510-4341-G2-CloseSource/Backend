package pe.edu.upc.matchevent.request.domain.services;

import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.queries.GetAllActivationRequestsQuery;
import pe.edu.upc.matchevent.request.domain.model.queries.GetActivationRequestByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ActivationRequestQueryService {
    List<ActivationRequest> handle(GetAllActivationRequestsQuery query);
    Optional<ActivationRequest> handle(GetActivationRequestByIdQuery query);
}
