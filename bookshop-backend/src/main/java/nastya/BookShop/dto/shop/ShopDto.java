package nastya.BookShop.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {

    private Integer id;
    private String shopName;
    private String country;
    private String city;
    private String address;
    private String description;
    private ShopClassification classification;
//    private String shopId;
    private String username;

}
