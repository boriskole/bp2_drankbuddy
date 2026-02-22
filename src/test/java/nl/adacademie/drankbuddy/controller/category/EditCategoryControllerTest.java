package nl.adacademie.drankbuddy.controller.category;

import nl.adacademie.drankbuddy.dto.EditCategoryRequestDto;
import nl.adacademie.drankbuddy.repository.testdao.TestCategoryDao;
import nl.adacademie.drankbuddy.view.type.EditCategoryPageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditCategoryControllerTest {

    @AfterEach
    void tearDown() {
        TestCategoryDao.clearAll();
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_null_fields() {
        // Set-up:
        EditCategoryRequestDto addCategoryRequestDto = new EditCategoryRequestDto(0, null); // Naam is null.
        EditCategoryController editCategoryController = new EditCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        EditCategoryPageStatus addCategoryPageStatus = editCategoryController.editCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(EditCategoryPageStatus.EMPTY_FIELDS, addCategoryPageStatus);
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_empty_fields() {
        // Set-up:
        EditCategoryRequestDto addCategoryRequestDto = new EditCategoryRequestDto(0, ""); // Naam is leeg.
        EditCategoryController editCategoryController = new EditCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        EditCategoryPageStatus addCategoryPageStatus = editCategoryController.editCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(EditCategoryPageStatus.EMPTY_FIELDS, addCategoryPageStatus);
    }

    @Test
    void it_returns_too_long_name_when_the_dto_has_a_name_longer_than_50_characters() {
        // Set-up:
        EditCategoryRequestDto addCategoryRequestDto = new EditCategoryRequestDto(0, "This is a very long category name that exceeds fifty characters limit"); // Naam is te lang.
        EditCategoryController editCategoryController = new EditCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        EditCategoryPageStatus addCategoryPageStatus = editCategoryController.editCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(EditCategoryPageStatus.TOO_LONG_NAME, addCategoryPageStatus);
    }

    @Test
    void it_returns_success_when_everything_is_correct() {
        // Set-up:
        EditCategoryRequestDto addCategoryRequestDto = new EditCategoryRequestDto(0, "Vodka");
        EditCategoryController editCategoryController = new EditCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        EditCategoryPageStatus addCategoryPageStatus = editCategoryController.editCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(EditCategoryPageStatus.SUCCESS, addCategoryPageStatus);
    }

}