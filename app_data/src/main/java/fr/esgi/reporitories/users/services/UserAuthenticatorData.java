package fr.esgi.reporitories.users.services;

import entities.UserAuthenticator;
import java.util.List;

public interface UserAuthenticatorData {
  UserAuthenticator save(UserAuthenticator userAuthenticator);
  UserAuthenticator findByUsername(String username);
  UserAuthenticator findById(Integer id);
  UserAuthenticator findByEmail(String email);
  UserAuthenticator findByUserId(Integer userid);
  List<String> getRolesByUserAuthenticator(Integer userId);
}
