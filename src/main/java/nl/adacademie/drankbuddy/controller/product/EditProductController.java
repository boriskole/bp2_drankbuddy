package nl.adacademie.drankbuddy.controller.product;

import nl.adacademie.drankbuddy.dto.EditProductRequestDto;
import nl.adacademie.drankbuddy.repository.interfaces.ProductDaoInterface;
import nl.adacademie.drankbuddy.view.type.EditProductPageStatus;

public class EditProductController {

    private final ProductDaoInterface productDao;

    public EditProductController(ProductDaoInterface productDao) {
        this.productDao = productDao;
    }

    public EditProductPageStatus editProduct(EditProductRequestDto editProductRequestDto) {

        // Als de categorie null is.
        if (editProductRequestDto.category() == null) {
            return EditProductPageStatus.EMPTY_FIELDS;
        }

        // Als de naam null of leeg is.
        if (editProductRequestDto.name() == null || editProductRequestDto.name().isBlank()) {
            return EditProductPageStatus.EMPTY_FIELDS;
        }

        // Als de naam langer is dan 50 karakters.
        if (editProductRequestDto.name().length() > 50) {
            return EditProductPageStatus.TOO_LONG_NAME;
        }

        // Product bewerken in de database.
        productDao.update(
            editProductRequestDto.id(),
            editProductRequestDto.name(),
            editProductRequestDto.category()
        );

        return EditProductPageStatus.SUCCESS;

    }

}
