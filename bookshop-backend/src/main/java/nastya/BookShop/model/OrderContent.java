package nastya.BookShop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "order_content")
@AllArgsConstructor
@NoArgsConstructor
public class OrderContent implements Serializable {

    @EmbeddedId
    private OrderContentId orderContentId;
    private Integer quantity;
    @Column(name = "price_per_item")
    private Integer price;

}
