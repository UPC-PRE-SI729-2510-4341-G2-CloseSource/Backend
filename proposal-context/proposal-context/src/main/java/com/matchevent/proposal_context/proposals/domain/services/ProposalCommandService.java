package com.matchevent.proposal_context.proposals.domain.services;

import com.matchevent.proposal_context.proposals.domain.model.aggregates.Proposal;
import com.matchevent.proposal_context.proposals.domain.model.commands.*;

import java.util.List;
import java.util.Optional;

public interface ProposalCommandService {
    Long handle(CreateProposalCommand command);
    Optional<Proposal> handle(UpdateProposalCommand command);
    void handle(DeleteProposalCommand command);
}