package nastya.BookShop.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotEnoughItemsException extends RuntimeException {

    private String message;

    public NotEnoughItemsException(String message) {
        super(message);
        this.message = message;
    }

}
