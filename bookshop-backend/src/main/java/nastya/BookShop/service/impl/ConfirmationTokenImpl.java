package nastya.BookShop.service.impl;

import nastya.BookShop.dto.user.ConfirmationTokenDto;
import nastya.BookShop.model.ConfirmationToken;
import nastya.BookShop.repository.ConfirmationTokenRepository;
import nastya.BookShop.repository.UserRepository;
import nastya.BookShop.service.api.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConfirmationTokenImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;


    @Autowired
    public ConfirmationTokenImpl(ConfirmationTokenRepository confirmationTokenRepository,
                                 UserRepository userRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ConfirmationTokenDto findByToken(String token) {
        return transfer(confirmationTokenRepository.findByConfirmationToken(token));
    }

    @Override
    public ConfirmationTokenDto save(ConfirmationTokenDto confirmationTokenDto) {
        return transfer(confirmationTokenRepository.save(transfer(confirmationTokenDto)));
    }

    private ConfirmationTokenDto transfer(ConfirmationToken confirmationToken) {
        ConfirmationTokenDto confirmationTokenDto = new ConfirmationTokenDto();
        confirmationTokenDto.setConfirmationToken(confirmationToken.getConfirmationToken());
        confirmationTokenDto.setId(confirmationToken.getId());
        confirmationTokenDto.setCreatedDate(confirmationToken.getCreatedDate());
        confirmationTokenDto.setUsername(confirmationToken.getUser().getUsername());
        return confirmationTokenDto;
    }

    private ConfirmationToken transfer(ConfirmationTokenDto confirmationTokenDto) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmationToken(confirmationTokenDto.getConfirmationToken());
        confirmationToken.setId(confirmationTokenDto.getId());
        confirmationToken.setCreatedDate(new Date());
        confirmationToken.setUser(userRepository.findByUsername(confirmationTokenDto.getUsername()));
        return confirmationToken;
    }
}
