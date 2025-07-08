package pe.edu.upc.matchevent.request.interfaces.rest;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.queries.GetActivationRequestByIdQuery;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestQueryService;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.EventDateRange;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateEventDateRangeResource;

@RestController
@RequestMapping("/api/v1/activation-requests/{id}/event-date-range")
@Tag(name = "Activation Request Event Dates", description = "Event Date Range Management")
public class ActivationRequestEventDateRangeController {

    private final ActivationRequestQueryService queryService;
    private final ActivationRequestRepository repository;

    public ActivationRequestEventDateRangeController(ActivationRequestQueryService queryService, ActivationRequestRepository repository) {
        this.queryService = queryService;
        this.repository = repository;
    }

    @PutMapping
    @Operation(
            summary = "Update event date range",
            operationId = "updateEventDateRange",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful update"),
                    @ApiResponse(responseCode = "404", description = "Activation request not found")
            }
    )
    public ResponseEntity<Void> updateEventDateRange(@PathVariable Long id, @RequestBody CreateEventDateRangeResource resource) {
        var optional = queryService.handle(new GetActivationRequestByIdQuery(id));
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        ActivationRequest ar = optional.get();
        ar.setEventDateRange(new EventDateRange(resource.startDate(), resource.endDate()));
        repository.save(ar);
        return ResponseEntity.ok().build();
    }


}
