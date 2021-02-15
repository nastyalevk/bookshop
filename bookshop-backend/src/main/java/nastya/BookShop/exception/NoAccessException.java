package nastya.BookShop.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoAccessException extends RuntimeException {

    private String message;

    public NoAccessException(String message) {
        super(message);
        this.message = message;
    }
}
