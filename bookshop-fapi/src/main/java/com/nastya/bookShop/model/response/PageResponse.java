package com.nastya.bookShop.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    @JsonProperty("content")
    private List<T> content;
    private int currentPage;
    @JsonProperty("totalElements")
    private long totalElements;
    private int totalPages;

}
