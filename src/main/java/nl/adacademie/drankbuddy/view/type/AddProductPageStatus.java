package nl.adacademie.drankbuddy.view.type;

public enum AddProductPageStatus {
    NONE, // Niks. De standaard view.
    EMPTY_FIELDS, // Niet alle velden zijn ingevuld.
    TOO_LONG_NAME, // De naam is te langer dan 50 karakters.
    SUCCESS // Er is succesvol een product aangemaakt.
}
