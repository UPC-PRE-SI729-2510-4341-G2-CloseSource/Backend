package pe.edu.upc.matchevent.request.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.matchevent.request.application.internal.commandservices.ActivationRequestCommandServiceImpl;
import pe.edu.upc.matchevent.request.domain.model.commands.CreateActivationLocationCommand;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateActivationLocationResource;
import pe.edu.upc.matchevent.request.interfaces.rest.transform.CreateActivationLocationCommandFromResourceAssembler;

@RestController
@RequestMapping("/api/v1/activation-requests/{activationRequestId}/location")
@Tag(name = "Activation Request Locations", description = "Location Management for Activation Requests")
public class ActivationRequestLocationsController {

    private final ActivationRequestCommandServiceImpl commandService;

    public ActivationRequestLocationsController(ActivationRequestCommandServiceImpl commandService) {
        this.commandService = commandService;
    }

    @PutMapping
    @Operation(
            summary = "Update location of activation request",
            operationId = "updateLocation",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful update",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(schema = @Schema(implementation = RuntimeException.class))
                    )
            }
    )
    public ResponseEntity<Void> updateLocation(
            @PathVariable Long activationRequestId,
            @RequestBody CreateActivationLocationResource resource
    ) {
        CreateActivationLocationCommand command = CreateActivationLocationCommandFromResourceAssembler.toCommandFromResource(resource);
        commandService.handle(activationRequestId, command);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(
            summary = "Add location to activation request",
            operationId = "addLocation",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful addition",
                            content = @Content(schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(schema = @Schema(implementation = RuntimeException.class)))
            }
    )
    public ResponseEntity<Void> addLocation(
            @PathVariable Long activationRequestId,
            @RequestBody CreateActivationLocationResource resource
    ) {
        var command = CreateActivationLocationCommandFromResourceAssembler.toCommandFromResource(resource);
        commandService.handle(activationRequestId, command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
