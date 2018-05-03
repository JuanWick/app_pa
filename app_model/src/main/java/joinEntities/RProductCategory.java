package joinEntities;

import lombok.Data;
import entities.Category;
import entities.Product;

@Data
public class RProductCategory {
    private Product product;
    private Category category;
}
