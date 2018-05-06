package fr.esgi.components.user;

import entities.Role;
import entities.User;
import fr.esgi.components.user.adapter.RoleDtoAdapter;
import fr.esgi.components.user.adapter.UserDtoAdapter;
import fr.esgi.components.user.dto.RoleDto;
import fr.esgi.components.user.dto.UserDto;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final
    UserData userData;

    private final
    UserService userService;

    private final
    UserDtoAdapter userDtoAdapter;

    private final
    RoleDtoAdapter roleDtoAdapter;

    @Autowired
    public UserController(UserData userData, UserService userService, UserDtoAdapter userDtoAdapter, RoleDtoAdapter roleDtoAdapter) {
        this.userData = userData;
        this.userService = userService;
        this.userDtoAdapter = userDtoAdapter;
        this.roleDtoAdapter = roleDtoAdapter;
    }

    /**
     *  Permet de récupérer les informations d'un utilisateur par ID
     * @param id de l'utilisateur
     * @return UserDto
     */
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable(value="id") int id) {
        User user = userData.getById(id);
        UserDto userDto = null;

        if(null != user){
            userDto =  userDtoAdapter.convertToDto(user);
        }
        return userDto;
    }

    /**
     * Permet la récupération de l'ensemble des rôles disponible aux utilisateurs
     * @return une liste de roles
     */
    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        List<Role> roles = userData.getRoles();
        List<RoleDto> roleDtos = new ArrayList<>();
        for(Role role : roles ){
            RoleDto roleDto = roleDtoAdapter.convertToDto(role);
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    /**
     * Permet la récupération du role d'un utilisateur
     * @return la liste des ids des roles de l'utilisateur
     */
    @GetMapping("/{userId}/roles")
    public List<Integer> getRolesByUserId(@PathVariable(value = "userId") int userId) {
        User user = userData.getById(userId);
        List<Role> roles;
        if(null != user.getRoles()){
            roles = user.getRoles();
        } else {
            roles = new ArrayList<>();
        }
        List<Integer> roleIds = new ArrayList<>();
        for(Role role : roles ){
           roleIds.add(role.getId());
        }
        return roleIds;
    }

    /**
     * Permet la création d'un utilisateur
     * @param userDto Dto représentant un utilisateur
     * @return UserDto
     */
    @PostMapping("")
    public UserDto create(@RequestBody final UserDto userDto){
        User user = userDtoAdapter.convertToModel(userDto);
        return userDtoAdapter.convertToDto(userData.save(user));
    }

    /**
     * Permet l'assignation d'un role à l'utilisateur
     * @param userId id de l'utilisateur
     * @param roleId id du role à ajouter à l'utilisateur
     */
    @PostMapping("/{userId}/roles/{roleId}")
    public void assignRole(@PathVariable(value="userId") int userId, @PathVariable(value="roleId") int roleId){
        User user = userData.getById(userId);

        Role role = new Role();
        role.setId(roleId);
        if(null != user.getRoles()){
            user.getRoles().add(role);
        } else {
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
        }
        userData.save(user);
    }
}
