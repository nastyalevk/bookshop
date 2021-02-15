package nastya.BookShop.dto.assortment;

import lombok.Getter;

@Getter
public enum AssortmentClassification {

    OPEN("open"),
    ACTIVE("active"),
    WAITING("waiting"),
    CLOSED("closed");

    private String name;

    AssortmentClassification(String name) {
        this.name = name;
    }
}
