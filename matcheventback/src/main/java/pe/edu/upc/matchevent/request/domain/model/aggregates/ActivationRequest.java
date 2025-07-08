package pe.edu.upc.matchevent.request.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.matchevent.request.domain.model.entities.ActivationLocation;
import pe.edu.upc.matchevent.request.domain.model.entities.AudienceProfile;
import pe.edu.upc.matchevent.request.domain.model.entities.MaterialRequirement;
import pe.edu.upc.matchevent.request.domain.model.entities.RequestStatus;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.CompanyId;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.EventDateRange;
import pe.edu.upc.matchevent.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "activation_requests")
public class ActivationRequest extends AuditableAbstractAggregateRoot<ActivationRequest> {

    @NotNull
    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "companyId", column = @Column(name = "company_id", nullable = false))
    })
    private CompanyId companyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private RequestStatus status;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private ActivationLocation location;

    @OneToMany(mappedBy = "activationRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialRequirement> materials;

    @Setter
    @Embedded
    private EventDateRange eventDateRange;

    @OneToMany(mappedBy = "activationRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AudienceProfile> audienceProfiles;

    public ActivationRequest() {}

    public ActivationRequest(String title, String description, CompanyId companyId, RequestStatus status,
                             ActivationLocation location, List<MaterialRequirement> materials,
                             EventDateRange eventDateRange, List<AudienceProfile> audienceProfiles) {
        this.title = title;
        this.description = description;
        this.companyId = companyId;
        this.status = status;
        this.location = location;
        this.materials = materials;
        this.eventDateRange = eventDateRange;
        this.audienceProfiles = audienceProfiles;

        this.materials = (materials != null) ? materials : new ArrayList<>();
        this.audienceProfiles = (audienceProfiles != null) ? audienceProfiles : new ArrayList<>();

        this.materials.forEach(material -> material.setActivationRequest(this));
        this.audienceProfiles.forEach(profile -> profile.setActivationRequest(this));

    }

}
