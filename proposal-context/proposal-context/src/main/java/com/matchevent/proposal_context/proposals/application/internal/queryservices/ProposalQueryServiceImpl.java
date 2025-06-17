package com.matchevent.proposal_context.proposals.application.internal.queryservices;

import com.matchevent.proposal_context.proposals.domain.model.aggregates.Proposal;
import com.matchevent.proposal_context.proposals.domain.model.queries.*;
import com.matchevent.proposal_context.proposals.domain.services.ProposalQueryService;
import com.matchevent.proposal_context.proposals.infrastructure.persistence.jpa.repositories.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalQueryServiceImpl implements ProposalQueryService {

    private final ProposalRepository proposalRepository;

    public ProposalQueryServiceImpl(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
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
    public Optional<Proposal> handle(GetProposalByProducerIdQuery query) {
        return this.proposalRepository.findById(query.producerId());
    }

    @Override
    public Optional<Proposal> handle(GetProposalByRequestIdQuery query) {
        return this.proposalRepository.findById(query.requestId());
    }

    @Override
    public Optional<Proposal> handle(GetProposalByServiceIdQuery query) {
        return this.proposalRepository.findById(query.serviceId());
    }

}