package nastya.BookShop.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer id;
    private String bookName;
    private String author;
    private String genre;
    private Integer publicationYear;
    private Integer pages;
    private String description;

}
