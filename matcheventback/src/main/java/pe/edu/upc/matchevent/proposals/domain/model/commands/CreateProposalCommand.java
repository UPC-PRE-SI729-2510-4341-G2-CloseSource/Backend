package pe.edu.upc.matchevent.proposals.domain.model.commands;

import pe.edu.upc.matchevent.proposals.domain.model.entities.ActivationPlan;
import pe.edu.upc.matchevent.proposals.domain.model.valueobjects.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateProposalCommand(
        Long requestId,
        Long producerId,
        String name,
        String objective,
        String concept,
        String branding,
        String activation,
        String resources,
        String kpi,
        Double offeredPrice,
        LocalDateTime submissionDate,
        ProposalStatus proposalStatus
) {}
