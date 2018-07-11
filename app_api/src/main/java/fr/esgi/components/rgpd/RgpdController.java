package fr.esgi.components.rgpd;

import fr.esgi.components.rgpd.dto.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 6000)
@RequestMapping("/rgpd")
public class RgpdController {

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
                .loyaltyStores(loyaltyStores)
                .sharedCarts(sharedCarts)
                .ownedStores(ownedStores)
                .build();
    }
}
