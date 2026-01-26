package nl.adacademie.drankbuddy.entity.mutation;

import nl.adacademie.drankbuddy.entity.Product;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Een verandering in voorraad voor een {@link Product}.
 */
public abstract class StockMutation {

    private int id;
    private int stockChange;
    private LocalDate date;
    private Product product;

    public StockMutation() { }

    public StockMutation(int stockChange, Product product) {
        this.stockChange = stockChange;
        this.product = product;
        this.date = LocalDate.now();
    }

    public StockMutation(int id, int stockChange, LocalDate date, Product product) {
        this.id = id;
        this.stockChange = stockChange;
        this.date = date;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockChange() {
        return stockChange;
    }

    public void setStockChange(int stockChange) {
        this.stockChange = stockChange;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass())
            return false;

        StockMutation that = (StockMutation) other;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StockMutation{" +
            "id=" + id +
            ", stockChange=" + stockChange +
            ", date=" + date +
            ", product=" + product +
            '}';
    }

}
