package project.keycloak_OAuth_OIDC.service;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.stereotype.Service;
import project.keycloak_OAuth_OIDC.dto.CreateUserRequest;

import javax.ws.rs.core.Response;

@Service
public interface UserService {

    public Response createKeycloakUser(CreateUserRequest dto);

    CredentialRepresentation createPasswordCredentials(String password);
}
