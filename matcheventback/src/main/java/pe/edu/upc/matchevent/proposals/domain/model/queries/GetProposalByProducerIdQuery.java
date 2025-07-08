package pe.edu.upc.matchevent.proposals.domain.model.queries;

//Para que un productor vea todas sus propuestas, filtrar por quién las creó.


import java.util.UUID;

public record GetProposalByProducerIdQuery(Long producerId) {
}
