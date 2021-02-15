package nastya.BookShop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @OneToMany(mappedBy = "userRolesId.user")
    @JsonIgnore
    private Set<UserRoles> userRoles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BookReview> review;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private ConfirmationToken confirmationToken;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Shop> shop;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> order;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Logs> userLog;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private Set<Logs> adminLog;

    private String username;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private Boolean activated;
    private Boolean isEnabled;

}
