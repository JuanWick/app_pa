package fr.esgi.services.carts;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

public class CartServiceTest extends TestCase {

    public CartServiceTest( String testName ) { super( testName );}
    public static Test suite() { return new TestSuite( CartServiceTest.class ); }

    private void init(){

    }

    public void test_should_get_by_id(){
        Assert.assertTrue(true);
    }

    public void test_should_not_get_by_id(){
        Assert.assertTrue(true);
    }

    public void test_should_create_cart(){
        Assert.assertTrue(true);
    }

    public void test_should_not_create_cart(){
        Assert.assertTrue(true);
    }
}
