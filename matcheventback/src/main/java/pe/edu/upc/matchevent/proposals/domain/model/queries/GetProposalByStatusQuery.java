package pe.edu.upc.matchevent.proposals.domain.model.queries;

import pe.edu.upc.matchevent.proposals.domain.model.valueobjects.ProposalStatus;

//Para listar propuestas filtrando por su estado (pending, accepted, rejected).
// Clave para dashboards o flujos de trabajo.
public record GetProposalByStatusQuery(ProposalStatus proposalStatus) {
}
