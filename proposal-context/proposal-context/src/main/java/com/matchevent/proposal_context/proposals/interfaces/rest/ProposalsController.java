package com.matchevent.proposal_context.proposals.interfaces.rest;

import com.matchevent.proposal_context.proposals.domain.model.commands.*;
import com.matchevent.proposal_context.proposals.domain.model.queries.*;
import com.matchevent.proposal_context.proposals.domain.model.aggregates.Proposal;
import com.matchevent.proposal_context.proposals.domain.services.ProposalCommandService;
import com.matchevent.proposal_context.proposals.domain.services.ProposalQueryService;
import com.matchevent.proposal_context.proposals.interfaces.rest.resources.CreateProposalResource;
import com.matchevent.proposal_context.proposals.interfaces.rest.resources.ProposalResponseResource;
import com.matchevent.proposal_context.proposals.interfaces.rest.resources.UpdateProposalResource;
import com.matchevent.proposal_context.proposals.interfaces.rest.transform.CreateProposalCommandFromResourceAssembler;
import com.matchevent.proposal_context.proposals.interfaces.rest.transform.ProposalResponseResourceFromEntityAssembler;
import com.matchevent.proposal_context.proposals.interfaces.rest.transform.UpdateProposalCommandFromResourceAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<ProposalResponseResource>> getAllProposals() {
        List<Proposal> proposals = proposalQueryService.handle(new GetAllProposalsQuery());
        List<ProposalResponseResource> resources = proposals.stream()
                .map(ProposalResponseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{proposalId}")
    public ResponseEntity<ProposalResponseResource> getProposalById(@PathVariable Long proposalId) {
        var optionalProposal = proposalQueryService.handle(new GetProposalByIdQuery(proposalId));
        if (optionalProposal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ProposalResponseResource resource = ProposalResponseResourceFromEntityAssembler.toResourceFromEntity(optionalProposal.get());
        return ResponseEntity.ok(resource);
    }

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

    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Void> deleteProposal(@PathVariable Long proposalId) {
        try {
            proposalCommandService.handle(new DeleteProposalCommand(proposalId));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
