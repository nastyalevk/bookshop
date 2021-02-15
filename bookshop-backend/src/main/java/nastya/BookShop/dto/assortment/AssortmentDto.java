package nastya.BookShop.dto.assortment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssortmentDto {

    private Integer bookId;
    private Integer shopId;
    private Integer quantity;
    private Integer Price;
    private String creationDate;
    private AssortmentClassification classification;

}
