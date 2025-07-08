package pe.edu.upc.matchevent.proposals.domain.model.queries;

import java.time.LocalDateTime;

//Encontrar propuesta en un rango de fecha
public record GetProposalByDateRangeQuery(LocalDateTime startDate, LocalDateTime endDate) {
}
