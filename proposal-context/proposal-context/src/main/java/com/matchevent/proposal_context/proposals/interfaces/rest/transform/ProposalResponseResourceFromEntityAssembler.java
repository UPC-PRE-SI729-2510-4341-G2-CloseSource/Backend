package com.matchevent.proposal_context.proposals.interfaces.rest.transform;

import com.matchevent.proposal_context.proposals.domain.model.aggregates.Proposal;
import com.matchevent.proposal_context.proposals.interfaces.rest.resources.ProposalResponseResource;

public class ProposalResponseResourceFromEntityAssembler {

    public static ProposalResponseResource toResourceFromEntity(Proposal proposal) {
        return new ProposalResponseResource(
                proposal.getProposalId(),
                proposal.getRequestId().value(),
                proposal.getProducerId().value(),
                proposal.getName(),
                proposal.getDescription(),
                proposal.getOfferedPrice(),
                proposal.getSubmissionDate(),
                proposal.getProposalStatus().name() // Si ProposalStatus es enum, lo paso a String
        );
    }
}
