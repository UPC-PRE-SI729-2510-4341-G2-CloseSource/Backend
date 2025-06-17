package com.matchevent.proposal_context.proposals.interfaces.rest.controllers;

import com.matchevent.proposal_context.proposals.domain.model.commands.DeleteProposalCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matchevent.proposal_context.proposals.domain.services.ProposalCommandService;
import com.matchevent.proposal_context.proposals.interfaces.rest.resources.*;

import com.matchevent.proposal_context.proposals.interfaces.rest.transform.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/proposals")
public class ProposalResource {

    private final ProposalCommandService proposalCommandService;

    public ProposalResource(ProposalCommandService proposalCommandService) {
        this.proposalCommandService = proposalCommandService;
    }

    @PostMapping
    public ResponseEntity<Long> createProposal(@RequestBody CreateProposalResource resource) {
        try {
            var command = CreateProposalCommandFromResourceAssembler.toCommand(resource);
            Long id = proposalCommandService.handle(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{proposalId}")
    public ResponseEntity<?> updateProposal(@PathVariable Long proposalId, @RequestBody UpdateProposalResource resource) {
        try {
            var command = UpdateProposalCommandFromResourceAssembler.toCommand(proposalId, resource);
            var updatedProposal = proposalCommandService.handle(command);
            return updatedProposal
                    .map(proposal -> ResponseEntity.ok().build())
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{proposalId}")
    public ResponseEntity<?> deleteProposal(@PathVariable Long proposalId) {
        try {
            var command = new DeleteProposalCommand(proposalId);
            proposalCommandService.handle(command);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
