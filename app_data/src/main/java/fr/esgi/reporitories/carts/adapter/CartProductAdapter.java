package fr.esgi.reporitories.carts.adapter;

import entities.Cart;
import entities.Product;
import fr.esgi.reporitories.carts.dao.RCartProductEntity;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CartProductAdapter {

    List<Product> adapt(Collection<RCartProductEntity> cartProducts){
        List<Product> products = new ArrayList<>();

        for(RCartProductEntity entity:cartProducts){
            TProductEntity productEntity = entity.gettProductByProductId();

            Product currentProduct = new Product();
            currentProduct.setId(productEntity.getId());
            currentProduct.setBarreCode(productEntity.getBarreCode());
            currentProduct.setInfo(productEntity.getInfo());
            currentProduct.setName(productEntity.getName());

            products.add(currentProduct);
        }
        return products;
    }

    Collection<RCartProductEntity> convert(Cart cart){
        ArrayList<RCartProductEntity> products = new ArrayList<>();

        for(Product product:cart.getProducts()){
            RCartProductEntity rCartProductEntity = new RCartProductEntity();

            TCartEntity cartEntity = new TCartEntity();
            cartEntity.setId(cart.getId());
            rCartProductEntity.settCartByCartId(cartEntity);

            TProductEntity productEntity = new TProductEntity();
            productEntity.setId(product.getId());
            rCartProductEntity.settProductByProductId(productEntity);

            products.add(rCartProductEntity);
        }
        return products;
    }
}
