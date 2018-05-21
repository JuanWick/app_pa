package fr.esgi.services.carts;

import entities.Cart;
import entities.Product;
import entities.User;
import fr.esgi.reporitories.carts.services.CartData;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    @Override
    public Cart save(CartData cartData, Cart cart) {
        return cartData.save(cart);
    }

    @Override
    public Cart createCart(CartData cartData, Integer userId) {
        User user = new User();
        user.setId(userId);

        Cart cart = new Cart();
        cart.setUser(user);
        return cartData.save(cart);
    }

    @Override
    public void delete(CartData cartData, Integer cartId) {
        cartData.delete(cartId);
    }

    @Override
    public Cart addProducts(CartData cartData, Integer cartId, List<Integer> productsId) {
        Cart cart = cartData.getById(cartId);

        if(null != cart){
            ArrayList newProducts = new ArrayList();

            for(Integer id:productsId){
                Product product = Product.builder().id(id).build();

                newProducts.add(product);
            }
            List<Product> updatedProducts = new ArrayList<>();
            if(null != cart.getProducts()){
                updatedProducts.addAll(cart.getProducts());
            }
            updatedProducts.addAll(newProducts);
            cart.setProducts(updatedProducts);
            cartData.save(cart);
            cart = cartData.getById(cartId);
        }
        return cart;
    }

    @Override
    public void deleteProduct(CartData cartData, Integer cartId, Integer productId) {
        cartData.deleteProduct(cartId,productId);
    }
}
