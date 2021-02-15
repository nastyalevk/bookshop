package nastya.BookShop.dto.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Integer id;
    private ERole name;

    public RoleDto(Integer id, String roleName) {
        this.id = id;
        this.name = ERole.valueOf(roleName);
    }
}
