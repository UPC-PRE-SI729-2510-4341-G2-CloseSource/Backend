package pe.edu.upc.matchevent.request.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.commands.SeedRequestStatusesCommand;
import pe.edu.upc.matchevent.request.domain.model.entities.RequestStatus;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.RequestStatuses;
import pe.edu.upc.matchevent.request.domain.services.RequestStatusCommandService;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.RequestStatusRepository;

import java.util.Arrays;

@Service
public class RequestStatusCommandServiceImpl implements RequestStatusCommandService {

    private final RequestStatusRepository requestStatusRepository;

    public RequestStatusCommandServiceImpl(RequestStatusRepository requestStatusRepository) {
        this.requestStatusRepository = requestStatusRepository;
    }

    @Override
    public void handle(SeedRequestStatusesCommand command) {
        Arrays.stream(RequestStatuses.values())
                .forEach(requestStatus -> {
                    if (!requestStatusRepository.existsByName(requestStatus)) {
                        requestStatusRepository.save(new RequestStatus(RequestStatuses.valueOf(requestStatus.name())));
                    }
                });
    }
}
