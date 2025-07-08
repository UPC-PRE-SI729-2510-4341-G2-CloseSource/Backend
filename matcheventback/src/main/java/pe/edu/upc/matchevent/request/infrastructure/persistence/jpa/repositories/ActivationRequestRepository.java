package pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;

@Repository
public interface ActivationRequestRepository extends JpaRepository<ActivationRequest, Long> {
}
