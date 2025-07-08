package pe.edu.upc.matchevent.iam.domain.services;

import pe.edu.upc.matchevent.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
