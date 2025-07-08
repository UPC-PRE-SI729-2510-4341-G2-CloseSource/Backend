package pe.edu.upc.matchevent.request.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.commands.SeedRequestStatusesCommand;
import pe.edu.upc.matchevent.request.domain.services.RequestStatusCommandService;

import java.sql.Timestamp;

@Service
public class RequestReadyEventHandler {

    private final RequestStatusCommandService requestStatusCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestReadyEventHandler.class);

    public RequestReadyEventHandler(RequestStatusCommandService requestStatusCommandService) {
        this.requestStatusCommandService = requestStatusCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if request statuses seeding is needed for {} at {}", applicationName, currentTimestamp());

        requestStatusCommandService.handle(new SeedRequestStatusesCommand());

        LOGGER.info("Request statuses seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
