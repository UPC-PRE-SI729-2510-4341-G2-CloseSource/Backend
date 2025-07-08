package pe.edu.upc.matchevent.request.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.queries.GetActivationRequestByIdQuery;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestQueryService;

@Service
public class RequestContextFacade {

    private final ActivationRequestQueryService activationRequestQueryService;

    public RequestContextFacade(ActivationRequestQueryService activationRequestQueryService) {
        this.activationRequestQueryService = activationRequestQueryService;
    }

    public boolean isRequestOwnedByCompany(Long requestId, Long companyId) {
        var query = new GetActivationRequestByIdQuery(requestId);
        var requestOptional = activationRequestQueryService.handle(query);

        if (requestOptional.isEmpty()) return false;

        var request = requestOptional.get();
        return request.getCompanyId().equals(companyId);
    }
}