package com.matchevent.proposal_context.proposals.domain.model.aggregates;

import com.matchevent.proposal_context.proposals.domain.model.commands.CreateProposalCommand;
import com.matchevent.proposal_context.proposals.domain.model.valueobjects.*;
import com.matchevent.proposal_context.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "proposals")
@NoArgsConstructor
public class Proposal extends AuditableAbstractAggregateRoot<Proposal> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id", nullable = false)
    private Long proposalId;


    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "request_id", nullable = false))
    private RequestId requestId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "producer_id", nullable = false))
    private ProducerId producerId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private Double offeredPrice;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus proposalStatus;

    public Proposal(ProposalId proposalId, RequestId requestId, ProducerId producerId
                   ,String name, String description, Double offeredPrice,
                    LocalDateTime submissionDate, ProposalStatus proposalStatus) {
        this.requestId = requestId;
        this.producerId = producerId;
        this.name = name;
        this.description = description;
        this.offeredPrice = offeredPrice;
        this.submissionDate = submissionDate;
        this.proposalStatus = proposalStatus;
    }

    // Métodos de negocio opcionales
    public void accept() {
        this.proposalStatus = ProposalStatus.ACCEPTED;
    }

    public void reject() {
        this.proposalStatus = ProposalStatus.REJECTED;
    }

    public Long getId() {
        return this.proposalId;
    }

    public Proposal(CreateProposalCommand command) {
        this.requestId = new RequestId(command.requestId());
        this.producerId = new ProducerId(command.producerId());
        this.name = command.name();
        this.description = command.description();
        this.offeredPrice = command.offeredPrice();
        this.submissionDate = command.submissionDate();
        this.proposalStatus = ProposalStatus.PENDING;
    }

    public void updateInformation(RequestId requestId, ProducerId producerId,
                                  String name, String description, Double price,
                                  LocalDateTime submissionDate, ProposalStatus status) {
        this.requestId = requestId;
        this.producerId = producerId;
        this.name = name;
        this.description = description;
        this.offeredPrice = price;
        this.submissionDate = submissionDate;
        this.proposalStatus = status;
    }
}
