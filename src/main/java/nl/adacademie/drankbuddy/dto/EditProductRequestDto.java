package nl.adacademie.drankbuddy.dto;

import nl.adacademie.drankbuddy.entity.Category;

public record EditProductRequestDto(int id, Category category, String name) {

}
