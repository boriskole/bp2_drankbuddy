package nl.adacademie.drankbuddy.controller;

import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.dto.RegisterRequestDto;
import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.interfaces.AccountDaoInterface;
import nl.adacademie.drankbuddy.view.security.LoginPageView;
import nl.adacademie.drankbuddy.view.security.RegisterPageView;
import nl.adacademie.drankbuddy.view.status.LoginPageStatus;
import nl.adacademie.drankbuddy.view.status.RegisterPageStatus;

import java.util.stream.Stream;

public class RegisterAccountController {

    private final AccountDaoInterface accountDao;

    public RegisterAccountController(AccountDaoInterface accountDao) {
        this.accountDao = accountDao;
    }

    public void registerAccount(RegisterRequestDto registerRequestDto) {

        // Valideren voor lege invulvelden.
        boolean hasEmptyField = Stream.of( // Alle dto info in een stream stoppen.
            registerRequestDto.name(),
            registerRequestDto.username(),
            registerRequestDto.password(),
            registerRequestDto.confirmPassword()
        ).anyMatch(string -> string == null || string.isBlank()); // Kijken of de string niet null of leeg is.

        if (hasEmptyField) { // Als een field leeg is, de view herladen met een error.
            DrankBuddy.changeView(new RegisterPageView(RegisterPageStatus.EMPTY_FIELDS));
            return;
        }

        // Valideren voor bevestig wachtwoord.
        if (! registerRequestDto.password().equals(registerRequestDto.confirmPassword())) {
            DrankBuddy.changeView(new RegisterPageView(RegisterPageStatus.PASSWORD_MISMATCH)); // Pagina herladen met error.
            return;
        }

        // Valideren of de gebruikersnaam al bestaat.
        boolean usernameIsTaken = accountDao.findByUsername( // Kijken of er al een account bestaat met die gebruikersnaam.
            registerRequestDto.username()
        ).isPresent();

        if (usernameIsTaken) { // Als de gebruikersnaam al bezet is, de view herladen met een error.
            DrankBuddy.changeView(new RegisterPageView(RegisterPageStatus.USERNAME_EXISTS));
            return;
        }

        // De DTO omzetten naar een account.
        Account account = new Account(
            registerRequestDto.username(),
            registerRequestDto.password(),
            registerRequestDto.name()
        );

        // De account opslaan in de database.
        accountDao.save(account);

        // De view herladen met een success message.
        DrankBuddy.changeView(new LoginPageView(LoginPageStatus.REGISTER_SUCCESS));
    }

}
