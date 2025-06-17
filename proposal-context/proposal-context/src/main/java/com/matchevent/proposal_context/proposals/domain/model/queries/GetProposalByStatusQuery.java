package com.matchevent.proposal_context.proposals.domain.model.queries;

import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ProposalStatus;

//Para listar propuestas filtrando por su estado (pending, accepted, rejected).
// Clave para dashboards o flujos de trabajo.
public record GetProposalByStatusQuery(ProposalStatus proposalStatus) {
}
