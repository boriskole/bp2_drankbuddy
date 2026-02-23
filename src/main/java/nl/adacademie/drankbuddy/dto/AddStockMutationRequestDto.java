package nl.adacademie.drankbuddy.dto;

import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.entity.mutation.StockMutationType;

public record AddStockMutationRequestDto(Product product, StockMutationType mutationType, int amount) {

}
