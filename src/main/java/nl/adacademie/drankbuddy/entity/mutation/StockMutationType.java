package nl.adacademie.drankbuddy.entity.mutation;

public enum StockMutationType {

    CORRECTION("Correctie"),
    SALE("Verkoop"),
    DELIVERY("Levering");

    private final String title;

    StockMutationType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
