//package fr.esgi.components.user;
//
//import entities.User;
//import fr.esgi.components.user.adapter.UserDtoAdapter;
//import fr.esgi.components.user.dto.UserDto;
//import fr.esgi.reporitories.users.services.UserData;
//import fr.esgi.services.users.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private final
//    UserData userData;
//
//    private final
//    UserService userService;
//
//    private final
//    UserDtoAdapter userDtoAdapter;
//
//    @Autowired
//    public UserController(UserData userData, UserService userService, UserDtoAdapter userDtoAdapter) {
//        this.userData = userData;
//        this.userService = userService;
//        this.userDtoAdapter = userDtoAdapter;
//    }
//
//    /**
//     *  Permet de récupérer les informations d'un utilisateur par ID
//     * @param id de l'utilisateur
//     * @return UserDto
//     */
//    @GetMapping("/{id}")
//    public UserDto getById(@PathVariable(value="id") int id) {
//        User user = userData.getById(id);
//        UserDto userDto = null;
//
//        if(null != user){
//            userDto =  userDtoAdapter.convertToDto(user);
//        }
//        return userDto;
//    }
//
//    /*
//      {
//      "id": null,
//      "name": "SIMPSON",
//      "firstName": "Bart"
//      }
//   */
//    @PostMapping("")
//    public User addOrUpdate(@RequestBody final User user){
//        return userData.save(user);
//    }
//}
