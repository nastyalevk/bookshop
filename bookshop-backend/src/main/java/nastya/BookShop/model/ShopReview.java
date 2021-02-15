package nastya.BookShop.model;

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
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "review_rate_shop")
@AllArgsConstructor
@NoArgsConstructor
public class ShopReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_review_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String comment;
    private Integer rating;
    @Column(name = "creation_date")
    private Date datetime;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private Boolean approved;

}
