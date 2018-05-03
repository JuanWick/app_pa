package fr.esgi;

        import fr.esgi.reporitories.stores.services.HibernateStoreData;
        import fr.esgi.reporitories.stores.services.StoreData;
        import fr.esgi.reporitories.users.services.HibernateUserData;
        import fr.esgi.reporitories.users.services.InMemotyUserData;
        import fr.esgi.reporitories.users.services.UserData;
        import fr.esgi.services.stores.StoreService;
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

    /** Data **/
    @Bean
    public StoreData StoreData() {return new HibernateStoreData();}

    @Bean
    public UserData UserData() {return new HibernateUserData();}
}
