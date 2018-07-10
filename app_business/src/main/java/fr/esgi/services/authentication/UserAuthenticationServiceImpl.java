package fr.esgi.services.authentication;

import entities.Role;
import entities.User;
import entities.UserAuthenticator;
import fr.esgi.exception.EmailAlreadyExistException;
import fr.esgi.exception.UserAlreadyExistException;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public UserAuthenticator save(UserAuthenticatorData userAuthenticatorData, UserAuthenticator userAuthenticator) {
        return userAuthenticatorData.save(userAuthenticator);    }

    @Override
    public boolean existsByUsername(UserAuthenticatorData userAuthenticatorData, String username) {
        return null != userAuthenticatorData.findByUsername(username);
    }

    @Override
    public boolean existsByEmail(UserAuthenticatorData userAuthenticatorData, String email) {
        return null != userAuthenticatorData.findByEmail(email);
    }

    @Override
    public void signIn(UserData userData, UserAuthenticatorData userAuthenticatorData, User user, Integer roleId, UserAuthenticator userAuthenticator) throws EmailAlreadyExistException, UserAlreadyExistException, fr.esgi.exception.RoleNotFoundException {

        if(existsByUsername(userAuthenticatorData, userAuthenticator.getLogin())) {
            throw new UserAlreadyExistException();
        }

        if(existsByEmail(userAuthenticatorData, userAuthenticator.getEmail())) {
            throw new EmailAlreadyExistException();
        }

        //On enregistre l'utilisateur
            Role role = userData.getRoleById(roleId);
            user.getRoles().add(role);


        user = userData.save(user);

        //On enregistre l'authentificateur
        userAuthenticator.setUser(user);

        userAuthenticatorData.save(userAuthenticator);
    }

    @Override
    public UserAuthenticator findByUserNameOrEmail(UserData userData,UserAuthenticatorData userAuthenticatorData, String s) throws UserNotFoundException{
        UserAuthenticator userAuthenticator = userAuthenticatorData.findByUsername(s);
        if(null == userAuthenticator){
            userAuthenticator = userAuthenticatorData.findByEmail(s);
        }

        if(null == userAuthenticator){
            throw new UserNotFoundException();
        }

        User user = userData.getById(userAuthenticator.getUser().getId());
        userAuthenticator.setUser(user);
        return userAuthenticator;
    }

    @Override
    public UserAuthenticator findById(UserAuthenticatorData userAuthenticatorData, Long userId) {
        return userAuthenticatorData.findById(userId.intValue());
    }
}

