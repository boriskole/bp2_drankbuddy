package nl.adacademie.drankbuddy.controller;

import nl.adacademie.drankbuddy.dto.RegisterRequestDto;
import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.testdao.TestAccountDao;
import nl.adacademie.drankbuddy.view.type.RegisterPageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterAccountControllerTest {

    @AfterEach
    void tearDown() {
        TestAccountDao.clearAll();
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_empty_or_null_fields() {
        // Set-up:
        RegisterAccountController registerAccountController = new RegisterAccountController(new TestAccountDao()); // Test versie gebruiken.
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("", "", null, null); // Een dto met lege/null velden.

        // Execution:
        RegisterPageStatus registerPageStatus = registerAccountController.registerAccount(registerRequestDto);

        // Assert:
        assertEquals(RegisterPageStatus.EMPTY_FIELDS, registerPageStatus);
    }

    @Test
    void it_returns_password_mismatch_when_the_passwords_dont_match() {
        // Set-up:
        RegisterAccountController registerAccountController = new RegisterAccountController(new TestAccountDao()); // Test versie gebruiken.
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("John Doe", "john-doe", "password", "other-password"); // Een dto met niet overeenkomende wachtwoorden.

        // Execution:
        RegisterPageStatus registerPageStatus = registerAccountController.registerAccount(registerRequestDto);

        // Assert:
        assertEquals(RegisterPageStatus.PASSWORD_MISMATCH, registerPageStatus);
    }

    @Test
    void it_returns_username_exists_when_attempting_to_register_with_an_existing_username() {
        // Set-up:
        Account account = new Account("john-doe", "password", "John Doe");
        TestAccountDao.addAll(List.of(account)); // De account toevoegen aan de 'database'.
        RegisterAccountController registerAccountController = new RegisterAccountController(new TestAccountDao()); // Test versie gebruiken.
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("John Doe", "john-doe", "password", "password");

        // Execution:
        RegisterPageStatus registerPageStatus = registerAccountController.registerAccount(registerRequestDto);

        // Assert:
        assertEquals(RegisterPageStatus.USERNAME_EXISTS, registerPageStatus);
    }

    @Test
    void it_returns_register_success_when_everything_is_correct() {
        // Set-up:
        TestAccountDao testAccountDao = new TestAccountDao();
        RegisterAccountController registerAccountController = new RegisterAccountController(testAccountDao); // Test versie gebruiken.
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("John Doe", "john-doe", "password", "password");

        // Execution:
        RegisterPageStatus registerPageStatus = registerAccountController.registerAccount(registerRequestDto);

        // Assert:
        assertEquals(RegisterPageStatus.REGISTER_SUCCESS, registerPageStatus);
        assertTrue(testAccountDao.findByUsername("john-doe").isPresent());
    }

}