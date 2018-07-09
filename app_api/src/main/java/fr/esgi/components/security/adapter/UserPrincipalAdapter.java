package fr.esgi.components.security.adapter;

import entities.Role;
import entities.User;
import entities.UserAuthenticator;
import fr.esgi.components.security.dto.UserPrincipal;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserPrincipalAdapter {

    @Autowired
    UserService userService;

    @Autowired
    UserData userData;

    @Autowired
    UserAuthenticatorData userAuthenticatorData;

    public UserDetails ModelToSpring(UserAuthenticator userAuthenticator) {
        List<GrantedAuthority> autorities = new ArrayList<>();
        for(String role : userAuthenticatorData.getRolesByUserAuthenticator(userAuthenticator.getId())){
            autorities.add(new SimpleGrantedAuthority(role));
        }
        UserPrincipal userDetails = UserPrincipal.builder()
                .id(userAuthenticator.getId())
                .username(userAuthenticator.getLogin())
                .password(userAuthenticator.getPassword())
                .email(userAuthenticator.getEmail())
                .authorities(autorities)
                .build();
        return userDetails;
    }
}
