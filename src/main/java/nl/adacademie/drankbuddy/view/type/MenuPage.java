package nl.adacademie.drankbuddy.view.type;

import nl.adacademie.drankbuddy.view.dashboard.CategoryOverviewView;
import nl.adacademie.drankbuddy.view.dashboard.SidebarComponent;
import nl.adacademie.drankbuddy.view.dashboard.ProductOverviewView;
import nl.adacademie.drankbuddy.view.dashboard.StockMutationsOverviewView;

/**
 * Wordt gebruikt in de {@link SidebarComponent} om te bepalen welke pagina momenteel actief is.
 *
 * @see ProductOverviewView
 * @see CategoryOverviewView
 * @see StockMutationsOverviewView
 */
public enum MenuPage {
    PRODUCTS,
    CATEGORIES,
    STOCK_MUTATIONS
}
