package pe.edu.upc.matchevent.request.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.shared.domain.model.entities.AuditableModel;

@Getter
@Entity
@Table(name = "material_requirements")
public class MaterialRequirement extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "specification")
    private String specification;

    @Column(name = "provided_by_company", nullable = false)
    private boolean providedByCompany;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activation_request_id")
    private ActivationRequest activationRequest;

    public MaterialRequirement() {}

    public MaterialRequirement(String name, int quantity, String specification, boolean providedByCompany) {
        this.name = name;
        this.quantity = quantity;
        this.specification = specification;
        this.providedByCompany = providedByCompany;
    }

}
