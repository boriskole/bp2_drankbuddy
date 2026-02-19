package nl.adacademie.drankbuddy.view.type;

import nl.adacademie.drankbuddy.view.category.CategoryOverviewView;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.product.ProductOverviewView;
import nl.adacademie.drankbuddy.view.stockmutation.StockMutationsOverviewView;

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
