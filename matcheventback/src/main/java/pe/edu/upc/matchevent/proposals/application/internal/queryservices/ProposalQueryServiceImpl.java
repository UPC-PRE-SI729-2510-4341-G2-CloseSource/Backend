package pe.edu.upc.matchevent.proposals.application.internal.queryservices;

import pe.edu.upc.matchevent.iam.domain.model.aggregates.User;
import pe.edu.upc.matchevent.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.edu.upc.matchevent.proposals.domain.model.aggregates.Proposal;
import pe.edu.upc.matchevent.proposals.domain.model.queries.*;
import pe.edu.upc.matchevent.proposals.domain.services.ProposalQueryService;
import pe.edu.upc.matchevent.proposals.infrastructure.persistence.jpa.repositories.ProposalRepository;
import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalQueryServiceImpl implements ProposalQueryService {

    private final UserRepository userRepository;
    private final ProposalRepository proposalRepository;
    private final ActivationRequestRepository activationRequestRepository;

    public ProposalQueryServiceImpl(UserRepository userRepository,
                                    ProposalRepository proposalRepository,
                                    ActivationRequestRepository activationRequestRepository) {
        this.userRepository = userRepository;
        this.proposalRepository = proposalRepository;
        this.activationRequestRepository = activationRequestRepository;
    }

    @Override
    public List<Proposal> handle(GetAllProposalsQuery query) {
        return this.proposalRepository.findAll();
    }

    @Override
    public List<Proposal> handle(GetProposalByNameQuery query) {
        return this.proposalRepository.findProposalByName(query.name());
    }

    @Override
    public List<Proposal> handle(GetProposalByDateRangeQuery query) {
        return this.proposalRepository.findBySubmissionDateBetween(query.startDate(), query.endDate());
    }

    @Override
    public List<Proposal> handle(GetProposalByStatusQuery query) {
        return this.proposalRepository.findByProposalStatus(query.proposalStatus());
    }

    @Override
    public Optional<Proposal> handle(GetProposalByIdQuery query) {
        return this.proposalRepository.findById(query.proposalId());
    }

    @Override
    public List<Proposal> handle(GetProposalByProducerIdQuery query) {
        Long producerId = query.producerId();
        User producer = userRepository.findById(producerId)
                .orElseThrow(() -> new IllegalArgumentException("Producer user not found"));
        return this.proposalRepository.findByProducerId(producer);
    }

    @Override
    public List<Proposal> handle(GetProposalByRequestIdQuery query) {
        Long requestId = query.requestId();
        ActivationRequest request = activationRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Producer user not found"));
        return this.proposalRepository.findByRequestId(request);
    }

}