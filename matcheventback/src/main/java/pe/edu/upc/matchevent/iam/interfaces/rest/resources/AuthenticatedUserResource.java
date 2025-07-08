package pe.edu.upc.matchevent.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
