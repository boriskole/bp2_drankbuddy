package nl.adacademie.drankbuddy.controller;

import nl.adacademie.drankbuddy.dto.LoginRequestDto;
import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.testdao.TestAccountDao;
import nl.adacademie.drankbuddy.view.status.LoginPageStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {

    @Test
    void it_returns_empty_fields_when_the_dto_has_empty_or_null_fields() {
        // Set-up:
        LoginController loginController = new LoginController(new TestAccountDao()); // Test versie gebruiken.
        LoginRequestDto loginRequestDto = new LoginRequestDto("", null); // Eerste is leeg, tweede is null.

        // Execute:
        LoginPageStatus loginPageStatus = loginController.login(loginRequestDto);

        // Assert:
        assertEquals(LoginPageStatus.EMPTY_FIELDS, loginPageStatus);
    }

    @Test
    void it_returns_invalid_credentials_when_a_nonexisting_username_is_entered() {
        // Set-up:
        TestAccountDao.clearAll(); // Er zitten geen entities in de 'database'.
        LoginController loginController = new LoginController(new TestAccountDao()); // Test versie gebruiken.
        LoginRequestDto loginRequestDto = new LoginRequestDto("this-user-does-not-exist", "password"); // Een niet bestaande gebruikersnaam.

        // Execute:
        LoginPageStatus loginPageStatus = loginController.login(loginRequestDto);

        // Assert:
        assertEquals(LoginPageStatus.INVALID_CREDENTIALS, loginPageStatus);
    }

    @Test
    void it_returns_invalid_credentials_when_a_wrong_password_is_entered() {
        // Set-up:
        Account account = new Account("john-doe", "secret-password", "John");
        TestAccountDao.addAll(List.of(account)); // De account in de 'database' stoppen.
        LoginController loginController = new LoginController(new TestAccountDao()); // Test versie gebruiken.
        LoginRequestDto loginRequestDto = new LoginRequestDto("john-doe", "this-is-a-wrong-password"); // Een fout wachtwoord, wel een bestaande gebruikersnaam.

        // Execute:
        LoginPageStatus loginPageStatus = loginController.login(loginRequestDto);

        // Assert:
        assertEquals(LoginPageStatus.INVALID_CREDENTIALS, loginPageStatus);
    }

    @Test
    void it_returns_login_success_when_everything_is_correct() {
        // Set-up:
        Account account = new Account("john-doe", "secret-password", "John");
        TestAccountDao.addAll(List.of(account)); // De account in de 'database' stoppen.
        LoginController loginController = new LoginController(new TestAccountDao()); // Test versie gebruiken.
        LoginRequestDto loginRequestDto = new LoginRequestDto("john-doe", "secret-password"); // Goeie inloggegevens.

        // Execute:
        LoginPageStatus loginPageStatus = loginController.login(loginRequestDto);

        // Assert:
        assertEquals(LoginPageStatus.LOGIN_SUCCESS, loginPageStatus);
    }

}