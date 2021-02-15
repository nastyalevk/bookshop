package nastya.BookShop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationTokenDto {

    private Integer id;
    private String confirmationToken;
    private Date createdDate;
    private String username;

}
