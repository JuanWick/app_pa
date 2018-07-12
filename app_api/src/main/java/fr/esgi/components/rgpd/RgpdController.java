package fr.esgi.components.rgpd;

import entities.Cart;
import entities.User;
import entities.UserAuthenticator;
import fr.esgi.components.cart.adapter.CartApiAdapter;
import fr.esgi.components.rgpd.adapter.RgpdAdapter;
import fr.esgi.components.rgpd.dto.*;
import fr.esgi.components.user.adapter.RoleApiAdapter;
import fr.esgi.components.user.adapter.UserApiAdapter;
import fr.esgi.exception.CartNotFoundException;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.exception.UserNotFoundExceptionApi;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserAuthenticatorData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.authentication.UserAuthenticationService;
import fr.esgi.services.carts.CartService;
import fr.esgi.services.stores.StoreService;
import fr.esgi.services.users.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 6000)
@RequestMapping("/rgpd")
public class RgpdController {

    private final
    UserData userData;

    private final
    StoreData storeData;

    private final
    CartData cartData;

    private final
    UserAuthenticatorData userAuthenticatorData;

    private final
    UserService userService;

    private final
    StoreService storeService;

    private final
    CartService cartService;

    private final
    UserApiAdapter userApiAdapter;

    private final
    RoleApiAdapter roleApiAdapter;

    private final
    CartApiAdapter cartApiAdapter;

    private final
    RgpdAdapter rgpdAdapter;

    private final
    UserAuthenticationService userAuthenticationService;

    public RgpdController(UserData userData, StoreData storeData, CartData cartData, UserAuthenticatorData userAuthenticatorData, UserService userService, StoreService storeService, CartService cartService, UserApiAdapter userApiAdapter, RoleApiAdapter roleApiAdapter, CartApiAdapter cartApiAdapter, RgpdAdapter rgpdAdapter, UserAuthenticationService userAuthenticationService) {
        this.userData = userData;
        this.storeData = storeData;
        this.cartData = cartData;
        this.userAuthenticatorData = userAuthenticatorData;
        this.userService = userService;
        this.storeService = storeService;
        this.cartService = cartService;
        this.userApiAdapter = userApiAdapter;
        this.roleApiAdapter = roleApiAdapter;
        this.cartApiAdapter = cartApiAdapter;
        this.rgpdAdapter = rgpdAdapter;
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/infos")
    public RgpdInfosDto getUsedInfos() {
        List<ProductCompletRgpdDto> productCompletRgpdDtos = new ArrayList<>();
        ProductCompletRgpdDto productCompletDto = ProductCompletRgpdDto.builder()
                .barreCode("")
                .info("")
                .name("")
                .price(0.0)
                .build();

        productCompletRgpdDtos.add(productCompletDto);

        SharedRgpdDto sharedRgpdDto = new SharedRgpdDto();
        sharedRgpdDto.setSharedUser(UserRgpdDto.builder().firstName("").name("").build());

        List<SharedRgpdDto> sharedRgpdDtos = new ArrayList<>();
        sharedRgpdDtos.add(sharedRgpdDto);

        CartRgpdDto cartRgpdDto = new CartRgpdDto();
        cartRgpdDto.setProducts(productCompletRgpdDtos);
        cartRgpdDto.setSharedusers(sharedRgpdDtos);

        List<CartRgpdDto> cartDtos = new ArrayList<>();
        cartDtos.add(cartRgpdDto);

        StoreRgpdDto storeDto = StoreRgpdDto.builder()
                .name("")
                .longitude(0.0)
                .latitude(0.0)
                .zipcode("")
                .country("")
                .city("")
                .address("")
                .build();

        List<StoreRgpdDto> ownedStores = new ArrayList<>();
        ownedStores.add(storeDto);

        List<StoreRgpdDto> loyaltyStores = new ArrayList<>();
        loyaltyStores.add(storeDto);

        List<CartRgpdDto> sharedCarts = new ArrayList<>();
        sharedCarts.add(cartRgpdDto);

        return RgpdInfosDto.builder()
                .email("")
                .firstName("")
                .login("")
                .name("")
                .password("")
                .role("")
                .longitude("")
                .latitude("")
                .rgpdAccepted(true)
                .rgpdAcceptedDate(new Date())
                .ownedCarts(cartDtos)
                .sharedCarts(sharedCarts)
                .ownedStores(ownedStores)
                .build();
    }

    @GetMapping("/userInfos/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public RgpdInfosDto getUsedInfos(@PathVariable(value="userId") int userId) {
       try {

        Object[] userInfos = userService.getById(userData,userAuthenticatorData,userId);

        User user = (User) userInfos[0];
        UserAuthenticator userAuthenticator = (UserAuthenticator) userInfos[1];

           List<Cart> carts = new ArrayList<>();
        try{
            carts = cartService.getByUserid(cartData,userId);
        } catch (CartNotFoundException c) {

        }

        List<CartRgpdDto> cartRgpdDtos = new ArrayList<>();

        for(Cart cart : carts){
            CartRgpdDto cartRgpdDto = new CartRgpdDto();
            cartRgpdDto.setProducts(rgpdAdapter.productsToDtos(cart.getProducts()));
            cartRgpdDto.setSharedusers(rgpdAdapter.sharedsUsersToDtod(cart.getSharedUsers()));
            cartRgpdDtos.add(cartRgpdDto);
        }

           List<Cart> ownedCarts = new ArrayList<>();
           try{
             ownedCarts = cartService.getByUserid(cartData,userId);
           } catch (CartNotFoundException c) {

           }

        List<CartRgpdDto> ownedRgpdCarts = rgpdAdapter.cartsToDto(ownedCarts);

        List<StoreRgpdDto> storeRgpdDtos = rgpdAdapter.storesToDtos(storeService.getByUserId(storeData, userId));

        return RgpdInfosDto.builder()
                .email(userAuthenticator.getEmail())
                .firstName(user.getFirstName())
                .login(userAuthenticator.getLogin())
                .name(user.getName())
                .password("[crypted]")
                .role(user.getRoles().get(0).getName())
                .longitude("")
                .latitude("")
                .rgpdAccepted(true)
                .rgpdAcceptedDate(new Date())
                .ownedCarts(ownedRgpdCarts)
                .sharedCarts(cartRgpdDtos)
                .ownedStores(storeRgpdDtos)
                .build();
       } catch (UserNotFoundException u){
           throw new UserNotFoundExceptionApi(u.getMessage());
       }

    }

    @GetMapping("/myInfos")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN','MANAGER')")
    public RgpdInfosDto getConnectedUserInfos() {
        try {
                UserAuthenticator userAuthenticator = userAuthenticationService.findByUserNameOrEmail(
                        userData,
                        userAuthenticatorData,
                        SecurityContextHolder.getContext().getAuthentication().getName());

        return getUsedInfos(userAuthenticator.getUser().getId());
        } catch (UserNotFoundException u){
            throw new UserNotFoundExceptionApi(u.getMessage());
        }
    }
}
