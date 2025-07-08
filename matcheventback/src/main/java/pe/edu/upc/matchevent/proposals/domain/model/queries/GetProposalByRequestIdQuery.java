package pe.edu.upc.matchevent.proposals.domain.model.queries;


import java.util.UUID;

//Para obtener todas las propuestas relacionadas a un Request específico.
// Muy útil para mostrar las opciones a un cliente/empresa.
public record GetProposalByRequestIdQuery(Long requestId) {}
