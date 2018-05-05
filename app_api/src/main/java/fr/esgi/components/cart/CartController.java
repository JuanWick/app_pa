package fr.esgi.components.cart;

import entities.Cart;
import fr.esgi.components.cart.adapter.CartDtoAdapter;
import fr.esgi.components.cart.dto.CartDto;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.carts.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final
    CartData cartData;

    private final
    CartService cartService;

    private final
    CartDtoAdapter cartDtoAdapter;

    @Autowired
    public CartController(CartData cartData, CartService cartService, CartDtoAdapter cartDtoAdapter) {
        this.cartData = cartData;
        this.cartService = cartService;
        this.cartDtoAdapter = cartDtoAdapter;
    }

    /**
     * Création d'un panier à partir de l'ID de l'utilisateur passé en paramètre.
     * @param id de l'utilisateur
     * @return ID du panier crée
     */
    @PostMapping("/{id}")
    public Integer createCart(@PathVariable(value="id") int id){
        return cartService.createCart(cartData,id).getId();
    }

    /**
     * Récupération d'un panier à partir de son ID
     * @param id du panier
     * @return ID de l'utilisateur possédant le panier et liste des ID des produits
     */
    @GetMapping("/{id}")
    public CartDto getById(@PathVariable(value="id") int id) {
        Cart cart = cartData.getById(id);
        CartDto cartDto = null;

        if(null != cart){
            cartDto =  cartDtoAdapter.convertToDto(cart);
        }
        return cartDto;
    }
}
