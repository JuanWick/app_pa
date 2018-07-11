package fr.esgi.components.user;

import entities.User;
import entities.UserAuthenticator;
import fr.esgi.components.security.strategy.jwt.JwtAuthenticationFilter;
import fr.esgi.components.security.strategy.jwt.JwtTokenProvider;
import fr.esgi.components.security.dto.*;
import fr.esgi.components.user.dto.PasswordRequest;
import fr.esgi.components.user.dto.SignInDto;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.Date;

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

    @Autowired
    JwtAuthenticationFilter authenticationFilter;

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
        if(!signUpRequest.isAcceptedRgpd()){
            throw new RgpdDeclinedExceptionApi("Erreur : RGPD declined");
        }
        User user = new User();
        user.setFirstName(signUpRequest.getFirstname());
        user.setName(signUpRequest.getName().toUpperCase());
        user.setRgpdAccepted(true);
        user.setRgpdAcceptedDate(new Date());

        UserAuthenticator userAuthenticator = new UserAuthenticator();
        userAuthenticator.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userAuthenticator.setLogin(signUpRequest.getUsername());
        userAuthenticator.setEmail(signUpRequest.getEmail());

        Integer userId = null;
        try{
            userId = userAuthenticationService.signIn(userData, userAuthenticatorData, user,  signUpRequest.getRoleId(), userAuthenticator);
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

        return ResponseEntity.created(location).body(new RegisterResponse(true, userId));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody PasswordRequest passwordRequest) {

        try {
            UserAuthenticator userAuthenticator = userAuthenticationService.findByUserNameOrEmail(
                    userData,
                    userAuthenticatorData,
                    SecurityContextHolder.getContext().getAuthentication().getName());
            userAuthenticator.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));

            userAuthenticationService.save(userAuthenticatorData, userAuthenticator);

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsernameOrEmail(userAuthenticator.getLogin());
            loginRequest.setPassword(passwordRequest.getPassword());

            return authenticateUser(loginRequest);
        } catch(UserNotFoundException u){
            throw new UserNotFoundExceptionApi(u.getMessage());
        }

    }

    @PostMapping("/reinit")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Transactional
    public void reInitUserPassword(@RequestBody SignInDto signInDto) {
        try {
            UserAuthenticator userAuthenticator = userAuthenticationService.findByUserNameOrEmail(
                    userData,
                    userAuthenticatorData,
                    signInDto.getUserNameOrEmail());
            userAuthenticator.setPassword(passwordEncoder.encode(signInDto.getPassword()));

            userAuthenticationService.save(userAuthenticatorData, userAuthenticator);

        } catch(UserNotFoundException u){
            throw new UserNotFoundExceptionApi(u.getMessage());
        }
    }
}