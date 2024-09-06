package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutDetailPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.annotations.Test;

public class CartTest extends TestBase {

    @Test
    public void verifyCartItemDetailsAreAccurate(){
        getPreconditions();
        verifyCartDisplaysCorrectProductDetails();
    }

    @Test
    public void verifyRemovingProductFromCart(){
        getPreconditions();
        removeProductFromCart();
        verifyProductIsRemovedFromTheCart();
        verifyCartBadgeUpdateAccordingly();
    }

    @Test
    public void verifyContinueShoppingButtonFunctionality(){
        getPreconditions();
        clickContinueShoppingButtonOfCartPage();
        verifyRedirectToProductPageSuccessfully();
        verifyCartItemsCountRemainedUnchanged();
    }

    @Test
    public void verifyCheckoutButtonFunctionality(){
        getPreconditions();
        clickCheckoutButtonOfCartPage();
        verifyDirectToCheckoutPageSuccessfully();
    }

    @Test
    public void verifyEmptyCartCannotProceedToCheckoutPage(){
        loginAsStandardUser();
        goToCartPage();
        verifyEmptyCartNotAllowNavigationToCheckoutPage();
    }

    private void getPreconditions() {
        loginAsStandardUser();
        addProductsToCart();
    }

    private static void verifyEmptyCartNotAllowNavigationToCheckoutPage() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickCheckOut();
        cartPage.verifyNavigationToCartPage();
    }


    private static void verifyDirectToCheckoutPageSuccessfully() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.verifyNavigateToCheckoutPageSuccessfully();
    }

    private static void verifyCartItemsCountRemainedUnchanged() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.checkCartBadgeIsUpdated();
    }

    private static void verifyRedirectToProductPageSuccessfully() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifySuccessNavigationToProductPage();
    }

    private static void clickContinueShoppingButtonOfCartPage() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickContinueShoppingButton();
    }

    private static void verifyCartBadgeUpdateAccordingly() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.verifyCartBadgeUpdated();
    }

    private static void removeProductFromCart() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.removeProduct();
    }

    private static void verifyCartDisplaysCorrectProductDetails() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.checkCartItemDetails();
    }

}
