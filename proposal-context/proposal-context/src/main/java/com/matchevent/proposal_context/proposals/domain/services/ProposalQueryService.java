package com.matchevent.proposal_context.proposals.domain.services;

import com.matchevent.proposal_context.proposals.domain.model.aggregates.Proposal;
import com.matchevent.proposal_context.proposals.domain.model.queries.*;

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
    List<Proposal> handle(GetProposalByServiceIdQuery query);
//    Optional<Proposal> handle(GetProposalByRequestIdQuery query);
//    Optional<Proposal> handle(GetProposalByProducerIdQuery query);
    //    Optional<Proposal> handle(GetProposalByServiceIdQuery query);

}