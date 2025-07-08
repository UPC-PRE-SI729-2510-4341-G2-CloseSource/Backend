package pe.edu.upc.matchevent.proposals.domain.model.aggregates;

import lombok.Setter;
import pe.edu.upc.matchevent.iam.domain.model.aggregates.User;
import pe.edu.upc.matchevent.proposals.domain.model.commands.CreateProposalCommand;
import pe.edu.upc.matchevent.proposals.domain.model.entities.ActivationPlan;
import pe.edu.upc.matchevent.proposals.domain.model.valueobjects.*;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "proposals")
@NoArgsConstructor
public class Proposal extends AuditableAbstractAggregateRoot<Proposal>{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activation_request_id", nullable = false)
    private ActivationRequest requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id", referencedColumnName = "id", nullable = false)
    private User producerId;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "activation_plan_id", referencedColumnName = "id")
    private ActivationPlan activationPlan;

    @Column(nullable = false)
    private Double offeredPrice;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus proposalStatus;



    public Proposal(ActivationRequest requestId, User producerId
                   ,String name, ActivationPlan activationPlan, Double offeredPrice,
                    LocalDateTime submissionDate, ProposalStatus proposalStatus) {
        this.requestId = requestId;
        this.producerId = producerId;
        this.name = name;
        this.activationPlan = activationPlan ;
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

    public Long getProducerId() {
        return this.producerId != null ? this.producerId.getId() : null;
    }

    public Long getRequestId() {
        return this.requestId != null ? this.requestId.getId() : null;
    }



    public Proposal(CreateProposalCommand command) {
        this.requestId = requestId;
        this.producerId = producerId;
        this.name = command.name();
        this.activationPlan = new ActivationPlan(
                command.objective(),
                command.concept(),
                command.branding(),
                command.activation(),
                command.resources(),
                command.kpi()
        );
        this.offeredPrice = command.offeredPrice();
        this.submissionDate = command.submissionDate();
        this.proposalStatus = ProposalStatus.PENDING;
    }

    public void updateInformation(ActivationRequest requestId, User producerId,
                                  String name, ActivationPlan activationPlan, Double price,
                                  LocalDateTime submissionDate, ProposalStatus status) {
        this.requestId = requestId;
        this.producerId = producerId;
        this.name = name;
        this.activationPlan = activationPlan;
        this.offeredPrice = price;
        this.submissionDate = submissionDate;
        this.proposalStatus = status;
    }
}
