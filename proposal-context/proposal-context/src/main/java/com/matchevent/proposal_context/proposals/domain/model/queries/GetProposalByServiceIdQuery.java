package com.matchevent.proposal_context.proposals.domain.model.queries;

import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ServiceId;

import java.util.UUID;

// Si alguna vez quieres filtrar propuestas que usen cierto servicio.
public record GetProposalByServiceIdQuery(Long serviceId) {}
