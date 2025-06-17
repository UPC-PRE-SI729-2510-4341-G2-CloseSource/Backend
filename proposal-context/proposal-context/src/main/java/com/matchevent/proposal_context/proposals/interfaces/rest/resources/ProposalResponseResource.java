package com.matchevent.proposal_context.proposals.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ProposalResponseResource(
        Long proposalId,
        Long requestId,
        Long producerId,
        Long serviceId,
        String name,
        String description,
        Double offeredPrice,
        LocalDateTime submissionDate,
        String proposalStatus
) {}
