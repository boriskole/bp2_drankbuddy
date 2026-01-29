package nl.adacademie.drankbuddy.view.status;

/**
 * De verschillende soorten fouten die kunnen optreden tijdens registreren.
 * <p>
 * Dit wordt gebruikt om te kunnen bepalen welke soort foutmelding gelaten zien moet worden.
 * <p>
 * @see nl.adacademie.drankbuddy.view.RegisterPageView
 */
public enum RegisterPageStatus {
    NONE, // Geen fout
    EMPTY_FIELDS, // 1 of meerdere velden zijn leeg
    USERNAME_EXISTS, // Gebruikersnaam bestaat al
    PASSWORD_MISMATCH, // Wachtwoorden komen niet overeen
    REGISTER_SUCCESS // Registratie is gelukt
}
