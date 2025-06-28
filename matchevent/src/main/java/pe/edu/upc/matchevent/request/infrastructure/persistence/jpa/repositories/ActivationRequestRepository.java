package pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;

public interface ActivationRequestRepository extends JpaRepository<ActivationRequest, Long> {
    boolean existsByCompanyIdAndEventTitle(Long companyId, String eventTitle);

}
