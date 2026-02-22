package nl.adacademie.drankbuddy.controller.category;

import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.dto.AddCategoryRequestDto;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.interfaces.CategoryDaoInterface;
import nl.adacademie.drankbuddy.view.type.AddCategoryPageStatus;

public class AddCategoryController {

    private final CategoryDaoInterface categoryDao;

    public AddCategoryController(CategoryDaoInterface categoryDao) {
        this.categoryDao = categoryDao;
    }

    public AddCategoryPageStatus addCategory(AddCategoryRequestDto addCategoryRequestDto) {

        // Checken of de naam niet null of leeg is.
        if (addCategoryRequestDto.name() == null || addCategoryRequestDto.name().isBlank()) {
            return AddCategoryPageStatus.EMPTY_FIELDS;
        }

        // Checken of de naam niet langer dan 50 karakters is.
        if (addCategoryRequestDto.name().length() > 50) { // Is nodig omdat de naam een varchar(50) is.
            return AddCategoryPageStatus.TOO_LONG_NAME;
        }

        // Nieuwe instantie maken van een categorie.
        Category category = new Category(
            addCategoryRequestDto.name(),
            DrankBuddy.loggedInAccount // De ingelogd account gebruiken.
        );
        categoryDao.save(category); // De categorie opslaan in de database.

        return AddCategoryPageStatus.SUCCESS;

    }

}
