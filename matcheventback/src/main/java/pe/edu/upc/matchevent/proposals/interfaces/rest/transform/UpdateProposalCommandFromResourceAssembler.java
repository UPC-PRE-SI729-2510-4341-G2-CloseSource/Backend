package pe.edu.upc.matchevent.proposals.interfaces.rest.transform;

import pe.edu.upc.matchevent.proposals.domain.model.commands.UpdateProposalCommand;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.UpdateProposalResource;

import pe.edu.upc.matchevent.proposals.domain.model.commands.UpdateProposalCommand;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.UpdateProposalResource;

public class UpdateProposalCommandFromResourceAssembler {
    public static UpdateProposalCommand toCommand(Long proposalId, UpdateProposalResource resource) {
        return new UpdateProposalCommand(
                proposalId,
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
