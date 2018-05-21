package fr.esgi.components.store.adapter;

import entities.Store;
import entities.User;
import fr.esgi.components.store.dto.StoreDto;
import fr.esgi.components.user.dto.UserDto;

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
                .id(null != storeDto.getId()? storeDto.getId():null).build();

        if(null != storeDto.getUser()){
            User user = new User();
            user.setId(storeDto.getUser().getId());
            store.setUser(user);
        }
        return store;
    }

    public StoreDto convertToDto(Store store){
        StoreDto storeDto = new StoreDto();
        storeDto.setAddress(store.getAddress());
        storeDto.setCity(store.getCity());
        storeDto.setCountry(store.getCountry());
        storeDto.setName(store.getName());
        storeDto.setZipcode(store.getZipcode());
        storeDto.setLatitude(store.getLatitude());
        storeDto.setLongitude(store.getLongitude());
        storeDto.setId(null != store.getId()? store.getId():null);

        if(null != store.getUser()){
            UserDto userDto = new UserDto();
            userDto.setId(store.getUser().getId());
            storeDto.setUser(userDto);
        }
        return storeDto;
    }
}
