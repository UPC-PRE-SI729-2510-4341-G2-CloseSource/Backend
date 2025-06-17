package com.matchevent.proposal_context.proposals.interfaces.rest.resources;

import java.time.LocalDateTime;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ProposalStatus;

public record CreateProposalResource(
        Long requestId,
        Long producerId,
        Long serviceId,
        String name,
        String description,
        Double offeredPrice,
        LocalDateTime submissionDate,
        ProposalStatus proposalStatus
) {}
