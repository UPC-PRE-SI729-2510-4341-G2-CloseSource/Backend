package com.matchevent.proposal_context.proposals.domain.model.queries;

import com.matchevent.proposal_context.proposals.domain.model.valueobjects.RequestId;

import java.util.UUID;

//Para obtener todas las propuestas relacionadas a un Request específico.
// Muy útil para mostrar las opciones a un cliente/empresa.
public record GetProposalByRequestIdQuery(Long requestId) {}
