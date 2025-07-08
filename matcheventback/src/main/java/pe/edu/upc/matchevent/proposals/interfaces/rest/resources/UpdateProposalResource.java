package pe.edu.upc.matchevent.proposals.interfaces.rest.resources;

import java.time.LocalDateTime;
import pe.edu.upc.matchevent.proposals.domain.model.valueobjects.ProposalStatus;

public record UpdateProposalResource(
        Long proposalId,
        Long requestId,
        Long producerId,
        String name,
        ActivationPlanResource activationPlan,
        Double offeredPrice,
        LocalDateTime submissionDate,
        ProposalStatus proposalStatus
) {}
