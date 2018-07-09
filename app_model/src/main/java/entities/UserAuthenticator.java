package entities;

import lombok.Data;

@Data
public class UserAuthenticator {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private User user;
}
