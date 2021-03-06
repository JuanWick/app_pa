package fr.esgi;

        import fr.esgi.components.category.adapter.CategoryApiAdapter;
        import fr.esgi.components.rgpd.adapter.RgpdAdapter;
        import fr.esgi.components.security.CustomUserDetailsService;
        import fr.esgi.components.security.adapter.UserPrincipalAdapter;
        import fr.esgi.components.security.strategy.AuthenticatorStrategy;
        import fr.esgi.components.security.strategy.SpringAuthenticatorStrategy;
        import fr.esgi.reporitories.products.adapter.CategoryDataAdapter;
        import fr.esgi.reporitories.users.services.HibernateUserAuthenticatorData;
        import fr.esgi.reporitories.users.services.UserAuthenticatorData;
        import fr.esgi.services.authentication.UserAuthenticationService;
        import fr.esgi.services.authentication.UserAuthenticationServiceImpl;
        import fr.esgi.components.cart.adapter.CartApiAdapter;
        import fr.esgi.components.product.adapter.ProductApiAdapter;
        import fr.esgi.components.store.adapter.StoreApiAdapter;
        import fr.esgi.components.user.adapter.RoleApiAdapter;
        import fr.esgi.components.user.adapter.UserApiAdapter;
        import fr.esgi.reporitories.carts.services.CartData;
        import fr.esgi.reporitories.carts.services.HibernateCartData;
        import fr.esgi.reporitories.products.services.HibernateProductData;
        import fr.esgi.reporitories.products.services.ProductData;
        import fr.esgi.reporitories.stores.services.HibernateStoreData;
        import fr.esgi.reporitories.stores.services.StoreData;
        import fr.esgi.reporitories.users.services.HibernateUserData;
        import fr.esgi.reporitories.users.services.UserData;
        import fr.esgi.services.carts.CartService;
        import fr.esgi.services.carts.CartServiceImpl;
        import fr.esgi.services.product.ProductService;
        import fr.esgi.services.product.ProductServiceImpl;
        import fr.esgi.services.stores.StoreService;
        import fr.esgi.services.stores.StoreServiceImpl;
        import fr.esgi.services.users.UserService;
        import fr.esgi.services.users.UserServiceImpl;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import springfox.documentation.builders.ApiInfoBuilder;
        import springfox.documentation.schema.AlternateTypeRule;
        import springfox.documentation.service.ApiInfo;
        import springfox.documentation.spring.web.plugins.Docket;
        import springfox.documentation.swagger2.annotations.EnableSwagger2;

        import java.awt.*;

        import static springfox.documentation.builders.PathSelectors.any;
        import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
        import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class ApiConfiguration
{
    public static void main( String[] args )
    {
        SpringApplication.run(ApiConfiguration.class, args);
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

    @Bean
    public ProductService ProductService(){ return new ProductServiceImpl(); }

    @Bean
    public CustomUserDetailsService CustomUserDetailsService(){return new CustomUserDetailsService(); }

    @Bean
    public UserAuthenticationService UserAuthenticationService(){return new UserAuthenticationServiceImpl(); }

    @Bean
    public AuthenticatorStrategy AuthenticatorStrategy(){return new SpringAuthenticatorStrategy();}

    /** Data **/
    @Bean
    public StoreData StoreData() {return new HibernateStoreData();}

    @Bean
    public UserData UserData() {return new HibernateUserData();}

    @Bean
    public CartData CartData() {return new HibernateCartData();}

    @Bean
    public ProductData ProductData() {return new HibernateProductData();}

    @Bean
    public UserAuthenticatorData UserAuthenticationData() {return new HibernateUserAuthenticatorData();}

    /** Dto **/
    @Bean
    public CartApiAdapter CartApiAdapter(){return new CartApiAdapter();}

    @Bean
    public UserApiAdapter UserApiAdapter(){return new UserApiAdapter();}

    @Bean
    public RoleApiAdapter RoleApiAdapter(){return new RoleApiAdapter();}

    @Bean
    public StoreApiAdapter StoreApiAdapter(){return new StoreApiAdapter();}

    @Bean
    public ProductApiAdapter ProductApiAdapter(){return new ProductApiAdapter();}

    @Bean
    public UserPrincipalAdapter UserPrincipalAdapter(){return new UserPrincipalAdapter();}

    @Bean
    public CategoryApiAdapter CategoryApiAdapter(){return new CategoryApiAdapter();}

    @Bean
    public RgpdAdapter RgpdAdapter(){return new RgpdAdapter();}

    /** SWAGGER **/
    @Bean
    public Docket swaggerDocumentation(){
       return new Docket(SWAGGER_2)
               .apiInfo(apiInfo())
               .select()
               .apis(basePackage("fr.esgi.components"))
               .paths(any())
               .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("app_api")
                .version("1.0")
                .build();

    }

}
