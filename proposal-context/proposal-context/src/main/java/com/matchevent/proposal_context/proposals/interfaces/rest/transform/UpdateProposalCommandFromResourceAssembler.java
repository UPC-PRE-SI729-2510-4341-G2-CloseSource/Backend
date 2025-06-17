package com.matchevent.proposal_context.proposals.interfaces.rest.transform;

import com.matchevent.proposal_context.proposals.domain.model.commands.UpdateProposalCommand;
import com.matchevent.proposal_context.proposals.interfaces.rest.resources.UpdateProposalResource;

public class UpdateProposalCommandFromResourceAssembler {
    public static UpdateProposalCommand toCommand(Long proposalId, UpdateProposalResource resource) {
        return new UpdateProposalCommand(
                proposalId,
                resource.requestId(),
                resource.producerId(),
                resource.serviceId(),
                resource.name(),
                resource.description(),
                resource.offeredPrice(),
                resource.submissionDate(),
                resource.proposalStatus()
        );
    }
}
