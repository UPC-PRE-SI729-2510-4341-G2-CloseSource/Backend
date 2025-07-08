package pe.edu.upc.matchevent.request.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.RequestStatuses;

@Entity
@Table(name = "request_statuses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class RequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 22)
    private RequestStatuses name;

    public RequestStatus(RequestStatuses name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static RequestStatus getDefaultRequestStatus() {
        return new RequestStatus(RequestStatuses.IN_PROGRESS);
    }

    public static RequestStatus toRequestStatusFromName(String name) {
        return new RequestStatus(RequestStatuses.valueOf(name));
    }
}
