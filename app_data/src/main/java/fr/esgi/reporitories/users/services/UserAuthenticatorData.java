package fr.esgi.reporitories.users.services;

import entities.Role;
import entities.UserAuthenticator;
import fr.esgi.reporitories.users.dao.TUserAuthenticator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAuthenticatorData {
  UserAuthenticator save(UserAuthenticator userAuthenticator);
  UserAuthenticator findByUsername(String username);
  UserAuthenticator findById(Integer userid);
  UserAuthenticator findByEmail(String email);
  List<String> getRolesByUserAuthenticator(Integer userId);
}
