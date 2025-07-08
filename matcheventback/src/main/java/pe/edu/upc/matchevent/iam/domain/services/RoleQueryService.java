package pe.edu.upc.matchevent.iam.domain.services;

import pe.edu.upc.matchevent.iam.domain.model.entities.Role;
import pe.edu.upc.matchevent.iam.domain.model.queries.GetAllRolesQuery;
import pe.edu.upc.matchevent.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
  List<Role> handle(GetAllRolesQuery query);
  Optional<Role> handle(GetRoleByNameQuery query);
}
