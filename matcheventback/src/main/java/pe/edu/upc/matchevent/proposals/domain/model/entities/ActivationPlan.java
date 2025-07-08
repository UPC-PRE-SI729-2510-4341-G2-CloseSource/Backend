package pe.edu.upc.matchevent.proposals.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

import static org.apache.commons.lang3.Validate.notBlank;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@Entity
@Table(name = "activation_plans")
@NoArgsConstructor
public class ActivationPlan {

    @Id
    private UUID id;

    private String objective;
    private String concept;
    private String branding;
    private String activation;
    private String resources;
    private String kpi;

    public ActivationPlan(String objective, String concept, String branding,
                          String activation, String resources, String kpi) {
        this.id = UUID.randomUUID();
        this.objective = objective;
        this.concept = concept;
        this.branding = branding;
        this.activation = activation;
        this.resources = resources;
        this.kpi = kpi;
    }

    // Métodos opcionales: update, validaciones, etc.
}
