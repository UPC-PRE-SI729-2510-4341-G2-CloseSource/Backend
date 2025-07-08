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
import pe.edu.upc.matchevent.request.domain.model.commands.CreateAudienceProfileCommand;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateAudienceProfileResource;
import pe.edu.upc.matchevent.request.interfaces.rest.transform.CreateAudienceProfileCommandFromResourceAssembler;

@RestController
@RequestMapping("/api/v1/activation-requests/{activationRequestId}/audience-profiles")
@Tag(name = "Activation Request Audience Profiles", description = "Audience Profiles Management")
public class ActivationRequestAudienceProfilesController {

    private final ActivationRequestCommandServiceImpl commandService;

    public ActivationRequestAudienceProfilesController(ActivationRequestCommandServiceImpl commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    @Operation(
            summary = "Add audience profile",
            description = "Registers a new audience profile under the specified activation request",
            operationId = "addAudienceProfile",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful addition",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
    public ResponseEntity<Void> addAudienceProfile(
            @PathVariable Long activationRequestId,
            @RequestBody CreateAudienceProfileResource resource
    ) {
        CreateAudienceProfileCommand command = CreateAudienceProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        commandService.handle(activationRequestId, command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
