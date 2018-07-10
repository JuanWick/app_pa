//package fr.esgi.components.user;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import fr.esgi.ApiConfiguration;
//import fr.esgi.components.security.dto.SignUpRequest;
//import fr.esgi.components.user.adapter.RoleApiAdapter;
//import fr.esgi.components.user.adapter.UserApiAdapter;
//import fr.esgi.components.user.dto.UserDetailsDto;
//import fr.esgi.config.SecurityConfig;
//import fr.esgi.reporitories.users.RoleRepository;
//import fr.esgi.reporitories.users.dao.TRoleEntity;
//import fr.esgi.reporitories.users.services.UserAuthenticatorData;
//import fr.esgi.reporitories.users.services.UserData;
//import fr.esgi.services.users.UserService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@ContextConfiguration(classes = {ApiConfiguration.class, SecurityConfig.class})
//public class AuthControllerTest {
//
//    @Autowired
//    UserData userData;
//
//    @Autowired
//    UserAuthenticatorData userAuthenticatorData;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserApiAdapter userApiAdapter;
//
//    @Autowired
//    RoleApiAdapter roleApiAdapter;
//
//    @Autowired
//    UserController userController;
//
//    @Autowired
//    AuthController authController;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    public AuthControllerTest() {
//    }
//    @Before
//    public void init(){
//        if(!roleRepository.existsById(1)){
//            TRoleEntity roleEntity = new TRoleEntity();
//            roleEntity.setName("USER");
//            roleRepository.save(roleEntity);
//        }
//    }
//
//    @Test
//    public void should_sign_in_user(){
//        SignUpRequest signUpRequest = SignUpRequest.builder()
//                .email("test2@test.fr")
//                .firstname("Grace")
//                .name("hopper")
//                .password("Azerty2018")
//                .roleId(1)
//                .username("test_hopper")
//                .build();
//
//        ResponseEntity response = authController.registerUser(signUpRequest);
//        Assert.assertEquals(201, response.getStatusCode().value());
//    }
//}
