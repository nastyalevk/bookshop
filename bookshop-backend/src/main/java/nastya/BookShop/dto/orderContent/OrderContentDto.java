package nastya.BookShop.dto.orderContent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderContentDto {

    private Integer orderId;
    private Integer bookId;
    private Integer quantity;
    private Integer price;

}
