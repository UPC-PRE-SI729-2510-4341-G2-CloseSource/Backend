package pe.edu.upc.matchevent.proposals.infrastructure.persistence.jpa.repositories;
import pe.edu.upc.matchevent.iam.domain.model.aggregates.User;
import pe.edu.upc.matchevent.proposals.domain.model.valueobjects.ProposalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.matchevent.proposals.domain.model.aggregates.Proposal;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>{
    boolean existsProposalByName(String name);
    boolean existsProposalByNameAndIdIsNot(String name, Long id);
    List<Proposal> findProposalByName(String name);
    List<Proposal> findBySubmissionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Proposal> findByProposalStatus(ProposalStatus status);

    List<Proposal> findByProducerId(User producerId);
    List<Proposal> findByRequestId(ActivationRequest requestId);

}
