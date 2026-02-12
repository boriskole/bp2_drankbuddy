package nl.adacademie.drankbuddy.controller;

import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.dto.LoginRequestDto;
import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.interfaces.AccountDaoInterface;
import nl.adacademie.drankbuddy.view.type.LoginPageStatus;

import java.util.Optional;
import java.util.stream.Stream;

public class LoginController {

    private final AccountDaoInterface accountDao;

    public LoginController(AccountDaoInterface accountDao) {
        this.accountDao = accountDao;
    }

    public LoginPageStatus login(LoginRequestDto loginRequestDto) {

        // Kijken of er lege velden zijn ingevuld.
        boolean hasEmptyField = Stream.of(
            loginRequestDto.username(),
            loginRequestDto.password()
        ).anyMatch(string -> string == null || string.isBlank()); // Null of leeg.

        if (hasEmptyField) {
            return LoginPageStatus.EMPTY_FIELDS;
        }

        // Account opzoeken in de database.
        Optional<Account> accountOptional = accountDao.findByUsername(loginRequestDto.username());

        if (accountOptional.isEmpty()) { // Kijken of de account bestaat.
            return LoginPageStatus.INVALID_CREDENTIALS;
        }

        Account account = accountOptional.get();

        if (! account.getPassword().equals(loginRequestDto.password())) { // Kijken of het wachtwoord juist is.
            return LoginPageStatus.INVALID_CREDENTIALS;
        }

        // Ingelogd account instellen.
        DrankBuddy.loggedInAccount = account;
        return LoginPageStatus.LOGIN_SUCCESS;
    }

}
