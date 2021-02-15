package nastya.BookShop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "assortment")
@AllArgsConstructor
@NoArgsConstructor
public class Assortment {

    @EmbeddedId
    private AssortmentId assortmentId;
    private Integer quantity;
    @Column(name = "price_per_item")
    private Integer Price;
    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private Classification classification;

}
