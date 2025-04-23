package tests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TaskNineteenTest extends TestBase {

    @Test
    public void successAddAndDeleteProductsInCart() {
        int productCounter = 3;
        int itemsIncart;


        app.defaultPage.defaultPageOpen();

        for (int i = 1; i < productCounter + 1; i++) {
            app.defaultPage.openFirstPopularProductCard();


            if (app.productCardPage.isSizesOnCard()) {
                app.productCardPage.clickFirstSize();
            }

            app.productCardPage.addProductToCartAndReturnOnPreviousPage(i);
        }

        app.cartPage.goToCartPage();

        itemsIncart = app.cartPage.getSizeOfCart();

        app.cartPage.deleteAllProductsFromCartByRemoveButton(itemsIncart, productCounter);

        Assertions.assertTrue(app.cartPage.isEmptyCartTextPresent(), "На странцие нет надписи об отсутствии товара");
    }
}
