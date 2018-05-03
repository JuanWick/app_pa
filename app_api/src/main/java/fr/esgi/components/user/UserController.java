package fr.esgi.components.user;

import entities.Store;
import entities.User;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.stores.StoreService;
import fr.esgi.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserData userData;

    @GetMapping("/{id}")
    public User getById(@PathVariable(value="id") int id) {
        return userService.getById(userData,id);
    }

    /*
      {
      "id": null,
      "name": "SIMPSON",
      "firstName": "Bart"
      }
   */
    @PostMapping("")
    public User addOrUpdate(@RequestBody final User user){
        System.out.println("RECU : "+user.toString());
        return userData.save(user);
    }
}
