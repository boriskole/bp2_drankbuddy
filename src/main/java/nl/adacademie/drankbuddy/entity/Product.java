package nl.adacademie.drankbuddy.entity;

import nl.adacademie.drankbuddy.entity.mutation.StockMutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Een (drank-)product. Onderdeel van een {@link Category}.
 */
public class Product {

    private int id;
    private String name;
    private Category category;
    private List<StockMutation> stockMutations;
    private int currentStock;

    public Product() { }

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Product(String name, Category category, int currentStock) {
        this.name = name;
        this.category = category;
        this.currentStock = currentStock;
    }

    public Product(int id, String name, Category category, List<StockMutation> stockMutations) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stockMutations = stockMutations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<StockMutation> getStockMutations() {
        return stockMutations;
    }

    public void setStockMutations(List<StockMutation> stockMutations) {
        this.stockMutations = stockMutations;
    }

    public void addStockMutation(StockMutation stockMutation) {
        if (stockMutations == null) {
            stockMutations = new ArrayList<>();
        }

        stockMutations.add(stockMutation);
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    @Override
    public String toString() {
        return String.format("%s (voorraad: %s)", name, currentStock);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass())
            return false;

        Product product = (Product) other;

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
