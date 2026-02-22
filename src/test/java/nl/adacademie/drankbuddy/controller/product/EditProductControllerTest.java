package nl.adacademie.drankbuddy.controller.product;

import nl.adacademie.drankbuddy.dto.EditProductRequestDto;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.testdao.TestProductDao;
import nl.adacademie.drankbuddy.view.type.EditProductPageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditProductControllerTest {

    @AfterEach
    void tearDown() {
        TestProductDao.clearAll();
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_null_fields() {
        // Set-up:
        EditProductRequestDto editProductRequestDto = new EditProductRequestDto(0, null, null); // Alles null/invalid.
        EditProductController editProductController = new EditProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        EditProductPageStatus editProductPageStatus = editProductController.editProduct(editProductRequestDto);

        // Assert:
        assertEquals(EditProductPageStatus.EMPTY_FIELDS, editProductPageStatus);
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_empty_fields() {
        // Set-up:
        EditProductRequestDto editProductRequestDto = new EditProductRequestDto(1, new Category(), ""); // Naam is leeg.
        EditProductController editProductController = new EditProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        EditProductPageStatus editProductPageStatus = editProductController.editProduct(editProductRequestDto);

        // Assert:
        assertEquals(EditProductPageStatus.EMPTY_FIELDS, editProductPageStatus);
    }

    @Test
    void it_returns_too_long_name_when_the_dto_has_a_name_longer_than_50_characters() {
        // Set-up:
        EditProductRequestDto editProductRequestDto = new EditProductRequestDto(1, new Category(), "This is a very long product name that exceeds fifty characters limit"); // Naam is te lang.
        EditProductController editProductController = new EditProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        EditProductPageStatus editProductPageStatus = editProductController.editProduct(editProductRequestDto);

        // Assert:
        assertEquals(EditProductPageStatus.TOO_LONG_NAME, editProductPageStatus);
    }

    @Test
    void it_returns_success_when_everything_is_correct() {
        // Set-up:
        EditProductRequestDto editProductRequestDto = new EditProductRequestDto(1, new Category(), "Vodka");
        EditProductController editProductController = new EditProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        EditProductPageStatus editProductPageStatus = editProductController.editProduct(editProductRequestDto);

        // Assert:
        assertEquals(EditProductPageStatus.SUCCESS, editProductPageStatus);
    }

}