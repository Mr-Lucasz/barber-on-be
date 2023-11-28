package barberon.barberonbe.controller;

import barberon.barberonbe.model.Barbeiro;
import barberon.barberonbe.model.Cliente;
import barberon.barberonbe.model.Usuario;
import barberon.barberonbe.service.AuthenticationService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Map<String, String> user) {
        Usuario authenticatedUser = authenticationService.authenticate(user.get("email"), user.get("password"), user.get("userType"));
        return ResponseEntity.ok(authenticatedUser);
    }
}