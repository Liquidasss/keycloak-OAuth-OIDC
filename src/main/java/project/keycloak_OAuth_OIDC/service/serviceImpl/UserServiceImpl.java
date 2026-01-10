package project.keycloak_OAuth_OIDC.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.keycloak_OAuth_OIDC.config.KeycloakProvider;
import project.keycloak_OAuth_OIDC.dto.CreateUserRequest;
import project.keycloak_OAuth_OIDC.entity.User;
import project.keycloak_OAuth_OIDC.repository.UserRepository;
import project.keycloak_OAuth_OIDC.service.UserService;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${keycloak.realm}")
    public String realm;

    private final KeycloakProvider kcProvider;
    private final UserRepository userRepository;

    @Override
    public Response createKeycloakUser(CreateUserRequest dto) {
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(dto.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(dto.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(dto.getFirstName());
        kcUser.setLastName(dto.getLastName());
        kcUser.setEmail(dto.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

        Response response = usersResource.create(kcUser);

        String keycloakId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .keycloakId(keycloakId)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return response;
    }

    @Override
    public CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
