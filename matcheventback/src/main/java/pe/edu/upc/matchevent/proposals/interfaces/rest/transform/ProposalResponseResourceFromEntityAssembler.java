package pe.edu.upc.matchevent.proposals.interfaces.rest.transform;

import pe.edu.upc.matchevent.proposals.domain.model.aggregates.Proposal;
import pe.edu.upc.matchevent.proposals.domain.model.entities.ActivationPlan;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.ActivationPlanResource;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.ProposalResponseResource;

public class ProposalResponseResourceFromEntityAssembler {

    public static ProposalResponseResource toResourceFromEntity(Proposal proposal) {
        ActivationPlan plan = proposal.getActivationPlan();

        ActivationPlanResource activationPlanResource = new ActivationPlanResource(
                plan.getObjective(),
                plan.getConcept(),
                plan.getBranding(),
                plan.getActivation(),
                plan.getResources(),
                plan.getKpi()
        );


        return new ProposalResponseResource(
                proposal.getId(),
                proposal.getRequestId(),
                proposal.getProducerId(),
                proposal.getName(),
                activationPlanResource,
                proposal.getOfferedPrice(),
                proposal.getSubmissionDate(),
                proposal.getProposalStatus().name()
        );
    }
}
