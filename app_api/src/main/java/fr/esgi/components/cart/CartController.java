package fr.esgi.components.cart;

import entities.Cart;
import fr.esgi.components.cart.adapter.CartApiAdapter;
import fr.esgi.components.cart.dto.CartAskDetailsDto;
import fr.esgi.components.cart.dto.CartDetailsDto;
import fr.esgi.components.cart.dto.CartDto;
import fr.esgi.components.cart.dto.CartProductsAddDto;
import fr.esgi.exception.*;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.carts.CartService;
import fr.esgi.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 6000)
@RequestMapping("/carts")
public class CartController {

    private final
    CartData cartData;

    private final
    UserData userData;

    private final
    StoreData storeData;

    private final
    ProductData productData;

    private final
    CartService cartService;

    private final
    ProductService productService;


    private final
    CartApiAdapter cartApiAdapter;

    @Autowired
    public CartController(CartData cartData, UserData userData, StoreData storeData, ProductData productData, CartService cartService, ProductService productService, CartApiAdapter cartApiAdapter) {
        this.cartData = cartData;
        this.userData = userData;
        this.storeData = storeData;
        this.productData = productData;
        this.cartService = cartService;
        this.productService = productService;
        this.cartApiAdapter = cartApiAdapter;
    }

    /**
     * Création d'un panier à partir de l'ID de l'utilisateur passé en paramètre.
     * @param userId de l'utilisateur
     * @return ID du panier crée
     */
    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public Integer create(@PathVariable(value="userId") int userId){

        try {
            return cartService.createCart(userData,cartData,userId).getId();
        } catch (UserNotFoundException e) {
            throw new UserNotFoundExceptionApi(e.getMessage());
        }
    }

    /**
     * Récupération d'un panier à partir de son ID
     * @param cartId du panier
     * @return ID de l'utilisateur possédant le panier et liste des ID des produits
     */
    @GetMapping("/{cartId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public CartDto getById(@PathVariable(value="cartId") int cartId) {
        try {
            return cartApiAdapter.convertToDto(cartService.getById(cartData,cartId));
        }catch (CartNotFoundException e) {
            throw new CartNotFoundExceptionApi(e.getMessage());
        }
    }

    @PostMapping("/details")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public CartDetailsDto getCartDetails(@RequestBody CartAskDetailsDto cartAskDetailsDto){
        try {
            CartDto cartDto = cartApiAdapter.convertToDto(cartService.getById(cartData, cartAskDetailsDto.getCartId()));
            return cartApiAdapter.convertToCartDetailsDto(
                    cartService.getByIdWithSearch(
                            cartData,
                            storeData,
                            productData,
                            productService,
                            cartAskDetailsDto.getCartId(),
                            cartAskDetailsDto.getLatitude(),
                            cartAskDetailsDto.getLongitude(),
                            cartAskDetailsDto.getPerimeter()),
                    cartAskDetailsDto.getCartId(),
                    cartDto.getSharedusers()
            );
        } catch (CartNotFoundException c){
          throw new CartNotFoundExceptionApi(c.getMessage());
        }
    }

    /**
     * Permet la suppression d'un panier
     * @param cartId, id du panier
     */
    @DeleteMapping("/{cartId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public void delete(@PathVariable(value="cartId") int cartId){
        try {
            cartService.delete(cartData,cartId);
        } catch (CartNotFoundException e){
            throw new CartNotFoundExceptionApi(e.getMessage());
        }
    }

    /**
     * Permet l'ajout de produit à un panier
     * @param cartProductsAddDto, liste des produits à ajouter au Panier
     */
    @PostMapping("/{cartId}/products")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public CartDto addProducts(@PathVariable(value="cartId") int cartId,@RequestBody final CartProductsAddDto cartProductsAddDto){
        try {
            return cartApiAdapter.convertToDto(
                    cartService.addProducts(cartData, productData, cartId, cartProductsAddDto.getProductsId()));
        } catch(CartNotFoundException e){
            throw new CartNotFoundExceptionApi(e.getMessage());
        } catch (ProductNotFoundException p){
            throw new ProductNotFoundExceptionApi(p.getMessage());
        }
    }

    /**
     * Permet la suppression d'un produit d'un panier
     * @param cartId, id du panier
     * @param productId, id du produit à supprimer
     */
    @DeleteMapping("/{cartId}/products/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public void deleteProduct(@PathVariable(value="cartId") int cartId,@PathVariable(value="productId") int productId){
        try {
            cartService.deleteProduct(cartData, productData, cartId,productId);
        } catch(CartNotFoundException c){
            throw new CartNotFoundExceptionApi(c.getMessage());
        } catch (ProductNotFoundException p){
            throw new ProductNotFoundExceptionApi(p.getMessage());
        }
    }
}
