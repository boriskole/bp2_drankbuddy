package nl.adacademie.drankbuddy.dto;

import nl.adacademie.drankbuddy.entity.Category;

public record AddProductRequestDto(Category category, String name) {

}
