package pe.edu.upc.matchevent.request.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.matchevent.request.domain.model.commands.CreateActivationRequestCommand;
import pe.edu.upc.matchevent.request.domain.model.commands.UpdateActivationRequestCommand;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestCommandService;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestQueryService;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateActivationRequestResource;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.UpdateActivationRequestResource;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.ActivationRequestResource;
import pe.edu.upc.matchevent.request.interfaces.rest.transform.ActivationRequestResourceAssembler;

import java.util.List;

@Tag(name = "Activation Requests", description = "Activation Request Management Endpoints")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/activation_requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivationRequestsController {

    private final ActivationRequestCommandService commandService;
    private final ActivationRequestQueryService queryService;

    public ActivationRequestsController(
            ActivationRequestCommandService commandService,
            ActivationRequestQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(
            summary = "Create a new activation request",
            description = "Registers a new activation request in the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Activation request created successfully",
                            content = @Content(schema = @Schema(implementation = ActivationRequestResource.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content(schema = @Schema(implementation = RuntimeException.class)))
            }
    )
    @PostMapping
    public ResponseEntity<ActivationRequestResource> create(@RequestBody CreateActivationRequestResource resource) {
        var command = new CreateActivationRequestCommand(
                resource.companyId(),
                resource.eventTitle(),
                resource.eventDescription(),
                resource.location(),
                resource.startDate(),
                resource.endDate(),
                resource.status()
        );
        var result = commandService.handle(command);
        return ResponseEntity.ok(ActivationRequestResourceAssembler.toResourceFromEntity(result));
    }

    @Operation(
            summary = "Update an existing activation request by ID",
            description = "Updates the data of an activation request with the given ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Activation request updated successfully",
                            content = @Content(schema = @Schema(implementation = ActivationRequestResource.class))),
                    @ApiResponse(responseCode = "404", description = "Activation request not found",
                            content = @Content)
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ActivationRequestResource> update(
            @PathVariable Long id,
            @RequestBody UpdateActivationRequestResource resource
    ) {
        var command = new UpdateActivationRequestCommand(
                id,
                null,
                resource.eventTitle(),
                resource.eventDescription(),
                resource.location(),
                resource.startDate(),
                resource.endDate(),
                resource.status()
        );
        var result = commandService.handle(command);
        return ResponseEntity.ok(ActivationRequestResourceAssembler.toResourceFromEntity(result));
    }

    @Operation(
            summary = "Delete an activation request by ID",
            description = "Deletes the activation request associated with the specified ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Activation request deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Activation request not found")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commandService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get a list of all activation requests",
            description = "Returns all activation requests registered in the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of activation requests",
                            content = @Content(schema = @Schema(implementation = ActivationRequestResource.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<ActivationRequestResource>> getAll() {
        var result = queryService.getAll();
        return ResponseEntity.ok(
                result.stream().map(ActivationRequestResourceAssembler::toResourceFromEntity).toList()
        );
    }

    @Operation(
            summary = "Get a specific activation request by ID",
            description = "Returns the activation request identified by the given ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Activation request found",
                            content = @Content(schema = @Schema(implementation = ActivationRequestResource.class))),
                    @ApiResponse(responseCode = "404", description = "Activation request not found",
                            content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ActivationRequestResource> getById(@PathVariable Long id) {
        ActivationRequest result = queryService.getById(id);
        return ResponseEntity.ok(ActivationRequestResourceAssembler.toResourceFromEntity(result));
    }
}
