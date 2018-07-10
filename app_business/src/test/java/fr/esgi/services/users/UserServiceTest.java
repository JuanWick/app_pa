package fr.esgi.services.users;

import fr.esgi.services.stores.StoreServiceTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

public class UserServiceTest extends TestCase {
    public UserServiceTest( String testName ) { super( testName );}
    public static Test suite() { return new TestSuite( UserServiceTest.class ); }

    private void init(){

    }

    public void test_should_get_by_id(){ Assert.assertTrue(true);}

    public void test_should_not_get_by_id(){ Assert.assertTrue(true);}

    public void test_should_get_Role_by_id(){ Assert.assertTrue(true);}

    public void test_should_not_get_Role_by_id(){ Assert.assertTrue(true);}
}
