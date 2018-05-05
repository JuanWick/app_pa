package fr.esgi;

import fr.esgi.reporitories.carts.adapter.CartAdapter;
import fr.esgi.reporitories.carts.adapter.CartProductAdapter;
import fr.esgi.reporitories.carts.adapter.SharedCartAdapter;
import fr.esgi.reporitories.stores.adapter.StoreAdapter;
import fr.esgi.reporitories.stores.services.HibernateStoreData;
import fr.esgi.reporitories.stores.services.StoreData;
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
    public CartProductAdapter CartProductAdapter(){
        return new CartProductAdapter();
    }

    @Bean
    public SharedCartAdapter SharedCartAdapter(){
        return new SharedCartAdapter();
    }
}
