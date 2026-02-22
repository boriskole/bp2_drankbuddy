package nl.adacademie.drankbuddy.entity.mutation;

import nl.adacademie.drankbuddy.entity.Product;

import java.time.LocalDateTime;

/**
 * Een verandering in voorraad voor een nieuwe levering van een {@link Product}
 */
public class DeliveryMutation extends StockMutation {

    public DeliveryMutation(int stockChange, Product product) {
        super(stockChange, product);
    }

    public DeliveryMutation(int id, int stockChange, LocalDateTime date, Product product) {
        super(id, stockChange, date, product);
    }

}
