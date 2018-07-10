package fr.esgi.components.user;

import entities.Role;
import entities.User;
import entities.UserAuthenticator;
import fr.esgi.components.user.adapter.RoleApiAdapter;
import fr.esgi.components.user.adapter.UserApiAdapter;
import fr.esgi.components.user.dto.RoleDto;
import fr.esgi.components.user.dto.UserDetailsDto;
import fr.esgi.components.user.dto.UserDto;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.exception.UserNotFoundExceptionApi;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 6000)
@RequestMapping("/users")
public class UserController {

    private final
    UserData userData;

    private final
    UserAuthenticatorData userAuthenticatorData;

    private final
    UserService userService;

    private final
    UserApiAdapter userApiAdapter;

    private final
    RoleApiAdapter roleApiAdapter;

    @Autowired
    public UserController(UserAuthenticatorData userAuthenticatorData, UserData userData, UserService userService, UserApiAdapter userApiAdapter, RoleApiAdapter roleApiAdapter) {
        this.userData = userData;
        this.userService = userService;
        this.userApiAdapter = userApiAdapter;
        this.roleApiAdapter = roleApiAdapter;
        this.userAuthenticatorData = userAuthenticatorData;
    }

    /**
     * Permet la création d'un utilisateur
     * @param userDto Dto représentant un utilisateur
     * @return UserDto
     */
    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public UserDto saveOrUpdate(@RequestBody final UserDto userDto){
        User user = userApiAdapter.convertToModel(userDto);
        return userApiAdapter.convertToDto(userData.save(user));
    }

    /**
     *  Permet de récupérer les informations d'un utilisateur par ID
     * @param id de l'utilisateur
     * @return UserDto
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public UserDetailsDto getById(@PathVariable(value="id") int id) {
        try{
            Object[] objects = userService.getById(userData,userAuthenticatorData,id);
            return userApiAdapter.convertToUserDetailsDto((User) objects[0], (UserAuthenticator) objects[1]);
        } catch (UserNotFoundException u){
            throw new UserNotFoundExceptionApi(u.getMessage());
        }
    }

    /**
     * Permet la suppression d'un utilisateur
     * @param userId, id de l'utilisateur
     */
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public void delete(@PathVariable(value="userId") int userId){
        userService.delete(userData,userId);
    }

    /**
     * Permet la récupération de l'ensemble des rôles disponible aux utilisateurs
     * @return une liste de roles
     */
    @GetMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<RoleDto> getRoles() {
        List<Role> roles = userData.getRoles();
        List<RoleDto> roleDtos = new ArrayList<>();
        for(Role role : roles ){
            RoleDto roleDto = roleApiAdapter.convertToDto(role);
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    /**
     * Permet la récupération du role d'un utilisateur
     * @return la liste des ids des roles de l'utilisateur
     */
    @GetMapping("/{userId}/roles")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Integer> getRolesByUserId(@PathVariable(value = "userId") int userId) {
        User user = userData.getById(userId);
        List<Role> roles;
        if(null != user && null != user.getRoles()){ //TODO renvoyer erreur si l'utilisateur existe pas
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
     * Permet l'assignation d'un role à l'utilisateur
     * @param userId id de l'utilisateur
     * @param roleId id du role à ajouter à l'utilisateur
     */
    @PostMapping("/{userId}/roles/{roleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void assignRole(@PathVariable(value="userId") int userId, @PathVariable(value="roleId") int roleId){
        User user = userData.getById(userId);

        if( null != user){ //TODO renvoyer erreur si l'utilisateur existe pas
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

}
