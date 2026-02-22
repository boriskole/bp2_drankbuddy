package nl.adacademie.drankbuddy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Een collectie van producten.
 *
 * @see Product
 */
public class Category {

    private int id;
    private String name;
    private Account owningAccount;
    private List<Product> products;
    private int amountOfProducts;

    public Category() { }

    public Category(String name, Account owningAccount) {
        this.name = name;
        this.owningAccount = owningAccount;
    }

    public Category(String name, Account owningAccount, int amountOfProducts) {
        this.name = name;
        this.owningAccount = owningAccount;
        this.amountOfProducts = amountOfProducts;
    }

    public Category(int id, String name, Account owningAccount, List<Product> products) {
        this.id = id;
        this.name = name;
        this.owningAccount = owningAccount;
        this.products = products;
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

    public Account getOwningAccount() {
        return owningAccount;
    }

    public void setOwningAccount(Account owningAccount) {
        this.owningAccount = owningAccount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }

        products.add(product);
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(int amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass())
            return false;

        Category category = (Category) other;

        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
