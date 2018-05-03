package joinEntities;

import lombok.Data;
import entities.Role;
import entities.User;

@Data
public class RUserRole {
    private User user;
    private Role role;
    private String password;
    private String mail;
}
