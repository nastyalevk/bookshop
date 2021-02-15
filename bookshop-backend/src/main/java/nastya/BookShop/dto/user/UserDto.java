package nastya.BookShop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nastya.BookShop.dto.role.RoleDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<RoleDto> roles;
    private Boolean activated;
    private Boolean isEnabled;
    private String message;

}
