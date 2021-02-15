package nastya.BookShop.dto.classification;

import lombok.Getter;

@Getter
public enum ClassificationParent {

    SHOP("shop"),
    ASSORTMENT("assortment"),
    ORDER("order");

    private String name;

    ClassificationParent(String name) {
        this.name = name;
    }
}
