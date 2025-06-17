package com.matchevent.proposal_context.proposals.domain.model.queries;

//Para que un productor vea todas sus propuestas, filtrar por quién las creó.

import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ProducerId;

import java.util.UUID;

public record GetProposalByProducerIdQuery(Long producerId) {
}
