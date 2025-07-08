package pe.edu.upc.matchevent.proposals.interfaces.rest.transform;

import pe.edu.upc.matchevent.proposals.domain.model.commands.CreateProposalCommand;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.CreateProposalResource;

public class CreateProposalCommandFromResourceAssembler {
    public static CreateProposalCommand toCommand(CreateProposalResource resource) {
        return new CreateProposalCommand(
                resource.requestId(),
                resource.producerId(),
                resource.name(),
                resource.activationPlan().objective(),
                resource.activationPlan().concept(),
                resource.activationPlan().branding(),
                resource.activationPlan().activation(),
                resource.activationPlan().resources(),
                resource.activationPlan().kpi(),
                resource.offeredPrice(),
                resource.submissionDate(),
                resource.proposalStatus()
        );
    }
}

