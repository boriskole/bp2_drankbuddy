package nl.adacademie.drankbuddy.entity.mutation;

public enum StockMutationType {

    SALE("Verkoop"),
    DELIVERY("Levering"),
    CORRECTION("Correctie");

    private final String title;

    StockMutationType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

}
