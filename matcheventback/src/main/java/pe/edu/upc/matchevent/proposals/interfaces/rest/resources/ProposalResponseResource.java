package pe.edu.upc.matchevent.proposals.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ProposalResponseResource(
        Long proposalId,
        Long requestId,
        Long producerId,
        String name,
        ActivationPlanResource activationPlan,
        Double offeredPrice,
        LocalDateTime submissionDate,
        String proposalStatus
) { }
