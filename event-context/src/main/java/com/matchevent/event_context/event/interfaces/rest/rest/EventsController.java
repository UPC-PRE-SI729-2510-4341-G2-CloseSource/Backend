package com.matchevent.event_context.event.interfaces.rest.rest;

import com.matchevent.event_context.event.domain.model.valueobjects.EventStatus;
import com.matchevent.event_context.event.domain.model.commands.*;
import com.matchevent.event_context.event.domain.model.queries.GetEventByIdQuery;
import com.matchevent.event_context.event.domain.services.EventCommandService;
import com.matchevent.event_context.event.domain.services.EventQueryService;
import com.matchevent.event_context.event.interfaces.rest.resources.*;
import com.matchevent.event_context.event.interfaces.rest.transform.EventResourceFromEntityAssembler;
import com.matchevent.event_context.event.interfaces.rest.transform.EventResourceFromViewAssembler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/events", produces = APPLICATION_JSON_VALUE)
public class EventsController {

    private final EventCommandService commandService;
    private final EventQueryService   queryService;

    public EventsController(EventCommandService commandService,
                            EventQueryService queryService) {
        this.commandService = commandService;
        this.queryService   = queryService;
    }

    /* ---------- CREATE ---------- */
    @PostMapping
    public ResponseEntity<EventResource> create(@RequestBody CreateEventResource body) {

        var cmd = new CreateEventCommand(
                body.proposalId(),
                LocalDateTime.parse(body.startDate()),
                LocalDateTime.parse(body.endDate()),
                body.description(),
                body.location());

        Long id = commandService.handle(cmd);

        return queryService.handle(new GetEventByIdQuery(id))
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .map(r -> ResponseEntity.ok(r))                 //  ← desambiguado
                .orElse(ResponseEntity.badRequest().build());
    }

    /* ---------- READ agregado completo ---------- */
    @GetMapping("/{id}")
    public ResponseEntity<EventResource> getById(@PathVariable Long id) {
        return queryService.handle(new GetEventByIdQuery(id))
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());
    }

    /* ---------- READ proyección (CQRS read-side) ---------- */
    @GetMapping("/views/{id}")
    public ResponseEntity<EventResource> getView(@PathVariable Long id) {
        return queryService.handleAsView(new GetEventByIdQuery(id))
                .map(EventResourceFromViewAssembler::toResource)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());
    }

    /* ---------- PATCH status ---------- */
    @PatchMapping("/{id}/status")
    public ResponseEntity<EventResource> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateEventStatusResource body) {

        var cmd = new UpdateEventStatusCommand(
                id, EventStatus.valueOf(body.newStatus().toUpperCase()));

        return commandService.handle(cmd)
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());
    }

    /* ---------- POST gallery ---------- */
    @PostMapping("/{id}/gallery")
    public ResponseEntity<EventResource> addGallery(
            @PathVariable Long id,
            @RequestBody AddGalleryItemResource body) {

        var cmd = new AddGalleryItemCommand(id, body.imageUrl(), body.caption());
        commandService.handle(cmd);

        return queryService.handle(new GetEventByIdQuery(id))
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.notFound().build());
    }
}
