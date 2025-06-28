package pe.edu.upc.matchevent.request.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.matchevent.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "activation_requests")
public class ActivationRequest extends AuditableAbstractAggregateRoot<ActivationRequest> {

    @Getter
    @NotNull
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Getter
    @NotBlank
    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    @Getter
    @NotBlank
    @Column(name = "event_description", nullable = false, columnDefinition = "TEXT")
    private String eventDescription;

    @Getter
    @NotBlank
    @Column(name = "location", nullable = false)
    private String location;

    @Getter
    @NotNull
    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Getter
    @NotNull
    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Getter
    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;

    // Constructors
    public ActivationRequest() {}

    public ActivationRequest(Long companyId, String title, String description, String location, String startDate, String endDate, String status) {
        this.companyId = companyId;
        this.eventTitle = title;
        this.eventDescription = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public void updateBasicInfo(String title, String description, String location, String startDate, String endDate, String status) {
        this.eventTitle = title;
        this.eventDescription = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {return this.id;}
    public Long getCompanyId() { return this.companyId; }
    public String getEventTitle() { return this.eventTitle; }
    public String getEventDescription() { return this.eventDescription; }
    public String getLocation() { return this.location; }
    public String getStartDate() { return this.startDate; }
    public String getEndDate() { return this.endDate; }
    public String getStatus() { return this.status; }

}
