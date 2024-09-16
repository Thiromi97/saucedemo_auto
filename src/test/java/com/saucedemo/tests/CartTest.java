package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutDetailPage;
import com.saucedemo.pages.ProductsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        verifyProductPageLoadedSuccessfully();
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

    private void verifyEmptyCartNotAllowNavigationToCheckoutPage() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickCheckOut();
        verifyCartPageLoadedSuccessfully();
    }


    private static void verifyDirectToCheckoutPageSuccessfully() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement pageTitle = checkoutDetailPage.getPageTitle();
        assertThat(pageTitle.getText()).isEqualTo("Checkout: Your Information");
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
        List<WebElement> productNames = productsPage.getProductNamesList();
        List<WebElement> productsDescriptions = productsPage.getProductDescriptionList();
        List<WebElement> productPrices = productsPage.getProductPricesList();
        HashMap<String, String> productNamesMap = new HashMap<>();
        HashMap<String, String> productDescriptionMap = new HashMap<>();
        HashMap<String, String> productPricesMap = new HashMap<>();
        putProductDetails(productNames, productNamesMap, "ProductName");
        putProductDetails(productsDescriptions, productDescriptionMap, "ProductDescription");
        putProductDetails(productPrices, productPricesMap, "ProductPrice");
        CartPage cartPage = new CartPage(webDriver);
        HashMap<String,String> cartItemNames = cartPage.getCartItemNames();
        HashMap<String,String> cartItemDescriptions = cartPage.getCartItemDescriptions();
        HashMap<String,String> cartItemPrices = cartPage.getCartItemPrices();
        for(int i=0;i<cartPage.getCartItemSize();i++){
            Assertions.assertThat(cartItemNames.get("CartItemName"+(i+1))).isEqualTo(productNamesMap.get("ProductName"+(i+1)));
            Assertions.assertThat(cartItemDescriptions.get("CartItemDescription"+(i+1))).isEqualTo(productDescriptionMap.get("ProductDescription"+(i+1)));
            Assertions.assertThat(cartItemPrices.get("CartItemPrice"+(i+1))).isEqualTo(productPricesMap.get("ProductPrice"+(i+1)));
        }
    }

    private static void putProductDetails(List<WebElement> productNames, HashMap<String, String> productNamesMap, String ProductName) {
        for (int i = 0; i < productNames.size(); i++) {
            WebElement productName = productNames.get(i);
            String txtProductName = productName.getText();
            productNamesMap.put(ProductName + (i + 1), txtProductName);
        }
    }


}
