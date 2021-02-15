package com.nastya.bookShop.model.Order;

import lombok.Getter;

@Getter
public enum OrderClassification {

    OPEN("open"),
    SUBMITTED("submitted"),
    PROCESSING("processing"),
    PROCESSED("processed"),
    CANCELED("canceled");

    private String name;

    OrderClassification(String name) {
        this.name = name;
    }
}
