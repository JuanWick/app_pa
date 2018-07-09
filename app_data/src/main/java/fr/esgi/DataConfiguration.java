package fr.esgi;

import fr.esgi.reporitories.carts.adapter.CartDataAdapter;
import fr.esgi.reporitories.products.adapter.CategoryDataAdapter;
import fr.esgi.reporitories.products.adapter.ProductDataAdapter;
import fr.esgi.reporitories.stores.adapter.StoreDataAdapter;
import fr.esgi.reporitories.users.adapter.RoleDataAdapter;
import fr.esgi.reporitories.users.adapter.UserAuthenticatorDataAdapter;
import fr.esgi.reporitories.users.adapter.UserDataAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {

    /** Adapters **/
    @Bean
    public StoreDataAdapter StoreAdapter(){
        return new StoreDataAdapter();
    }

    @Bean
    public UserDataAdapter UserAdapter(){ return new UserDataAdapter(); }

    @Bean
    public CartDataAdapter CartAdapter(){
        return new CartDataAdapter();
    }

    @Bean
    public ProductDataAdapter ProductAdapter(){
        return new ProductDataAdapter();
    }

    @Bean
    public CategoryDataAdapter CategoryAdapter(){
        return new CategoryDataAdapter();
    }

    @Bean
    public RoleDataAdapter RoleAdapter(){
        return new RoleDataAdapter();
    }

    @Bean
    public UserAuthenticatorDataAdapter UserAuthenticatorDataAdapter(){return new UserAuthenticatorDataAdapter();}

}
