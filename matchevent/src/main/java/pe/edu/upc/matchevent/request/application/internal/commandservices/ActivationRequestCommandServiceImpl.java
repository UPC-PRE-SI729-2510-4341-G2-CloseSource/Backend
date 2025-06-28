package pe.edu.upc.matchevent.request.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.commands.CreateActivationRequestCommand;
import pe.edu.upc.matchevent.request.domain.model.commands.UpdateActivationRequestCommand;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestCommandService;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;

@Service
@Transactional
public class ActivationRequestCommandServiceImpl implements ActivationRequestCommandService {

    private final ActivationRequestRepository activationRequestRepository;

    public ActivationRequestCommandServiceImpl(ActivationRequestRepository activationRequestRepository) {
        this.activationRequestRepository = activationRequestRepository;
    }

    @Override
    public ActivationRequest handle(CreateActivationRequestCommand command) {

        if (command.companyId() == null || command.companyId() <= 0) {
            throw new IllegalArgumentException("Company ID must be a positive number");
        }

        if (command.eventTitle() == null || command.eventTitle().isBlank()) {
            throw new IllegalArgumentException("Event title cannot be blank");
        }

        if (command.eventTitle().length() > 100) {
            throw new IllegalArgumentException("Event title must be less than 100 characters");
        }

        if (command.eventDescription() == null || command.eventDescription().isBlank()) {
            throw new IllegalArgumentException("Event description cannot be blank");
        }

        if (command.eventDescription().length() > 200) {
            throw new IllegalArgumentException("Event description must be less than 200 characters");
        }

        if (activationRequestRepository.existsByCompanyIdAndEventTitle(command.companyId(), command.eventTitle())) {
            throw new IllegalArgumentException("An activation request with this company ID and title already exists");
        }

        var request = new ActivationRequest(
                command.companyId(),
                command.eventTitle(),
                command.eventDescription(),
                command.location(),
                command.startDate(),
                command.endDate(),
                command.status()
        );

        try {
            return activationRequestRepository.save(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving activation request: " + e.getMessage());
        }
    }


    @Override
    public ActivationRequest handle(UpdateActivationRequestCommand command) {
        try {
            var request = activationRequestRepository.findById(command.id())
                    .orElseThrow(() -> new IllegalArgumentException("Activation request not found with ID: " + command.id()));

            request.updateBasicInfo(
                    command.eventTitle(),
                    command.eventDescription(),
                    command.location(),
                    command.startDate(),
                    command.endDate(),
                    command.status()
            );

            return activationRequestRepository.save(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating activation request: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            activationRequestRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting activation request with ID " + id + ": " + e.getMessage());
        }
    }
}
