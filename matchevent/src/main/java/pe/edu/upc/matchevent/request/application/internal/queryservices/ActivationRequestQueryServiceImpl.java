package pe.edu.upc.matchevent.request.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestQueryService;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;

import java.util.List;

@Service
public class ActivationRequestQueryServiceImpl implements ActivationRequestQueryService {

    private final ActivationRequestRepository activationRequestRepository;

    public ActivationRequestQueryServiceImpl(ActivationRequestRepository activationRequestRepository) {
        this.activationRequestRepository = activationRequestRepository;
    }

    @Override
    public List<ActivationRequest> getAll() {
        return activationRequestRepository.findAll();
    }

    @Override
    public ActivationRequest getById(Long id) {
        return activationRequestRepository.findById(id).orElseThrow();
    }
}
