package nl.adacademie.drankbuddy.view.type;

public enum AddStockMutationPageStatus {
    NONE, // Niks. De standaard view.
    EMPTY_FIELDS, // Niet alle velden zijn ingevuld.
    NO_ZERO, // Je kan geen nul invoeren.
    UNDER_ZERO, // Je kan geen negatieve voorraad hebben.
    SUCCESS // Er is succesvol een voorraadmutatie aangemaakt.
}
