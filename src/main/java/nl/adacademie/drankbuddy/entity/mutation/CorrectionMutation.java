package nl.adacademie.drankbuddy.entity.mutation;

import nl.adacademie.drankbuddy.entity.Product;

import java.time.LocalDateTime;

/**
 * Een correctie in de voorraad voor een {@link Product}
 */
public class CorrectionMutation extends StockMutation {

    public CorrectionMutation(int stockChange, Product product) {
        super(stockChange, product);
    }

    public CorrectionMutation(int id, int stockChange, LocalDateTime date, Product product) {
        super(id, stockChange, date, product);
    }

}
