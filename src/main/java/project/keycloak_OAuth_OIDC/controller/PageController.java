package project.keycloak_OAuth_OIDC.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController {

    @GetMapping("/public/hello")
    public ResponseEntity<String> helloPublic(){
        return ResponseEntity.ok("Hello public user");
    }

    @GetMapping("/member/hello")
    public ResponseEntity<String> helloMember(){
        return ResponseEntity.ok("Hello member");
    }

    @GetMapping("/moderator/hello")
    public ResponseEntity<String> helloModerator(){
        return ResponseEntity.ok("Hello moderator");
    }

    @GetMapping("/admin/hello")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello admin");
    }
}
