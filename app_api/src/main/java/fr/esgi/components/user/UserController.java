package fr.esgi.components.user;

import entities.Role;
import entities.User;
import entities.UserAuthenticator;
import fr.esgi.components.cart.adapter.CartApiAdapter;
import fr.esgi.components.cart.dto.CartDto;
import fr.esgi.components.store.adapter.StoreApiAdapter;
import fr.esgi.components.store.dto.StoreDto;
import fr.esgi.components.user.adapter.RoleApiAdapter;
import fr.esgi.components.user.adapter.UserApiAdapter;
import fr.esgi.components.user.dto.RoleDto;
import fr.esgi.components.user.dto.UserDetailsDto;
import fr.esgi.components.user.dto.UserDto;
import fr.esgi.exception.*;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.carts.CartService;
import fr.esgi.services.stores.StoreService;
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
    CartData cartData;

    private final
    StoreData storeData;

    private final
    UserAuthenticatorData userAuthenticatorData;

    private final
    UserService userService;

    private final
    CartService cartService;

    private final
    StoreService storeService;

    private final
    UserApiAdapter userApiAdapter;

    private final
    RoleApiAdapter roleApiAdapter;

    private final
    CartApiAdapter cartApiAdapter;

    private final
    StoreApiAdapter storeApiAdapter;

    @Autowired
    public UserController(UserAuthenticatorData userAuthenticatorData, UserData userData, CartData cartData, StoreData storeData, UserService userService, CartService cartService, StoreService storeService, UserApiAdapter userApiAdapter, RoleApiAdapter roleApiAdapter, CartApiAdapter cartApiAdapter, StoreApiAdapter storeApiAdapter) {
        this.userData = userData;
        this.cartData = cartData;
        this.storeData = storeData;
        this.userService = userService;
        this.cartService = cartService;
        this.storeService = storeService;
        this.userApiAdapter = userApiAdapter;
        this.roleApiAdapter = roleApiAdapter;
        this.userAuthenticatorData = userAuthenticatorData;
        this.cartApiAdapter = cartApiAdapter;
        this.storeApiAdapter = storeApiAdapter;
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
    @GetMapping("/{userId}/carts")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public List<CartDto> getCarts(@PathVariable(value="userId") int userId) {
        try {
            return cartApiAdapter.convertModelsToDtos(cartService.getByUserid(cartData, userId));
        } catch (CartNotFoundException c){
            throw new CartNotFoundExceptionApi(c.getMessage());
        }
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

    /**
     * Permet la récupération de la liste de magasin par userId
     * @param userId ID du utilisateur
     * @return la liste en detail des magasins
     */
    @GetMapping("/stores/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<StoreDto> getStores(@PathVariable(value="userId") int userId) {
        try{
            return storeApiAdapter.convertModelsToDto(storeService.getByUserId(storeData,userId));
        } catch (StoreNotFoundException ex){
            throw new StoreNotFoundExceptionApi(ex.getMessage());
        }
    }
}
