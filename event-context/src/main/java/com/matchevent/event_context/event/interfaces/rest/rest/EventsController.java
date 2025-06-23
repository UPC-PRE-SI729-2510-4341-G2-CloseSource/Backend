package com.matchevent.event_context.event.interfaces.rest.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matchevent.event_context.event.domain.model.commands.*;
import com.matchevent.event_context.event.domain.model.queries.GetEventByIdQuery;
import com.matchevent.event_context.event.domain.services.EventCommandService;
import com.matchevent.event_context.event.domain.services.EventQueryService;
import com.matchevent.event_context.event.interfaces.rest.resources.*;
import com.matchevent.event_context.event.interfaces.rest.transform.EventResourceFromEntityAssembler;

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
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResource> getById(@PathVariable Long id) {
        return queryService.handle(new GetEventByIdQuery(id))
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
