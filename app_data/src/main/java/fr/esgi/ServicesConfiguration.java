package fr.esgi;

import entities.Category;
import fr.esgi.reporitories.carts.adapter.CartAdapter;
import fr.esgi.reporitories.products.adapter.CategoryAdapter;
import fr.esgi.reporitories.products.adapter.ProductAdapter;
import fr.esgi.reporitories.stores.adapter.StoreAdapter;
import fr.esgi.reporitories.users.adapter.RoleAdapter;
import fr.esgi.reporitories.users.adapter.UserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    /** Adapters **/
    @Bean
    public StoreAdapter StoreAdapter(){
        return new StoreAdapter();
    }

    @Bean
    public UserAdapter UserAdapter(){
        return new UserAdapter();
    }

    @Bean
    public CartAdapter CartAdapter(){
        return new CartAdapter();
    }

    @Bean
    public ProductAdapter ProductAdapter(){
        return new ProductAdapter();
    }

    @Bean
    public CategoryAdapter CategoryAdapter(){
        return new CategoryAdapter();
    }

    @Bean
    public RoleAdapter RoleAdapter(){
        return new RoleAdapter();
    }

}
