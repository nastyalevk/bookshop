package nastya.BookShop.dto.classification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationDto {

    private Integer id;
    private Integer classificationId;
    private String classificationStatus;
    private String name;

}
