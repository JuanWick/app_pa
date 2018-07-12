package fr.esgi.components.store.adapter;

import entities.Store;
import entities.User;
import fr.esgi.components.store.dto.StoreDto;
import fr.esgi.components.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class StoreApiAdapter {
    public Store convertToModel (StoreDto storeDto){
        Store store = Store.builder()
                .address(storeDto.getAddress())
                .city(storeDto.getCity())
                .country(storeDto.getCountry())
                .name(storeDto.getName())
                .zipcode(storeDto.getZipcode())
                .longitude(storeDto.getLongitude())
                .latitude(storeDto.getLatitude())
                .id(storeDto.getId()).build();

        if(null != storeDto.getUser()){
            User user = new User();
            user.setId(storeDto.getUser().getId());
            store.setUser(user);
        }
        return store;
    }

    public StoreDto convertToDto(Store store){
       return StoreDto.builder()
                .address(store.getAddress())
                .city(store.getCity())
                .country(store.getCountry())
                .id(store.getId())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .name(store.getName())
                .user(null != store.getUser()? UserDto.builder().id(store.getUser().getId()).build():null)
                .zipcode(store.getZipcode())
                .build();
    }

    public List<StoreDto> convertModelsToDto(List<Store> byUserId) {
        List<StoreDto> stores = new ArrayList<>();

        for(Store store:byUserId){
            stores.add(convertToDto(store));
        }
        return stores;
    }
}
