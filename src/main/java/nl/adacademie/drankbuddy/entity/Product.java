package nl.adacademie.drankbuddy.entity;

import nl.adacademie.drankbuddy.entity.mutation.StockMutation;

import java.util.ArrayList;
import java.util.List;

/**
 * Een (drank-)product. Onderdeel van een {@link Category}.
 */
public class Product {

    private int id;
    private String name;
    private Category category;
    private List<StockMutation> stockMutations;

    public Product() { }

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
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

}
