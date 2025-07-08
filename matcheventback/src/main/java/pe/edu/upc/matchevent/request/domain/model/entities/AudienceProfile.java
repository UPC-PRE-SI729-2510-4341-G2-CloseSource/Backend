package pe.edu.upc.matchevent.request.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.GenderTarget;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.InterestTag;
import pe.edu.upc.matchevent.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Entity
@Table(name = "audience_profiles")
public class AudienceProfile extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "age_range", nullable = false)
    private String ageRange;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_target", nullable = false)
    private GenderTarget genderTarget;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "audience_profile_interest_tags", joinColumns = @JoinColumn(name = "audience_profile_id"))
    @Column(name = "interest_tag")
    private List<InterestTag> interests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activation_request_id")
    private ActivationRequest activationRequest;

    public AudienceProfile() {}

    public AudienceProfile(String ageRange, GenderTarget genderTarget, List<InterestTag> interests) {
        this.ageRange = ageRange;
        this.genderTarget = genderTarget;
        this.interests = interests;
    }

    public void setActivationRequest(ActivationRequest activationRequest) {
        this.activationRequest = activationRequest;
    }
}
