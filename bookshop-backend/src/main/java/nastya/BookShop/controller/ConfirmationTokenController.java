package nastya.BookShop.controller;

import nastya.BookShop.dto.user.ConfirmationTokenDto;
import nastya.BookShop.service.api.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class ConfirmationTokenController {

    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public ConfirmationTokenController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ConfirmationTokenDto> createToken(@RequestBody ConfirmationTokenDto confirmationTokenDto) {
        return new ResponseEntity<>(confirmationTokenService.save(confirmationTokenDto), HttpStatus.OK);
    }

    @GetMapping("/{token}")
    public ResponseEntity<ConfirmationTokenDto> findByToken(@PathVariable("token") String token) {
        return new ResponseEntity<>(confirmationTokenService.findByToken(token), HttpStatus.OK);
    }
}
