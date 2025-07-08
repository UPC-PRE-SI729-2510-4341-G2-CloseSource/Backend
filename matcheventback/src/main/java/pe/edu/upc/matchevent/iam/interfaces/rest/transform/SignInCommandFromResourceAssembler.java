package pe.edu.upc.matchevent.iam.interfaces.rest.transform;

import pe.edu.upc.matchevent.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.matchevent.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
