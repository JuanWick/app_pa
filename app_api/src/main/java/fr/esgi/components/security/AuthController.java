package fr.esgi.components.security;

import entities.User;
import entities.UserAuthenticator;
import fr.esgi.components.security.dto.ApiResponse;
import fr.esgi.components.security.dto.JwtAuthenticationResponse;
import fr.esgi.components.security.dto.LoginRequest;
import fr.esgi.components.security.dto.SignUpRequest;
import fr.esgi.exception.*;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.authentication.UserAuthenticationService;
import fr.esgi.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    UserService userService;

    @Autowired
    UserData userData;

    @Autowired
    UserAuthenticatorData userAuthenticatorData;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        // Creating user's account
        User user = new User();
        user.setFirstName(signUpRequest.getFirstname());
        user.setName(signUpRequest.getName());

        UserAuthenticator userAuthenticator = new UserAuthenticator();
        userAuthenticator.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userAuthenticator.setLogin(signUpRequest.getUsername());
        userAuthenticator.setEmail(signUpRequest.getEmail());

        try{
            userAuthenticationService.signIn(userData, userAuthenticatorData, user,  signUpRequest.getRoleId(), userAuthenticator);
        } catch (UserAlreadyExistException us) {
            throw new UserAlreadyExistExceptionApi(us.getMessage());
        } catch (EmailAlreadyExistException em) {
            throw new EmailAlreadyExistExceptionApi(em.getMessage());
        } catch (RoleNotFoundException r) {
            throw new RoleNotFoundExceptionApi(r.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(userAuthenticator.getLogin()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}