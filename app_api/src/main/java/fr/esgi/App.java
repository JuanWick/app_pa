package fr.esgi;

        import fr.esgi.components.cart.adapter.CartDtoAdapter;
        import fr.esgi.components.user.adapter.UserDtoAdapter;
        import fr.esgi.reporitories.carts.services.CartData;
        import fr.esgi.reporitories.carts.services.HibernateCartData;
        import fr.esgi.reporitories.stores.services.HibernateStoreData;
        import fr.esgi.reporitories.stores.services.StoreData;
        import fr.esgi.reporitories.users.services.HibernateUserData;
        import fr.esgi.reporitories.users.services.InMemotyUserData;
        import fr.esgi.reporitories.users.services.UserData;
        import fr.esgi.services.carts.CartService;
        import fr.esgi.services.carts.CartServiceImpl;
        import fr.esgi.services.stores.StoreService;
        import fr.esgi.services.stores.StoreServiceImpl;
        import fr.esgi.services.stores.StoreServiceImpl;
        import fr.esgi.services.users.UserService;
        import fr.esgi.services.users.UserServiceImpl;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    /** Services **/
    @Bean
    public StoreService StoreService(){
        return new StoreServiceImpl();
    }

    @Bean
    public UserService UserService(){ return new UserServiceImpl(); }

    @Bean
    public CartService CartService(){ return new CartServiceImpl(); }

    /** Data **/
    @Bean
    public StoreData StoreData() {return new HibernateStoreData();}

    @Bean
    public UserData UserData() {return new HibernateUserData();}

    @Bean
    public CartData CartData() {return new HibernateCartData();}

    /** Dto **/
    @Bean
    public CartDtoAdapter CartDtoAdapter(){return new CartDtoAdapter();}

    @Bean
    public UserDtoAdapter UserDtoAdapter(){return new UserDtoAdapter();}
}
