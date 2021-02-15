package nastya.BookShop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "classif")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
public class Classification {

    @Id
    @Column(name = "classif_id")
    private Integer id;

    @OneToMany(mappedBy = "classification")
    @JsonIgnore
    private Set<Classification> classifications;

    @OneToMany(mappedBy = "classification")
    @JsonIgnore
    private Set<Shop> shop;

    @OneToMany(mappedBy = "classification")
    @JsonIgnore
    private Set<Assortment> assortment;

    @OneToMany(mappedBy = "classification")
    @JsonIgnore
    private Set<Order> order;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parent_id", nullable = false)
    private Classification classification;

    @Column(name = "name")
    private String name;

}
