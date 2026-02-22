package nl.adacademie.drankbuddy.controller.product;

import nl.adacademie.drankbuddy.dto.AddProductRequestDto;
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.repository.interfaces.ProductDaoInterface;
import nl.adacademie.drankbuddy.view.type.AddProductPageStatus;

public class AddProductController {

    private final ProductDaoInterface productDao;

    public AddProductController(ProductDaoInterface productDao) {
        this.productDao = productDao;
    }

    public AddProductPageStatus addProduct(AddProductRequestDto addProductRequestDto) {

        // Als de categorie null is.
        if (addProductRequestDto.category() == null) {
            return AddProductPageStatus.EMPTY_FIELDS;
        }

        // Als de naam null of leeg is.
        if (addProductRequestDto.name() == null || addProductRequestDto.name().isBlank()) {
            return AddProductPageStatus.EMPTY_FIELDS;
        }

        // Als de naam langer is dan 50 karakters.
        if (addProductRequestDto.name().length() > 50) {
            return AddProductPageStatus.TOO_LONG_NAME;
        }

        // Nieuw product aanmaken.
        Product product = new Product(
            addProductRequestDto.name(),
            addProductRequestDto.category()
        );

        // Product opslaan in de database.
        productDao.save(product);

        return AddProductPageStatus.SUCCESS;

    }

}
