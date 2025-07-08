package pe.edu.upc.matchevent.request.interfaces.rest.resources;

import pe.edu.upc.matchevent.request.domain.model.valueobjects.CompanyId;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.EventDateRange;

import java.util.List;

public record ActivationRequestResource(
        Long id,
        String title,
        String description,
        CompanyId companyId,
        String status,
        ActivationLocationResource location,
        List<MaterialRequirementResource> materials,
        EventDateRange eventDateRange,
        List<AudienceProfileResource> audienceProfiles
) {}
