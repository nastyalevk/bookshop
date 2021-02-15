package nastya.BookShop.dto.logs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogsDto {

    private Integer id;
    private Integer adminId;
    private Integer userId;
    private String reason;
    private String datetime;
    private String operation;

}
