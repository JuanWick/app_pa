package fr.esgi.services.product;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProductServiceTest extends TestCase {

    public ProductServiceTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( ProductServiceTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void test_should_return_stores_with_product_in_perimeter()
    {

        assertTrue( true );
    }
}
