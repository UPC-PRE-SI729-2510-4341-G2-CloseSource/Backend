package pe.edu.upc.matchevent.request.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.domain.model.commands.*;
import pe.edu.upc.matchevent.request.domain.model.entities.ActivationLocation;
import pe.edu.upc.matchevent.request.domain.model.entities.AudienceProfile;
import pe.edu.upc.matchevent.request.domain.model.entities.MaterialRequirement;
import pe.edu.upc.matchevent.request.domain.services.ActivationRequestCommandService;
import pe.edu.upc.matchevent.request.domain.model.valueobjects.EventDateRange;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.RequestStatusRepository;

@Service
public class ActivationRequestCommandServiceImpl implements ActivationRequestCommandService {

    private final ActivationRequestRepository activationRequestRepository;
    private final RequestStatusRepository requestStatusRepository;

    public ActivationRequestCommandServiceImpl(ActivationRequestRepository activationRequestRepository,
                                               RequestStatusRepository requestStatusRepository) {
        this.activationRequestRepository = activationRequestRepository;
        this.requestStatusRepository = requestStatusRepository;
    }

    @Override
    public Long handle(CreateActivationRequestCommand command) {
        if (command.title() == null || command.title().isBlank())
            throw new IllegalArgumentException("Title cannot be blank.");
        if (command.description() == null || command.description().isBlank())
            throw new IllegalArgumentException("Description cannot be blank.");

        var status = this.requestStatusRepository.findByName(
                pe.edu.upc.matchevent.request.domain.model.valueobjects.RequestStatuses.valueOf(command.status())
        ).orElseThrow(() -> new IllegalArgumentException("Request status with name " + command.status() + " not found"));

        var activationRequest = new ActivationRequest(
                command.title(),
                command.description(),
                command.companyId(),
                status,
                null,  // location
                null,  // materials
                null,  // eventDateRange
                null   // audienceProfiles
        );

        this.activationRequestRepository.save(activationRequest);
        return activationRequest.getId();
    }

    public void handle(Long activationRequestId, CreateAudienceProfileCommand command) {
        var activationRequest = activationRequestRepository.findById(activationRequestId)
                .orElseThrow(() -> new IllegalArgumentException("ActivationRequest not found"));

        if (command.ageRange() == null || command.ageRange().isBlank())
            throw new IllegalArgumentException("Age range must be specified.");
        if (command.genderTarget() == null)
            throw new IllegalArgumentException("Gender target must be specified.");
        if (command.interests() == null || command.interests().isEmpty())
            throw new IllegalArgumentException("Interests must not be empty.");

        var profile = new AudienceProfile(
                command.ageRange(),
                command.genderTarget(),
                command.interests()
        );
        profile.setActivationRequest(activationRequest);
        activationRequest.getAudienceProfiles().add(profile);

        activationRequestRepository.save(activationRequest);
    }

    public void handle(Long activationRequestId, CreateActivationLocationCommand command) {
        var activationRequest = activationRequestRepository.findById(activationRequestId)
                .orElseThrow(() -> new IllegalArgumentException("ActivationRequest not found"));

        if (command.capacity() <= 0)
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        if (command.address() == null || command.address().isBlank())
            throw new IllegalArgumentException("Address must be provided.");
        if (command.coordinates() == null)
            throw new IllegalArgumentException("Coordinates must be provided.");

        var location = new ActivationLocation(
                command.address(),
                command.coordinates(),
                command.capacity(),
                command.imageUrl()
        );

        activationRequest.setLocation(location);
        activationRequestRepository.save(activationRequest);
    }

    public void handle(Long activationRequestId, CreateEventDateRangeCommand command) {
        var activationRequest = activationRequestRepository.findById(activationRequestId)
                .orElseThrow(() -> new IllegalArgumentException("ActivationRequest not found"));

        if (command.startDate() == null || command.endDate() == null)
            throw new IllegalArgumentException("Start date and end date must be provided.");
        if (command.endDate().isBefore(command.startDate()))
            throw new IllegalArgumentException("End date must not be before start date.");

        var eventDateRange = new EventDateRange(command.startDate(), command.endDate());

        activationRequest.setEventDateRange(eventDateRange);
        activationRequestRepository.save(activationRequest);
    }

    public void handle(Long activationRequestId, CreateMaterialRequirementCommand command) {
        var activationRequest = activationRequestRepository.findById(activationRequestId)
                .orElseThrow(() -> new IllegalArgumentException("ActivationRequest not found"));

        if (command.name() == null || command.name().isBlank())
            throw new IllegalArgumentException("Material name must be specified.");
        if (command.quantity() <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero.");

        var material = new MaterialRequirement(
                command.name(),
                command.quantity(),
                command.specification(),
                command.providedByCompany()
        );
        material.setActivationRequest(activationRequest);
        activationRequest.getMaterials().add(material);

        activationRequestRepository.save(activationRequest);
    }
}
