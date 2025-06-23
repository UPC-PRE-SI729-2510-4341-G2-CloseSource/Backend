package com.matchevent.proposal_context.proposals.interfaces.rest.transform;

import com.matchevent.proposal_context.proposals.domain.model.commands.CreateProposalCommand;
import com.matchevent.proposal_context.proposals.interfaces.rest.resources.CreateProposalResource;

public class CreateProposalCommandFromResourceAssembler {
    public static CreateProposalCommand toCommand(CreateProposalResource resource) {
        return new CreateProposalCommand(
                resource.requestId(),
                resource.producerId(),
                resource.name(),
                resource.description(),
                resource.offeredPrice(),
                resource.submissionDate(),
                resource.proposalStatus()
        );
    }
}
