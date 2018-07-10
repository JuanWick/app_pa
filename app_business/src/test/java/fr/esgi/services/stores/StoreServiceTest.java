package fr.esgi.services.stores;

import fr.esgi.services.carts.CartServiceTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

public class StoreServiceTest  extends TestCase {
    public StoreServiceTest( String testName ) { super( testName );}
    public static Test suite() { return new TestSuite( StoreServiceTest.class ); }

    private void init(){

    }

    public void test_should_get_product_by_storeId(){
        Assert.assertTrue(true);
    }

    public void test_should_not_get_product_by_storeId(){
        Assert.assertTrue(true);
    }

    public void test_should_get_by_storeId(){
        Assert.assertTrue(true);
    }

    public void test_should_not_get_by_storeId(){
        Assert.assertTrue(true);
    }
}
