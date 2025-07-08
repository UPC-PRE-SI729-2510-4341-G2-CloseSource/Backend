package pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.matchevent.request.domain.model.entities.RequestStatus;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.RequestStatuses;

import java.util.Optional;

public interface RequestStatusRepository extends JpaRepository<RequestStatus, Long> {
    boolean existsByName(RequestStatuses name);
    Optional<RequestStatus> findByName(RequestStatuses name);
}
