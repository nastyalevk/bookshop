package nastya.BookShop.service.api;

import nastya.BookShop.dto.user.ConfirmationTokenDto;

public interface ConfirmationTokenService {

    ConfirmationTokenDto findByToken(String token);

    ConfirmationTokenDto save(ConfirmationTokenDto confirmationTokenDto);
}
