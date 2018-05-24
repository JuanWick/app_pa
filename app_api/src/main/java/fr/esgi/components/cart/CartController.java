package fr.esgi.components.cart;

import entities.Cart;
import fr.esgi.components.cart.adapter.CartApiAdapter;
import fr.esgi.components.cart.dto.CartDto;
import fr.esgi.components.cart.dto.CartProductsAddDto;
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
    UserData userData;

    private final
    CartService cartService;

    private final
    CartApiAdapter cartApiAdapter;

    @Autowired
    public CartController(CartData cartData, UserData userData, CartService cartService, CartApiAdapter cartApiAdapter) {
        this.cartData = cartData;
        this.userData = userData;
        this.cartService = cartService;
        this.cartApiAdapter = cartApiAdapter;
    }

    /**
     * Création d'un panier à partir de l'ID de l'utilisateur passé en paramètre.
     * @param userId de l'utilisateur
     * @return ID du panier crée
     */
    @PostMapping("/{userId}")
    public Integer create(@PathVariable(value="userId") int userId){
        return cartService.createCart(userData,cartData,userId).getId();
    }

    /**
     * Récupération d'un panier à partir de son ID
     * @param cartId du panier
     * @return ID de l'utilisateur possédant le panier et liste des ID des produits
     */
    @GetMapping("/{cartId}")
    public CartDto getById(@PathVariable(value="cartId") int cartId) {
        Cart cart = cartData.getById(cartId);
        CartDto cartDto = null;

        if(null != cart){
            cartDto =  cartApiAdapter.convertToDto(cart);
        }
        return cartDto;
    }

    /**
     * Permet la suppression d'un panier
     * @param cartId, id du panier
     */
    @DeleteMapping("/{cartId}")
    public void delete(@PathVariable(value="cartId") int cartId){
        cartService.delete(cartData,cartId);
    }

    /**
     * Permet l'ajout de produit à un panier
     * @param cartProductsAddDto, liste des produits à ajouter au Panier
     */
    @PostMapping("/{cartId}/products")
    public CartDto addProducts(@PathVariable(value="cartId") int cartId,@RequestBody final CartProductsAddDto cartProductsAddDto){
        CartDto cartDto = null;
        Cart cart = cartService.addProducts(cartData,cartId,cartProductsAddDto.getProductsId());

        if(null != cart){
            cartDto = cartApiAdapter.convertToDto(cart);
        }
        return cartDto;
    }

    /**
     * Permet la suppression d'un produit d'un panier
     * @param cartId, id du panier
     * @param productId, id du produit à supprimer
     */
    @DeleteMapping("/{cartId}/products/{productId}")
    public void deleteProduct(@PathVariable(value="cartId") int cartId,@PathVariable(value="productId") int productId){
        cartService.deleteProduct(cartData,cartId,productId);
    }
}
