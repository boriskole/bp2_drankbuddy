package nl.adacademie.drankbuddy.controller.product;

import nl.adacademie.drankbuddy.dto.AddProductRequestDto;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.testdao.TestProductDao;
import nl.adacademie.drankbuddy.view.type.AddProductPageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddProductControllerTest {

    @AfterEach
    void tearDown() {
        TestProductDao.clearAll();
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_null_fields() {
        // Set-up:
        AddProductRequestDto addProductRequestDto = new AddProductRequestDto(null, null); // Allebei null.
        AddProductController addProductController = new AddProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        AddProductPageStatus addProductPageStatus = addProductController.addProduct(addProductRequestDto);

        // Assert:
        assertEquals(AddProductPageStatus.EMPTY_FIELDS, addProductPageStatus);
    }

    @Test
    void it_returns_empty_fields_when_the_dto_has_empty_fields() {
        // Set-up:
        AddProductRequestDto addProductRequestDto = new AddProductRequestDto(new Category(), ""); // Naam is leeg.
        AddProductController addProductController = new AddProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        AddProductPageStatus addProductPageStatus = addProductController.addProduct(addProductRequestDto);

        // Assert:
        assertEquals(AddProductPageStatus.EMPTY_FIELDS, addProductPageStatus);
    }

    @Test
    void it_returns_too_long_name_when_the_dto_has_a_name_longer_than_50_characters() {
        // Set-up:
        AddProductRequestDto addProductRequestDto = new AddProductRequestDto(new Category(), "This is a very long product name that exceeds fifty characters limit"); // Naam is te lang.
        AddProductController addProductController = new AddProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        AddProductPageStatus addProductPageStatus = addProductController.addProduct(addProductRequestDto);

        // Assert:
        assertEquals(AddProductPageStatus.TOO_LONG_NAME, addProductPageStatus);
    }

    @Test
    void it_returns_success_when_everything_is_correct() {
        // Set-up:
        AddProductRequestDto addProductRequestDto = new AddProductRequestDto(new Category(), "Vodka");
        AddProductController addProductController = new AddProductController(new TestProductDao()); // Test versie gebruiken.

        // Execute:
        AddProductPageStatus addProductPageStatus = addProductController.addProduct(addProductRequestDto);

        // Assert:
        assertEquals(AddProductPageStatus.SUCCESS, addProductPageStatus);
    }

}