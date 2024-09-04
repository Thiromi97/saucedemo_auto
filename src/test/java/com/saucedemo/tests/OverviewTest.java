package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutOverviewPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OverviewTest extends TestBase {

    @Test
    public void verifyProductDetailsAreCorrect(){
        loginAsStandardUser();
        addProductsToCart();
        CartPage cartPage = new CartPage(webDriver);
        HashMap<String,String> cartItemNames = cartPage.getCartItemNames();
        HashMap<String,String> cartItemDescriptions = cartPage.getCartItemDescriptions();
        HashMap<String,String> cartItemPrices = cartPage.getCartItemPrices();
        clickCheckoutButtonOfCartPage();
        fillAllCheckoutFieldsOfCheckoutDetailPage();
        checkOverviewItemDetailsMatchCartDetails(cartItemNames, cartItemDescriptions, cartItemPrices);
    }

    @Test
    public void verifyTheItemTotal(){
        getPreconditions();
        verifyItemTotalIsSumOfAllProductPrices();

    }

    @Test
    public void verifyTheTotal(){
        getPreconditions();
        verifyTotalIsSumOfItemTotalAndTax();
    }


    @Test
    public void verifyFinishButtonFunctionality(){
        getPreconditions();
        clickOnFinishButtonOfOverviewPage();
        verifyThankYouPageLoadedSuccessfully();
    }


    @Test
    public void verifyCancelButtonFunctionality() {
        getPreconditions();
        clickOnOverviewCancelButton();
        verifyProductPageLoadedSuccessfully();
    }


    private void getPreconditions() {
        loginAsStandardUser();
        addProductsToCart();
        clickCheckoutButtonOfCartPage();
        fillAllCheckoutFieldsOfCheckoutDetailPage();
    }

    private static void verifyItemTotalIsSumOfAllProductPrices() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        overviewPage.checkItemTotalIsSumOfProductPrices();
    }

    private static void verifyTotalIsSumOfItemTotalAndTax() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        overviewPage.checkTotalIsSumOfItemTotalAndTax();
    }

    private static void verifyThankYouPageLoadedSuccessfully() {
        CheckoutCompletePage completePage = new CheckoutCompletePage(webDriver);
        completePage.verifyNavigationToThankYouPage();
    }


    private static void clickOnOverviewCancelButton() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        overviewPage.clickCancel();
    }

    private void putProductDetails(List<WebElement> productNames, HashMap<String, String> productNamesMap, String ProductName) {
        for (int i = 0; i < productNames.size(); i++) {
            WebElement productName = productNames.get(i);
            String txtProductName = productName.getText();
            productNamesMap.put(ProductName + (i + 1), txtProductName);
        }
    }

    private void checkOverviewItemDetailsMatchCartDetails(HashMap<String, String> cartItemNames, HashMap<String, String> cartItemDescriptions, HashMap<String, String> cartItemPrices) {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        HashMap<String, String> productNamesMap = new HashMap<>();
        HashMap<String, String> productDescriptionMap = new HashMap<>();
        HashMap<String, String> productPricesMap = new HashMap<>();
        putProductDetails(overviewPage.getProductNames(), productNamesMap, "ProductName");
        putProductDetails(overviewPage.getProductDescriptions(), productDescriptionMap, "ProductDescription");
        putProductDetails(overviewPage.getProductPrices(), productPricesMap, "ProductPrice");
        for(int i=0;i<overviewPage.getNoOfItems();i++){
            assertThat(cartItemNames.get("CartItemName"+(i+1))).isEqualTo(productNamesMap.get("ProductName"+(i+1)));
            assertThat(cartItemDescriptions.get("CartItemDescription"+(i+1))).isEqualTo(productDescriptionMap.get("ProductDescription"+(i+1)));
            assertThat(cartItemPrices.get("CartItemPrice"+(i+1))).isEqualTo(productPricesMap.get("ProductPrice"+(i+1)));
        }
    }
}
