package pe.edu.upc.matchevent.proposals.interfaces.rest;

import pe.edu.upc.matchevent.proposals.domain.model.commands.*;
import pe.edu.upc.matchevent.proposals.domain.model.queries.*;
import pe.edu.upc.matchevent.proposals.domain.model.aggregates.Proposal;
import pe.edu.upc.matchevent.proposals.domain.services.ProposalCommandService;
import pe.edu.upc.matchevent.proposals.domain.services.ProposalQueryService;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.CreateProposalResource;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.ProposalResponseResource;
import pe.edu.upc.matchevent.proposals.interfaces.rest.resources.UpdateProposalResource;
import pe.edu.upc.matchevent.proposals.interfaces.rest.transform.CreateProposalCommandFromResourceAssembler;
import pe.edu.upc.matchevent.proposals.interfaces.rest.transform.ProposalResponseResourceFromEntityAssembler;
import pe.edu.upc.matchevent.proposals.interfaces.rest.transform.UpdateProposalCommandFromResourceAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/proposals", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Proposals", description = "Proposal Management Endpoints")
public class ProposalsController {

    private final ProposalCommandService proposalCommandService;
    private final ProposalQueryService proposalQueryService;

    public ProposalsController(ProposalCommandService proposalCommandService, ProposalQueryService proposalQueryService) {
        this.proposalCommandService = proposalCommandService;
        this.proposalQueryService = proposalQueryService;
    }

    @Operation(
            summary = "Crear nuevo Proposal",
            description = "Registrar nuevo proposal en la base de datos."
    )
    @PostMapping
    public ResponseEntity<ProposalResponseResource> createProposal(@RequestBody CreateProposalResource resource) {
        try {
            CreateProposalCommand command = CreateProposalCommandFromResourceAssembler.toCommand(resource);
            Long proposalId = proposalCommandService.handle(command);

            if (proposalId == null || proposalId == 0L) {
                return ResponseEntity.badRequest().build();
            }

            var optionalProposal = proposalQueryService.handle(new GetProposalByIdQuery(proposalId));
            if (optionalProposal.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            ProposalResponseResource proposalResource = ProposalResponseResourceFromEntityAssembler.toResourceFromEntity(optionalProposal.get());
            return new ResponseEntity<>(proposalResource, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(
            summary = "Obtener todas las propuestas",
            description = "Retorna una lista con todas las propuestas registradas en el sistema."
    )
    @GetMapping
    public ResponseEntity<List<ProposalResponseResource>> getAllProposals() {
        List<Proposal> proposals = proposalQueryService.handle(new GetAllProposalsQuery());
        List<ProposalResponseResource> resources = proposals.stream()
                .map(ProposalResponseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(
            summary = "Obtener propuesta por su ID",
            description = "Retorna un solo JSON de respuesta (el valor del ID)."
    )
    @GetMapping("/{proposalId}")
    public ResponseEntity<ProposalResponseResource> getProposalById(@PathVariable Long proposalId) {
        var optionalProposal = proposalQueryService.handle(new GetProposalByIdQuery(proposalId));
        if (optionalProposal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ProposalResponseResource resource = ProposalResponseResourceFromEntityAssembler.toResourceFromEntity(optionalProposal.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(
            summary = "Actualizar Proposal por su ID",
            description = "Colocar el ID del proposal para actualizar sus datos."
    )
    @PutMapping("/{proposalId}")
    public ResponseEntity<ProposalResponseResource> updateProposal(@PathVariable Long proposalId, @RequestBody UpdateProposalResource resource) {
        try {
            UpdateProposalCommand command = UpdateProposalCommandFromResourceAssembler.toCommand(proposalId, resource);
            var optionalProposal = proposalCommandService.handle(command);

            if (optionalProposal.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            ProposalResponseResource updatedResource = ProposalResponseResourceFromEntityAssembler.toResourceFromEntity(optionalProposal.get());
            return ResponseEntity.ok(updatedResource);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Borrar proposal por ID",
            description = "Borrar proposal por el ID que se le coloque."
    )
    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Void> deleteProposal(@PathVariable Long proposalId) {
        try {
            proposalCommandService.handle(new DeleteProposalCommand(proposalId));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(
            summary = "Obtener Proposal por el RequestID",
            description = "Retorna una lista de proposals con el mismo requestID (sujeto a cambios " +
                    "cuando se implemente ACL)."
    )
    @GetMapping("/by-requestId")
    public ResponseEntity<List<ProposalResponseResource>> getProposalsByRequestId(@RequestParam Long requestId) {
        var proposals = proposalQueryService.handle(new GetProposalByRequestIdQuery(requestId));
        var resources = proposals.stream()
                .map(ProposalResponseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(
            summary = "Obtener Proposal por el ProducerID",
            description = "Retorna una lista con todas las propuestas registradas con el ProducerID" +
                    " (sujeto a cambios cuando se implemente ACL)."
    )
    @GetMapping("/by-producerId")
    public ResponseEntity<List<ProposalResponseResource>> getProposalsByProducerId(@RequestParam Long producerId) {
        var proposals = proposalQueryService.handle(new GetProposalByProducerIdQuery(producerId));
        var resources = proposals.stream()
                .map(ProposalResponseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/by-dates")
    public ResponseEntity<List<ProposalResponseResource>> getProposalsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        var proposals = proposalQueryService.handle(
                new GetProposalByDateRangeQuery(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate))
        );

        var resources = proposals.stream()
                .map(ProposalResponseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
