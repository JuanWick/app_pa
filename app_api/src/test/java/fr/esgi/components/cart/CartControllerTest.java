//package fr.esgi.components.cart;
//
//import fr.esgi.ApiConfiguration;
//import fr.esgi.components.cart.adapter.CartApiAdapter;
//import fr.esgi.reporitories.carts.services.CartData;
//import fr.esgi.reporitories.products.services.ProductData;
//import fr.esgi.reporitories.users.services.UserData;
//import fr.esgi.services.carts.CartService;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@ContextConfiguration(classes = ApiConfiguration.class)
//public class CartControllerTest {
//
//    @Autowired
//    CartData cartData;
//
//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    UserData userData;
//
//    @Autowired
//    ProductData productData;
//
//    @Autowired
//    CartApiAdapter cartApiAdapter;
//
//    public CartControllerTest() {
//    }
//
//
//
//    @Test
//    @Transactional
//    @Ignore
//    public void should_add_cart(){
//        CartController cartController = new CartController(cartData,userData,productData,cartService,cartApiAdapter);
//        Assert.assertNotNull( cartController.getById(8));
//    }
//}
