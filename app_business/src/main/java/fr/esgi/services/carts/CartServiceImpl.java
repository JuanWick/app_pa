package fr.esgi.services.carts;

import entities.Cart;
import entities.Product;
import entities.User;
import fr.esgi.exception.CartNotFoundException;
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.users.services.UserData;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    @Override
    public Cart save(CartData cartData, Cart cart) {
        return cartData.save(cart);
    }

    @Override
    public Cart getById(CartData cartData, Integer cartId) throws CartNotFoundException{
        Cart cart = cartData.getById(cartId);
        if(null != cart){
            return cart;
        } else {
            throw new CartNotFoundException();        }
    }

    @Override
    public Cart createCart(UserData userData, CartData cartData, Integer userId) throws UserNotFoundException {
        User user = userData.getById(userId);
        if(null != user){
            Cart cart = new Cart();
            cart.setUser(user);
            return cartData.save(cart);
        } else {
            throw new UserNotFoundException();        }
    }

    @Override
    public void delete(CartData cartData, Integer cartId) throws CartNotFoundException{
        if(null != getById(cartData,cartId)){
            cartData.delete(cartId);
        }
    }

    @Override
    public Cart addProducts(CartData cartData, ProductData productData, Integer cartId, List<Integer> productsId) throws ProductNotFoundException, CartNotFoundException  {
        Cart cart = cartData.getById(cartId);

        if(null != cart){
            ArrayList newProducts = new ArrayList();

            for(Integer id:productsId){
                // On verifie que le produit existe en persistence
                if(null != productData.getById(id)){
                    //On vérifie que le produit n'est pas déjà présent
                    if(null != cart.getProducts()){
                        boolean isPresent = false;
                        for(Product existingProduct : cart.getProducts()){
                            if(existingProduct.getId().equals(id)){
                                isPresent = true;
                            }
                        }
                        if(!isPresent){
                            newProducts.add(Product.builder().id(id).build());
                        }
                    }
                } else {
                    throw new ProductNotFoundException();
                }

            }
            List<Product> updatedProducts = new ArrayList<>();
            if(null != cart.getProducts()){
                updatedProducts.addAll(cart.getProducts());
            }
            updatedProducts.addAll(newProducts);
            cart.setProducts(updatedProducts);
            cartData.save(cart);
            return cartData.getById(cartId);
        } else {
            throw new CartNotFoundException();
        }
    }

    @Override
    public void deleteProduct(CartData cartData, ProductData productData, Integer cartId, Integer productId) throws CartNotFoundException, ProductNotFoundException {
        //On vérifie que le cart existe
        if( null == cartData.getById(cartId)){
            throw new CartNotFoundException();
        }
        //On vérifie que le produit existe
        if(null == productData.getById(productId)){
            throw new ProductNotFoundException();
        }

        //On vérifie que le produit existe dans le panier
        if(!cartData.deleteProduct(cartId,productId)){
            throw new ProductNotFoundException("Erreur : Produit absent du panier");
        }
    }
}
