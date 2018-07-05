package fr.esgi.components.cart;

import entities.Cart;
import entities.User;
import fr.esgi.components.cart.adapter.CartApiAdapter;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.carts.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private
    CartData cartData;

    @MockBean
    private UserData userData;

    @MockBean
    private
    CartService cartService;

    @MockBean
    private
    CartApiAdapter cartApiAdapter;
/*
    @Test
    public void should_get_cart_by_id()
            throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Name");
        user.setFirstName("FirstName");

        Cart cart = new Cart();
        cart.setId(2);
        cart.setUser(user);

        given(cartService.createCart(userData, cartData,1)).willReturn(cart);

        mvc.perform(get("/carts/1")
                .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isOk());
               /* .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(2)));
    }*/
}
