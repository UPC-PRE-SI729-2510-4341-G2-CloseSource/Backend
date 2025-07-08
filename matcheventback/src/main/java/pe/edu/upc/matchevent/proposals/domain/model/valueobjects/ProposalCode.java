package pe.edu.upc.matchevent.proposals.domain.model.valueobjects;

import java.util.UUID;

public record ProposalCode(String profileCode) {
    public ProposalCode() {
        this(UUID.randomUUID().toString());
    }
    public ProposalCode {
        if (profileCode == null || profileCode.isBlank()) {
            throw new IllegalArgumentException("Student code cannot be null or blank");
        }
        if (profileCode.length() != 36) {
            throw new IllegalArgumentException("Student code must be 36 characters long");
        }
        if (!profileCode.matches("[a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12}")) {
            throw new IllegalArgumentException("Student code must be a valid UUID");
        }
    }
}