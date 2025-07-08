package pe.edu.upc.matchevent.request.domain.model.valueobjects;

public enum RequestStatuses {
    IN_PROGRESS(1),
    ACCEPTED(2),
    REJECTED(3),
    CANCELLED(4);

    private final int value;

    RequestStatuses(int value) {
        this.value = value;
    }
}
