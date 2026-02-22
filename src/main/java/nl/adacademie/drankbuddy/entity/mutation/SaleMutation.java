package nl.adacademie.drankbuddy.entity.mutation;

import nl.adacademie.drankbuddy.entity.Product;

import java.time.LocalDateTime;

/**
 * Een verandering in voorraad voor wanneer een {@link Product} verkocht wordt.
 */
public class SaleMutation extends StockMutation {

    public SaleMutation(int stockChange, Product product) {
        super(stockChange, product);
    }

    public SaleMutation(int id, int stockChange, LocalDateTime date, Product product) {
        super(id, stockChange, date, product);
    }

}
