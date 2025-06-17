package com.matchevent.proposal_context.proposals.domain.model.commands;

import com.matchevent.proposal_context.proposals.domain.model.valueobjects.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateProposalCommand(
        Long requestId,
        Long producerId,
        Long serviceId,
        String name,
        String description,
        Double offeredPrice,
        LocalDateTime submissionDate,
        ProposalStatus proposalStatus
) {}
