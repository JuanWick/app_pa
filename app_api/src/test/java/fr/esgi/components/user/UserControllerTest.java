package fr.esgi.components.user;

import entities.UserAuthenticator;
import fr.esgi.ApiConfiguration;
import fr.esgi.components.cart.CartController;
import fr.esgi.components.cart.adapter.CartApiAdapter;
import fr.esgi.components.security.dto.RegisterResponse;
import fr.esgi.components.security.dto.SignUpRequest;
import fr.esgi.components.user.adapter.RoleApiAdapter;
import fr.esgi.components.user.adapter.UserApiAdapter;
import fr.esgi.components.user.dto.UserDetailsDto;
import fr.esgi.config.SecurityConfig;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.users.RoleRepository;
import fr.esgi.reporitories.users.UserRepository;
import fr.esgi.reporitories.users.dao.TRoleEntity;
import fr.esgi.reporitories.users.dao.TUserAuthenticator;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.carts.CartService;
import fr.esgi.services.users.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ApiConfiguration.class, SecurityConfig.class})
public class UserControllerTest {

    @Autowired
    UserData userData;

    @Autowired
    UserAuthenticatorData userAuthenticatorData;

    @Autowired
    UserService userService;

    @Autowired
    UserApiAdapter userApiAdapter;

    @Autowired
    RoleApiAdapter roleApiAdapter;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthController authController;

    UserController userController;

    Integer userId;

    public UserControllerTest() {
    }

    @Before
    public void init(){
        if(!roleRepository.existsById(1)){
            TRoleEntity roleEntity = new TRoleEntity();
            roleEntity.setName("USER");
            roleRepository.save(roleEntity);
        }

        userController = new UserController(userAuthenticatorData,userData, userService, userApiAdapter, roleApiAdapter);
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("test@test.fr")
                .firstname("Lisa")
                .name("Crispin")
                .password("Azerty2018")
                .roleId(1)
                .username("test_username")
                .build();

       ResponseEntity responseEntity = authController.registerUser(signUpRequest);
       RegisterResponse registerResponse = (RegisterResponse) responseEntity.getBody();
        userId = registerResponse.getUserId();
    }

    @Test
    @Transactional
    public void should_get_user_details_by_id(){
            UserDetailsDto userDetailsDto = userController.getById(userId);
            Assert.assertNotNull(userDetailsDto);
            Assert.assertEquals("Lisa",userDetailsDto.getFirstName());
            Assert.assertEquals("test@test.fr",userDetailsDto.getMail());
            Assert.assertEquals("CRISPIN",userDetailsDto.getName());
            Assert.assertEquals("test_username",userDetailsDto.getUsername());
            Assert.assertEquals(userId.intValue(),userDetailsDto.getId().intValue());
    }
}
