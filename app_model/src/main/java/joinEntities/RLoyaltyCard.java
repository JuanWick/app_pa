package joinEntities;

import lombok.Data;
import entities.Store;
import entities.User;

@Data
public class RLoyaltyCard {
    private Store store;
    private User user;
}
