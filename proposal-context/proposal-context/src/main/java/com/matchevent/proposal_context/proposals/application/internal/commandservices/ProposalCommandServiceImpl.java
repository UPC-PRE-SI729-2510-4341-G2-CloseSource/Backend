package com.matchevent.proposal_context.proposals.application.internal.commandservices;

import com.matchevent.proposal_context.proposals.domain.model.aggregates.Proposal;
import com.matchevent.proposal_context.proposals.domain.model.commands.CreateProposalCommand;
import com.matchevent.proposal_context.proposals.domain.model.commands.DeleteProposalCommand;
import com.matchevent.proposal_context.proposals.domain.model.commands.UpdateProposalCommand;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ProducerId;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ProposalId;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.RequestId;
import com.matchevent.proposal_context.proposals.domain.services.ProposalCommandService;
import com.matchevent.proposal_context.proposals.infrastructure.persistence.jpa.repositories.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProposalCommandServiceImpl implements ProposalCommandService {

    private final ProposalRepository proposalRepository;

    public ProposalCommandServiceImpl(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public Long handle(CreateProposalCommand command) {


        var proposal = new Proposal(command);

        try {
            proposalRepository.save(proposal);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving proposal: " + e.getMessage());
        }

        return proposal.getId(); // Esto debería mapear al ID Long del agregado
    }

    @Override
    public Optional<Proposal> handle(UpdateProposalCommand command) {
        var proposalId = new ProposalId(command.proposalId());


        if (proposalRepository.existsProposalByNameAndIdIsNot(command.name(), proposalId.value())) {
            throw new IllegalArgumentException("Proposal with name '" + command.name() + "' already exists");
        }

        var optionalProposal = proposalRepository.findById(proposalId.value());
        if (optionalProposal.isEmpty()) {
            throw new IllegalArgumentException("Proposal with ID " + proposalId.value() + " does not exist");
        }

        var proposalToUpdate = optionalProposal.get();

        proposalToUpdate.updateInformation(
                new RequestId(command.requestId()),
                new ProducerId(command.producerId()),
                command.name(),
                command.description(),
                command.offeredPrice(),
                command.submissionDate(),
                command.proposalStatus()
        );

        try {
            var updatedProposal = proposalRepository.save(proposalToUpdate);
            return Optional.of(updatedProposal);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating proposal: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProposalCommand command) {
        var proposalId = new ProposalId(command.proposalId());


        if (!proposalRepository.existsById(proposalId.value())) {
            throw new IllegalArgumentException("Proposal with ID " + proposalId.value() + " does not exist");
        }

        try {
            proposalRepository.deleteById(proposalId.value());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting proposal: " + e.getMessage());
        }
    }
}
