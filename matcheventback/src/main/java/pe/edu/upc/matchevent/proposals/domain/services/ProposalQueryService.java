package pe.edu.upc.matchevent.proposals.domain.services;

import pe.edu.upc.matchevent.proposals.domain.model.aggregates.Proposal;
import pe.edu.upc.matchevent.proposals.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProposalQueryService {
    List<Proposal> handle(GetAllProposalsQuery query);
    List<Proposal> handle(GetProposalByDateRangeQuery query);
    List<Proposal> handle(GetProposalByNameQuery query);
    List<Proposal> handle(GetProposalByStatusQuery query);
    Optional<Proposal> handle(GetProposalByIdQuery query);
    List<Proposal> handle(GetProposalByProducerIdQuery query);
    List<Proposal> handle(GetProposalByRequestIdQuery query);
}