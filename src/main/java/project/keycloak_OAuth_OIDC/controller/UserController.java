package project.keycloak_OAuth_OIDC.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.keycloak_OAuth_OIDC.dto.CreateUserRequest;
import project.keycloak_OAuth_OIDC.service.serviceImpl.UserServiceImpl;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest dto){
        Response createdResponse = userService.createKeycloakUser(dto);
        return ResponseEntity.status(createdResponse.getStatus()).build();
    }

}
