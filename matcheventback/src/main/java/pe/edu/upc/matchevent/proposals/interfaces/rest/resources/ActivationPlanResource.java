package pe.edu.upc.matchevent.proposals.interfaces.rest.resources;

public record ActivationPlanResource(
        String objective,
        String concept,
        String branding,
        String activation,
        String resources,
        String kpi
) {}