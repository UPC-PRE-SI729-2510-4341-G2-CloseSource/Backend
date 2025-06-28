package pe.edu.upc.matchevent.request.domain.services;

import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;

import java.util.List;

public interface ActivationRequestQueryService {
    List<ActivationRequest> getAll();
    ActivationRequest getById(Long id);
}
