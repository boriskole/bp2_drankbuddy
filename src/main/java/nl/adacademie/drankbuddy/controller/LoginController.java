package nl.adacademie.drankbuddy.controller;

import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.dto.LoginRequestDto;
import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.interfaces.AccountDaoInterface;
import nl.adacademie.drankbuddy.view.dashboard.ProductOverviewView;
import nl.adacademie.drankbuddy.view.security.LoginPageView;
import nl.adacademie.drankbuddy.view.status.LoginPageStatus;

import java.util.Optional;
import java.util.stream.Stream;

public class LoginController {

    private final AccountDaoInterface accountDao;

    public LoginController(AccountDaoInterface accountDao) {
        this.accountDao = accountDao;
    }

    public void login(LoginRequestDto loginRequestDto) {

        // Kijken of er lege velden zijn ingevuld.
        boolean hasEmptyField = Stream.of(
            loginRequestDto.username(),
            loginRequestDto.password()
        ).anyMatch(string -> string == null || string.isBlank()); // Null of leeg.

        if (hasEmptyField) {
            DrankBuddy.changeView(new LoginPageView(LoginPageStatus.EMPTY_FIELDS)); // Pagina herladen met error.
            return;
        }

        // Account opzoeken in de database.
        Optional<Account> accountOptional = accountDao.findByUsername(loginRequestDto.username());

        if (accountOptional.isEmpty()) { // Kijken of de account bestaat.
            DrankBuddy.changeView(new LoginPageView(LoginPageStatus.INVALID_CREDENTIALS)); // Pagina herladen met error.
            return;
        }

        Account account = accountOptional.get();

        if (! account.getPassword().equals(loginRequestDto.password())) { // Kijken of het wachtwoord juist is.
            DrankBuddy.changeView(new LoginPageView(LoginPageStatus.INVALID_CREDENTIALS)); // Pagina herladen met error.
            return;
        }

        // Ingelogd account instellen.
        DrankBuddy.loggedInAccount = account;
        DrankBuddy.changeView(new ProductOverviewView());
    }

}
