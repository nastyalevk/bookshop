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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shops")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_id")
    private Integer id;

    @OneToMany(mappedBy = "shop")
    @JsonIgnore
    private Set<ShopReview> ShopReview;

    @OneToMany(mappedBy = "assortmentId.shop")
    @JsonIgnore
    private Set<Assortment> assortment;

    @OneToMany(mappedBy = "shop")
    @JsonIgnore
    private Set<Order> order;

    @Column(name = "shop_name")
    private String shopName;
    private String country;
    private String city;
    private String address;
    private String description;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private Classification classification;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;

}
