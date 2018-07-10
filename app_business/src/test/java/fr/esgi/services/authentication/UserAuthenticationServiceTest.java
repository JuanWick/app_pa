package fr.esgi.services.authentication;

import entities.User;
import entities.UserAuthenticator;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.mockito.Mockito;

public class UserAuthenticationServiceTest extends TestCase {

    UserAuthenticatorData userAuthenticatorData = Mockito.mock(UserAuthenticatorData.class);
    UserData userData = Mockito.mock(UserData.class);

    UserAuthenticationService userAuthenticationService;
    UserAuthenticator userAuthenticator;

    public UserAuthenticationServiceTest( String testName ) { super( testName );}
    public static Test suite() { return new TestSuite( UserAuthenticationServiceTest.class ); }

    private void init(){
        userAuthenticator = new UserAuthenticator();
        userAuthenticator.setEmail("email");
        userAuthenticator.setId(1);
        userAuthenticator.setLogin("login");
        User user = new User();
        user.setId(1);
        userAuthenticator.setUser(user);
        userAuthenticationService = new UserAuthenticationServiceImpl();
    }

    public void test_should_find_by_id()
    {
        init();
        Mockito.when(userAuthenticatorData.findById(1)).thenReturn(userAuthenticator);
        UserAuthenticator user = userAuthenticationService.findById(userAuthenticatorData,1l);
        Assert.assertNotNull(user);
    }

    public void test_should_not_find_by_id()
    {
        init();
        UserAuthenticator user = userAuthenticationService.findById(userAuthenticatorData,2l);
        Assert.assertNull(user);
    }

    public void test_should_find_by_username()
    {
        init();
        Mockito.when(userAuthenticatorData.findByUsername("login")).thenReturn(userAuthenticator);
        Mockito.when(userData.getById(1)).thenReturn(userAuthenticator.getUser());
        UserAuthenticator user = userAuthenticationService.findByUserNameOrEmail(userData,userAuthenticatorData, "login");
        Assert.assertNotNull(user);
    }

    public void test_should_not_find_by_username()
    {
        init();
        Mockito.when(userAuthenticatorData.findByUsername("login")).thenReturn(userAuthenticator);
        Mockito.when(userData.getById(1)).thenReturn(userAuthenticator.getUser());
        try{
            UserAuthenticator user = userAuthenticationService.findByUserNameOrEmail(userData,userAuthenticatorData, "Nok");
            fail("Exception UserNotFoundException non levé");
        } catch (UserNotFoundException ex){

        }
    }

    public void test_should_find_by_mail()
    {
        init();
        Mockito.when(userAuthenticatorData.findByEmail("email")).thenReturn(userAuthenticator);
        Mockito.when(userData.getById(1)).thenReturn(userAuthenticator.getUser());
        UserAuthenticator user = userAuthenticationService.findByUserNameOrEmail(userData,userAuthenticatorData, "email");
        Assert.assertNotNull(user);
    }

    public void test_should_not_find_by_mail()
    {
        init();
        Mockito.when(userAuthenticatorData.findByEmail("email")).thenReturn(userAuthenticator);
        Mockito.when(userData.getById(1)).thenReturn(userAuthenticator.getUser());
        try{
            UserAuthenticator user = userAuthenticationService.findByUserNameOrEmail(userData,userAuthenticatorData, "Nok");
            fail("Exception UserNotFoundException non levé");
        } catch (UserNotFoundException ex){

        }
    }
}
