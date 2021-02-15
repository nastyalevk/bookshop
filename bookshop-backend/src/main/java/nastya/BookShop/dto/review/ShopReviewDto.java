package nastya.BookShop.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopReviewDto {

    private Integer id;
    private String username;
    private String comment;
    private Integer rating;
    private Integer shopId;
    private String datetime;
    private boolean approved;

}
