package pe.edu.upc.matchevent.request.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CompanyId(Long companyId) {
    public CompanyId {
        if (companyId == null || companyId < 0) {
            throw new IllegalArgumentException("Company id cannot be null or negative");
        }
    }

    public CompanyId() {
        this(0L);
    }
}
