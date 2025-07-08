package pe.edu.upc.matchevent.request.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.queries.GetActivationRequestByIdQuery;
import pe.edu.upc.matchevent.request.domain.model.queries.GetAllActivationRequestsQuery;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestQueryService;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActivationRequestQueryServiceImpl implements ActivationRequestQueryService {

    private final ActivationRequestRepository activationRequestRepository;

    public ActivationRequestQueryServiceImpl(ActivationRequestRepository activationRequestRepository) {
        this.activationRequestRepository = activationRequestRepository;
    }

    @Override
    public List<ActivationRequest> handle(GetAllActivationRequestsQuery query) {
        return this.activationRequestRepository.findAll();
    }

    @Override
    public Optional<ActivationRequest> handle(GetActivationRequestByIdQuery query) {
        return this.activationRequestRepository.findById(query.activationRequestId());
    }
}
