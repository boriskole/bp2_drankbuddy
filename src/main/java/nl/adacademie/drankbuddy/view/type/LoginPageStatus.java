package nl.adacademie.drankbuddy.view.type;

import nl.adacademie.drankbuddy.view.security.LoginPageView;

/**
 * De verschillende soorten fouten die kunnen optreden tijdens inloggen.
 * <p>
 * Dit wordt gebruikt om te kunnen bepalen welke soort foutmelding gelaten zien moet worden.
 * <p>
 * @see LoginPageView
 */
public enum LoginPageStatus {
    NONE, // Geen fout
    EMPTY_FIELDS, // 1 of meerdere velden zijn leeg
    INVALID_CREDENTIALS, // Ongeldige gebruikersnaam of wachtwoord
    REGISTER_SUCCESS, // Registratie gelukt
    LOGIN_SUCCESS // Inlog gelukt
}
