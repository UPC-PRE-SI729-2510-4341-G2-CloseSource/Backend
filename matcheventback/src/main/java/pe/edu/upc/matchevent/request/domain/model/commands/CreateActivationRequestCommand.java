package pe.edu.upc.matchevent.request.domain.model.commands;

import pe.edu.upc.matchevent.request.domain.model.valueobjects.CompanyId;

public record CreateActivationRequestCommand(
        String title,
        String description,
        CompanyId companyId,
        String status
) {
}
