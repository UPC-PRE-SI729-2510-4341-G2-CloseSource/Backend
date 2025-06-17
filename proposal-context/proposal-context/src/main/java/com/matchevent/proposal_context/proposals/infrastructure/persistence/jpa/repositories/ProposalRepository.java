package com.matchevent.proposal_context.proposals.infrastructure.persistence.jpa.repositories;

import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ProducerId;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ProposalStatus;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.RequestId;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.ServiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.matchevent.proposal_context.proposals.domain.model.aggregates.Proposal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>{
    boolean existsProposalByName(String name);
    boolean existsProposalByNameAndIdIsNot(String name, Long id);
    List<Proposal> findProposalByName(String name);
    List<Proposal> findBySubmissionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Proposal> findByProposalStatus(ProposalStatus status);
    List<Proposal> findByProducerId(ProducerId producerId);
    List<Proposal> findByRequestId(RequestId requestId);
    List<Proposal> findByServiceId(ServiceId serviceId);

}
