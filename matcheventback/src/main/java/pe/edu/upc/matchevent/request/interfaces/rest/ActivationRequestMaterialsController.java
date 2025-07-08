package pe.edu.upc.matchevent.request.interfaces.rest;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.matchevent.request.application.internal.commandservices.ActivationRequestCommandServiceImpl;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.entities.MaterialRequirement;
import pe.edu.upc.matchevent.request.domain.model.queries.GetActivationRequestByIdQuery;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestQueryService;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;
import pe.edu.upc.matchevent.request.interfaces.rest.resources.CreateMaterialRequirementResource;
import pe.edu.upc.matchevent.request.interfaces.rest.transform.CreateMaterialRequirementCommandFromResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activation-requests/{id}/materials")
@Tag(name = "Activation Request Materials", description = "Material Requirements Management")
public class ActivationRequestMaterialsController {

    private final ActivationRequestQueryService queryService;
    private final ActivationRequestRepository repository;
    private final ActivationRequestCommandServiceImpl commandService;

    public ActivationRequestMaterialsController(ActivationRequestQueryService queryService,
                                                ActivationRequestRepository repository,
                                                ActivationRequestCommandServiceImpl commandService) {
        this.queryService = queryService;
        this.repository = repository;
        this.commandService = commandService;
    }

    @PostMapping
    @Operation(
            summary = "Add material requirement to activation request",
            operationId = "addMaterialRequirement",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful addition"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<Void> addMaterial(@PathVariable Long id, @RequestBody CreateMaterialRequirementResource resource) {
        var command = CreateMaterialRequirementCommandFromResourceAssembler.toCommandFromResource(resource);
        commandService.handle(id, command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(
            summary = "Replace all materials of activation request",
            operationId = "replaceAllMaterials",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful update"),
                    @ApiResponse(responseCode = "404", description = "Activation request not found")
            }
    )
    public ResponseEntity<Void> replaceAllMaterials(@PathVariable Long id, @RequestBody List<CreateMaterialRequirementResource> resources) {
        var optional = queryService.handle(new GetActivationRequestByIdQuery(id));
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        ActivationRequest ar = optional.get();
        ar.getMaterials().clear();

        for (var res : resources) {
            var material = new MaterialRequirement(
                    res.name(), res.quantity(), res.specification(), res.providedByCompany()
            );
            material.setActivationRequest(ar);
            ar.getMaterials().add(material);
        }

        repository.save(ar);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Delete all materials from activation request",
            operationId = "deleteAllMaterials",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful deletion"),
                    @ApiResponse(responseCode = "404", description = "Activation request not found")
            }
    )
    public ResponseEntity<Void> deleteAllMaterials(@PathVariable Long id) {
        var optional = queryService.handle(new GetActivationRequestByIdQuery(id));
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        ActivationRequest ar = optional.get();
        ar.getMaterials().clear();
        repository.save(ar);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{materialId}")
    @Operation(
            summary = "Delete material by ID from activation request",
            operationId = "deleteMaterialById",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful deletion"),
                    @ApiResponse(responseCode = "404", description = "Activation request or material not found")
            }
    )
    public ResponseEntity<Void> deleteMaterialById(@PathVariable Long id, @PathVariable Long materialId) {
        var optional = queryService.handle(new GetActivationRequestByIdQuery(id));
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        ActivationRequest ar = optional.get();
        boolean removed = ar.getMaterials().removeIf(m -> m.getId().equals(materialId));
        if (!removed) return ResponseEntity.notFound().build();

        repository.save(ar);
        return ResponseEntity.ok().build();
    }
}
