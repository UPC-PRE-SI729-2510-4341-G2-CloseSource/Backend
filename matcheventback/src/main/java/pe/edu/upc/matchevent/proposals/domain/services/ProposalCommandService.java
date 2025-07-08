package pe.edu.upc.matchevent.proposals.domain.services;

import pe.edu.upc.matchevent.proposals.domain.model.aggregates.Proposal;
import pe.edu.upc.matchevent.proposals.domain.model.commands.*;

import java.util.List;
import java.util.Optional;

public interface ProposalCommandService {
    Long handle(CreateProposalCommand command);
    Optional<Proposal> handle(UpdateProposalCommand command);
    void handle(DeleteProposalCommand command);
}