package fr.esgi.services.authentication;

import entities.User;
import entities.UserAuthenticator;
import fr.esgi.exception.EmailAlreadyExistException;
import fr.esgi.exception.UserAlreadyExistException;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;

public interface UserAuthenticationService {

    UserAuthenticator save(UserAuthenticatorData userAuthenticatorData, UserAuthenticator userAuthenticator);

    boolean existsByUsername(UserAuthenticatorData userAuthenticatorData, String username);

    boolean existsByEmail(UserAuthenticatorData userAuthenticatorData, String username);

    Integer signIn(UserData userData,UserAuthenticatorData userAuthenticatorData, User user, Integer roleid, UserAuthenticator userAuthenticator) throws EmailAlreadyExistException, UserAlreadyExistException;

    UserAuthenticator findByUserNameOrEmail(UserData userData, UserAuthenticatorData userAuthenticatorData, String s);

    UserAuthenticator findById(UserAuthenticatorData userAuthenticatorData, Long userId);
}

