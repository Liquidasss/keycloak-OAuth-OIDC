package project.keycloak_OAuth_OIDC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.keycloak_OAuth_OIDC.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
