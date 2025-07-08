package pe.edu.upc.matchevent.request.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.matchevent.request.domain.model.queries.GetActivationRequestByIdQuery;
import pe.edu.upc.matchevent.request.domain.model.queries.GetAllActivationRequestsQuery;
import pe.edu.upc.matchevent.request.domain.services.*;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.*;
import pe.edu.upc.matchevent.request.interfaces.rest.transform.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/activation-requests", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Activation Requests", description = "Activation Requests Management Endpoints")
public class ActivationRequestsController {

    private final ActivationRequestQueryService queryService;
    private final ActivationRequestCommandService commandService;
    private final ActivationRequestRepository repository;

    public ActivationRequestsController(
            ActivationRequestQueryService queryService,
            ActivationRequestCommandService commandService,
            ActivationRequestRepository repository) {
        this.queryService = queryService;
        this.commandService = commandService;
        this.repository = repository;
    }

    @PostMapping
    @Operation(
            summary = "Create a new activation request",
            description = "Registers a new activation request with basic details",
            operationId = "createActivationRequest",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful operation", content = @Content(schema = @Schema(implementation = ActivationRequestResource.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RuntimeException.class)))
            }
    )
    public ResponseEntity<ActivationRequestResource> createActivationRequest(@RequestBody CreateActivationRequestResource resource) {
        var command = CreateActivationRequestCommandFromResourceAssembler.toCommandFromResource(resource);
        var id = commandService.handle(command);
        if (id.equals(0L)) return ResponseEntity.badRequest().build();
        var optional = queryService.handle(new GetActivationRequestByIdQuery(id));
        return optional.map(ar -> new ResponseEntity<>(ActivationRequestResourceFromEntityAssembler.toResourceFromEntity(ar), HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get all activation requests",
            description = "Returns a list of all activation requests",
            operationId = "getAllActivationRequests",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ActivationRequestResource>> getAll() {
        var query = new GetAllActivationRequestsQuery();
        var entities = queryService.handle(query);
        var resources = entities.stream()
                .map(ActivationRequestResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(
            summary = "Get activation request by ID",
            description = "Returns the activation request corresponding to the given ID",
            operationId = "getActivationRequestById",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Activation request not found"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ActivationRequestResource> getById(@PathVariable Long id) {
        var optional = queryService.handle(new GetActivationRequestByIdQuery(id));
        return optional.map(value -> ResponseEntity.ok(ActivationRequestResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete activation request by ID",
            description = "Deletes an activation request by its unique identifier",
            operationId = "deleteActivationRequest",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful deletion"),
                    @ApiResponse(responseCode = "404", description = "Activation request not found")
            }
    )
    public ResponseEntity<Void> deleteActivationRequest(@PathVariable Long id) {
        var optional = queryService.handle(new GetActivationRequestByIdQuery(id));
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        repository.delete(optional.get());
        return ResponseEntity.ok().build();
    }
}
