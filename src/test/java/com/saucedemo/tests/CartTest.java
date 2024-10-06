package com.saucedemo.tests;

import com.saucedemo.CartProduct;
import com.saucedemo.Product;
import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutDetailPage;
import com.saucedemo.pages.ProductsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartTest extends TestBase {

    private static void verifyDirectToCheckoutPageSuccessfully() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement pageTitle = checkoutDetailPage.getPageTitle();
        assertThat(pageTitle.getText()).isEqualTo(properties.getProperty("checkoutDetailPageTitle"));
    }

    private static void verifyCartItemsCountRemainedUnchanged() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        Assertions.assertThat(productsPage.getCartBadgeCount()).isEqualTo("6");
    }

    private static void clickContinueShoppingButtonOfCartPage() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickContinueShoppingButton();
    }

    private static void verifyCartBadgeUpdateAccordingly() {
        CartPage cartPage = new CartPage(webDriver);
        String cartBadgeCount = cartPage.getCartCount();
        assertThat(cartBadgeCount).isEqualTo("5");
    }

    private static void removeProductFromCart() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.removeProduct();
    }

    private static void verifyCartDisplaysCorrectProductDetails() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        CartPage cartPage = new CartPage(webDriver);
        List<Product> products = productsPage.extractProducts();
        List<CartProduct> cartProducts = cartPage.extractProducts();
        int productSize = products.size();
        for (int i = 0; i < productSize; i++) {
            Product product = products.get(i);
            CartProduct cartProduct = cartProducts.get(i);
            String txtProductName = product.getName();
            String txtProductDescription = product.getDescription();
            String txtProductPrice = product.getPrice();
            String txtCartProductName = cartProduct.getName();
            String txtCartProductDescription = cartProduct.getDescription();
            String txtCartProductPrice = cartProduct.getPrice();
            assertThat(txtCartProductName).isEqualTo(txtProductName);
            assertThat(txtCartProductDescription).isEqualTo(txtProductDescription);
            assertThat(txtCartProductPrice).isEqualTo(txtProductPrice);
        }
    }

    @Test
    public void verifyCartItemDetailsAreAccurate() {
        getPreconditions();
        verifyCartDisplaysCorrectProductDetails();
    }

    @Test
    public void verifyRemovingProductFromCart() {
        getPreconditions();
        removeProductFromCart();
        verifyProductIsRemovedFromTheCart();
        verifyCartBadgeUpdateAccordingly();
    }

    @Test
    public void verifyContinueShoppingButtonFunctionality() {
        getPreconditions();
        clickContinueShoppingButtonOfCartPage();
        verifyProductPageLoadedSuccessfully();
        verifyCartItemsCountRemainedUnchanged();
    }

    @Test
    public void verifyCheckoutButtonFunctionality() {
        getPreconditions();
        clickCheckoutButtonOfCartPage();
        verifyDirectToCheckoutPageSuccessfully();
    }

    @Test
    public void verifyEmptyCartCannotProceedToCheckoutPage() {
        loginAsStandardUser();
        goToCartPage();
        verifyEmptyCartNotAllowNavigationToCheckoutPage();
    }

    private void getPreconditions() {
        loginAsStandardUser();
        addProductsToCart();
    }

    private void verifyEmptyCartNotAllowNavigationToCheckoutPage() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickCheckOut();
        verifyCartPageLoadedSuccessfully();
    }


}
