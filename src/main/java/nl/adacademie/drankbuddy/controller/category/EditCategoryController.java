package nl.adacademie.drankbuddy.controller.category;

import nl.adacademie.drankbuddy.dto.EditCategoryRequestDto;
import nl.adacademie.drankbuddy.repository.interfaces.CategoryDaoInterface;
import nl.adacademie.drankbuddy.view.type.EditCategoryPageStatus;

public class EditCategoryController {

    private final CategoryDaoInterface categoryDao;

    public EditCategoryController(CategoryDaoInterface categoryDao) {
        this.categoryDao = categoryDao;
    }

    public EditCategoryPageStatus editCategory(EditCategoryRequestDto editCategoryRequestDto) {

        // Checken of de naam niet null of leeg is.
        if (editCategoryRequestDto.name() == null || editCategoryRequestDto.name().isBlank()) {
            return EditCategoryPageStatus.EMPTY_FIELDS;
        }

        // Checken of de naam niet langer dan 50 karakters is.
        if (editCategoryRequestDto.name().length() > 50) { // Is nodig omdat de naam een varchar(50) is.
            return EditCategoryPageStatus.TOO_LONG_NAME;
        }

        categoryDao.update(editCategoryRequestDto.id(), editCategoryRequestDto.name()); // De categorie updaten in de database.

        return EditCategoryPageStatus.SUCCESS;

    }

}
