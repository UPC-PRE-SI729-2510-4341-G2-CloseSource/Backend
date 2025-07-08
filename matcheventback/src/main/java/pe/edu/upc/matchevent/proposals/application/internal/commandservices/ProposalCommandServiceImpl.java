package pe.edu.upc.matchevent.proposals.application.internal.commandservices;

import jakarta.persistence.Id;
import pe.edu.upc.matchevent.iam.domain.model.aggregates.User;
import pe.edu.upc.matchevent.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.edu.upc.matchevent.proposals.domain.model.aggregates.Proposal;
import pe.edu.upc.matchevent.proposals.domain.model.commands.CreateProposalCommand;
import pe.edu.upc.matchevent.proposals.domain.model.commands.DeleteProposalCommand;
import pe.edu.upc.matchevent.proposals.domain.model.commands.UpdateProposalCommand;
import pe.edu.upc.matchevent.proposals.domain.model.entities.ActivationPlan;
import pe.edu.upc.matchevent.proposals.domain.model.valueobjects.ProposalStatus;
import pe.edu.upc.matchevent.proposals.domain.services.ProposalCommandService;
import pe.edu.upc.matchevent.proposals.infrastructure.persistence.jpa.repositories.ProposalRepository;
import pe.edu.upc.matchevent.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;
import pe.edu.upc.matchevent.request.domain.model.aggregates.ActivationRequest;
import pe.edu.upc.matchevent.request.infrastructure.persistence.jpa.repositories.ActivationRequestRepository;
import pe.edu.upc.matchevent.request.interfaces.acl.RequestContextFacade;

import java.util.Optional;

@Service
public class ProposalCommandServiceImpl implements ProposalCommandService {

    private final IamContextFacade iamContextFacade;
    private final ActivationRequestRepository activationRequestRepository;
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;
    private final RequestContextFacade requestContextFacade;

    public ProposalCommandServiceImpl(IamContextFacade iamContextFacade,
                                      ActivationRequestRepository activationRequestRepository,
                                      ProposalRepository proposalRepository,
                                      UserRepository userRepository,
                                      RequestContextFacade requestContextFacade) {
        this.iamContextFacade = iamContextFacade;
        this.activationRequestRepository = activationRequestRepository;
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
        this.requestContextFacade = requestContextFacade;
    }

    @Override
    public Long handle(CreateProposalCommand command) {

        // VALIDACIÓN CON ACL: Verificar que el Producer existe
        Long producerId = command.producerId();
        Long requestId = command.requestId();
        String username = iamContextFacade.fetchUsernameByUserId(producerId);
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Producer with ID " + producerId + " does not exist in IAM.");
        }

        // Cargar entidad User y Request completa para relacionarla en Proposal
        User producer = userRepository.findById(producerId)
                .orElseThrow(() -> new IllegalArgumentException("Producer user not found with id " + producerId));
        ActivationRequest request = activationRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("ActivationRequest no encontrado para id: " + requestId));


        // VALIDAR QUE EL REQUEST EXISTE y pertenece a la compañía autorizada (si tienes esta lógica)
        if (producerId == null || username.isBlank()) {
            throw new IllegalArgumentException("Request with ID " + requestId + " does not exist");
        }

        // Crear Proposal con el User cargado
        Proposal proposal = new Proposal(
                request,
                producer,
                command.name(),
                new ActivationPlan(
                        command.objective(),
                        command.concept(),
                        command.branding(),
                        command.activation(),
                        command.resources(),
                        command.kpi()
                ),
                command.offeredPrice(),
                command.submissionDate(),
                ProposalStatus.PENDING
        );

        try {
            proposalRepository.save(proposal);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving proposal: " + e.getMessage());
        }

        return proposal.getId(); // ID del agregado
    }


    @Override
    public Optional<Proposal> handle(UpdateProposalCommand command) {
        Long proposalId = command.id();

        // Validar que no exista otra propuesta con el mismo nombre y distinto ID
        if (proposalRepository.existsProposalByNameAndIdIsNot(command.name(), proposalId)) {
            throw new IllegalArgumentException("Proposal with name '" + command.name() + "' already exists");
        }

        // Buscar propuesta a actualizar
        var optionalProposal = proposalRepository.findById(proposalId);
        if (optionalProposal.isEmpty()) {
            throw new IllegalArgumentException("Proposal with ID " + proposalId + " does not exist");
        }
        var proposalToUpdate = optionalProposal.get();

        // Cargar el User productor desde repo
        var producer = userRepository.findById(command.producerId())
                .orElseThrow(() -> new IllegalArgumentException("Producer user not found with ID " + command.producerId()));
        var request = activationRequestRepository.findById(command.requestId())
                .orElseThrow(() -> new IllegalArgumentException("ActivationRequest no encontrado para id: " + command.requestId()));
        // Construir nuevo ActivationPlan
        ActivationPlan newActivationPlan = new ActivationPlan(
                command.objective(),
                command.concept(),
                command.branding(),
                command.activation(),
                command.resources(),
                command.kpi()
        );

        // Actualizar la propuesta con nuevos datos
        proposalToUpdate.updateInformation(
                request,
                producer,
                command.name(),
                newActivationPlan,
                command.offeredPrice(),
                command.submissionDate(),
                command.proposalStatus()
        );

        try {
            var updatedProposal = proposalRepository.save(proposalToUpdate);
            return Optional.of(updatedProposal);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating proposal: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProposalCommand command) {
        Long proposalId = command.id();


        if (!proposalRepository.existsById(proposalId)) {
            throw new IllegalArgumentException("Proposal with ID " + proposalId + " does not exist");
        }

        try {
            proposalRepository.deleteById(proposalId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting proposal: " + e.getMessage());
        }
    }
}
