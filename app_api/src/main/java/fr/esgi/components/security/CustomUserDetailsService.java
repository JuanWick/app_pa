package fr.esgi.components.security;

import entities.UserAuthenticator;
import fr.esgi.exception.AuthenticationExceptionApi;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.authentication.UserAuthenticationService;
import fr.esgi.components.security.adapter.UserPrincipalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserAuthenticatorData userAuthenticatorData;

    @Autowired
    UserData userData;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    UserPrincipalAdapter userPrincipalAdapter;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAuthenticator userAuthenticator = userAuthenticationService.findByUserNameOrEmail(userData, userAuthenticatorData,s);

        if(null != userAuthenticator){
            return userPrincipalAdapter.ModelToSpring(userAuthenticator);
        } else {
            throw new AuthenticationExceptionApi("Authentification impossible");
        }
    }

    public UserDetails loadUserByToken(Long userId) {
        UserAuthenticator userAuthenticator = userAuthenticationService.findById(userAuthenticatorData,userId);
        if(null != userAuthenticator){
            return userPrincipalAdapter.ModelToSpring(userAuthenticator);
        } else {
            throw new AuthenticationExceptionApi("Identification impossible");
        }
    }
}
