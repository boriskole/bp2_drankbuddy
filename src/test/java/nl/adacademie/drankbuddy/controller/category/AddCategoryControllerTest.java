package nl.adacademie.drankbuddy.controller.category;

import nl.adacademie.drankbuddy.dto.AddCategoryRequestDto;
import nl.adacademie.drankbuddy.repository.testdao.TestCategoryDao;
import nl.adacademie.drankbuddy.view.type.AddCategoryPageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCategoryControllerTest {

    @AfterEach
    void tearDown() {
        TestCategoryDao.clearAll();
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_null_fields() {
        // Set-up:
        AddCategoryRequestDto addCategoryRequestDto = new AddCategoryRequestDto(null); // Naam is null.
        AddCategoryController addCategoryController = new AddCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        AddCategoryPageStatus addCategoryPageStatus = addCategoryController.addCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(AddCategoryPageStatus.EMPTY_FIELDS, addCategoryPageStatus);
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_empty_fields() {
        // Set-up:
        AddCategoryRequestDto addCategoryRequestDto = new AddCategoryRequestDto(""); // Naam is leeg.
        AddCategoryController addCategoryController = new AddCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        AddCategoryPageStatus addCategoryPageStatus = addCategoryController.addCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(AddCategoryPageStatus.EMPTY_FIELDS, addCategoryPageStatus);
    }
    
    @Test
    void it_returns_too_long_name_when_the_dto_has_a_name_longer_than_50_characters() {
        // Set-up:
        AddCategoryRequestDto addCategoryRequestDto = new AddCategoryRequestDto("This is a very long category name that exceeds fifty characters limit"); // Naam is te lang.
        AddCategoryController addCategoryController = new AddCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        AddCategoryPageStatus addCategoryPageStatus = addCategoryController.addCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(AddCategoryPageStatus.TOO_LONG_NAME, addCategoryPageStatus);
    }

    @Test
    void it_returns_success_when_everything_is_correct() {
        // Set-up:
        AddCategoryRequestDto addCategoryRequestDto = new AddCategoryRequestDto("Vodka");
        AddCategoryController addCategoryController = new AddCategoryController(new TestCategoryDao()); // Test versie gebruiken.

        // Execute:
        AddCategoryPageStatus addCategoryPageStatus = addCategoryController.addCategory(addCategoryRequestDto);

        // Assert:
        assertEquals(AddCategoryPageStatus.SUCCESS, addCategoryPageStatus);
    }

}