package fr.esgi.services.product;

import entities.Category;
import entities.Product;
import entities.Store;
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest extends TestCase {

    ProductData productData = Mockito.mock(ProductData.class);
    StoreData storeData = Mockito.mock(StoreData.class);
    ProductService productService;
    Product product1;

    private void init(){
        product1 = Product.builder()
                .barreCode("BARRE_CODE")
                .id(1)
                .name("Nom_produit1").build();

        productService = new ProductServiceImpl();
    }

    public ProductServiceTest( String testName ) { super( testName );}
    public static Test suite() { return new TestSuite( ProductServiceTest.class ); }

    public void test_should_get_product_by_id()
    {
        init();
        Mockito.when(productData.getById(1)).thenReturn(product1);

        ProductService productService = new ProductServiceImpl();
        Product product = productService.getById(productData,1);
        Assert.assertNotNull(product);
    }

    public void test_should_not_get_product_by_id()
    {
        init();
        Mockito.when(productData.getById(0)).thenReturn(null);
        try {
            Product product = productService.getById(productData,0);
            fail("Exception ProductNotFoundException non levé");
        } catch (ProductNotFoundException p){

        }
    }

    public void test_should_get_product_by_name()
    {
        init();
        Mockito.when(productData.getByName("Nom_produit1")).thenReturn(product1);

        ProductService productService = new ProductServiceImpl();
        Product product = productService.getByName(productData,"Nom_produit1");
        Assert.assertNotNull(product);
    }

    public void test_should_not_get_product_by_name()
    {
        init();
        Mockito.when(productData.getByName("inconnu")).thenReturn(null);
        try {
            Product product = productService.getByName(productData,"inconnu");
            fail("Exception ProductNotFoundException non levé");
        } catch (ProductNotFoundException p){

        }
    }

    public void test_should_get_product_by_barrecode()
    {
        init();
        Mockito.when(productData.getByBarreCode("BARRE_CODE")).thenReturn(product1);

        ProductService productService = new ProductServiceImpl();
        Product product = productService.getByBarreCode(productData,"BARRE_CODE");
        Assert.assertNotNull(product);
    }

    public void test_should_not_get_product_by_barrecode()
    {
        init();
        Mockito.when(productData.getByBarreCode("inconnu")).thenReturn(null);
        try {
            Product product = productService.getByBarreCode(productData,"inconnu");
            fail("Exception ProductNotFoundException non levé");
        } catch (ProductNotFoundException p){

        }
    }

    public void test_should_return_products_by_value(){
        productService = new ProductServiceImpl();

        Product productOk = Product.builder().name("test").build();
        Product productNoK = Product.builder().name("NOK").build();

        Store store1 = Store.builder()
                .id(1)
                .latitude(48.85661011756004)
                .longitude(2.351807498707217)
                .build();

        Store store2 = Store.builder()
                .id(2)
                .latitude(48.85722859635931)
                .longitude(2.349572719552384)
                .build();

        Store store3 = Store.builder()
                .id(3)
                .latitude(48.85797039867159)
                .longitude(2.346873533445091)
                .build();

        Store store4 = Store.builder()
                .id(4)
                .latitude(48.85797039867159)
                .longitude(2.346873533445091)
                .build();

        List<Object[]> bddResult = new ArrayList<>();

        Object[] object1 = new Object[2];
        object1[0] = store1;
        object1[1] = productOk;
        bddResult.add(object1);

        Object[] object2 = new Object[2];
        object2[0] = store2;
        object2[1] = productNoK;
        bddResult.add(object2);

        Object[] object3 = new Object[2];
        object3[0] = store3;
        object3[1] = productOk;
        bddResult.add(object3);

        Object[] object4 = new Object[2];
        object4[0] = store4;
        object4[1] = productOk;
        bddResult.add(object4);

        Mockito.when(productData.getStoresWithProductValue("test")).thenReturn(bddResult);
        Mockito.when(storeData.getById(1)).thenReturn(store1);
        Mockito.when(storeData.getById(2)).thenReturn(store2);
        Mockito.when(storeData.getById(3)).thenReturn(store3);
        Mockito.when(storeData.getById(4)).thenReturn(store4);

        List<Object[]> results = productService.searchByValue(storeData,productData,"test",48.85661400000001,2.3522219000000177,50.0);

       Assert.assertEquals(1,results.size());
    }

    public void test_should_return_products_by_category(){
        productService = new ProductServiceImpl();
        Category ok = new Category();
        ok.setName("test");
        Category nok = new Category();
        nok.setName("nok");

        ArrayList<Category> okList = new ArrayList<>();
        okList.add(ok);

        ArrayList<Category> nokList = new ArrayList<>();
        nokList.add(nok);

        Product productOk = Product.builder().categories(okList).build();
        Product productNoK = Product.builder().categories(nokList).build();

        Store store1 = Store.builder()
                .id(1)
                .latitude(48.85661011756004)
                .longitude(2.351807498707217)
                .build();

        Store store2 = Store.builder()
                .id(2)
                .latitude(48.85722859635931)
                .longitude(2.349572719552384)
                .build();

        Store store3 = Store.builder()
                .id(3)
                .latitude(48.85797039867159)
                .longitude(2.346873533445091)
                .build();

        Store store4 = Store.builder()
                .id(4)
                .latitude(48.85797039867159)
                .longitude(2.346873533445091)
                .build();

        List<Object[]> bddResult = new ArrayList<>();

        Object[] object1 = new Object[2];
        object1[0] = store1;
        object1[1] = productOk;
        bddResult.add(object1);

        Object[] object2 = new Object[2];
        object2[0] = store2;
        object2[1] = productNoK;
        bddResult.add(object2);

        Object[] object3 = new Object[2];
        object3[0] = store3;
        object3[1] = productOk;
        bddResult.add(object3);

        Object[] object4 = new Object[2];
        object4[0] = store4;
        object4[1] = productOk;
        bddResult.add(object4);

        Mockito.when(productData.getStoresWithProductCategory("test")).thenReturn(bddResult);
        Mockito.when(storeData.getById(1)).thenReturn(store1);
        Mockito.when(storeData.getById(2)).thenReturn(store2);
        Mockito.when(storeData.getById(3)).thenReturn(store3);
        Mockito.when(storeData.getById(4)).thenReturn(store4);

        List<Object[]> results = productService.searchByCategorie(storeData,productData,"test",48.85661400000001,2.3522219000000177,50.0);

        Assert.assertEquals(1,results.size());
    }
}
