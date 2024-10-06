package com.saucedemo.tests;

import com.saucedemo.CartProduct;
import com.saucedemo.OverviewProduct;
import com.saucedemo.Product;
import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.ProductsPage;
import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OverviewTest extends TestBase {

    @Test
    public void verifyProductDetailsAreCorrect(){
        loginAsStandardUser();
        addProductsToCart();
        clickCheckoutButtonOfCartPage();
        fillAllCheckoutFieldsOfCheckoutDetailPage();
        checkOverviewItemDetailsMatchCartDetails();
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
        double total = 0.00;
        for(WebElement price:overviewPage.getProductPrices()){
            String priceText = price.getText().replaceAll("[^0-9.]", "");
            double numProductPrice = Double.parseDouble(priceText);
            total = total +numProductPrice ;
        }
        WebElement txtItemTotal = overviewPage.getItemTotal();
        String itemTotal = txtItemTotal.getText().replaceAll("[^0-9.]","");
        double actualItemTotal = Double.parseDouble(itemTotal);
        assertThat(actualItemTotal).isEqualTo(total);
    }

    private static void verifyTotalIsSumOfItemTotalAndTax() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        WebElement txtItemTotal = overviewPage.getItemTotal();
        String itemTotal = txtItemTotal.getText().replaceAll("[^0-9.]","");
        double numItemTotal = Double.parseDouble(itemTotal);
        WebElement txtTax = overviewPage.getTax();
        String itemTax = txtTax.getText().replaceAll("[^0-9.]","");
        double numItemTax = Double.parseDouble(itemTax);
        double expectedTotal = numItemTotal + numItemTax;
        WebElement txtTotal = overviewPage.getTotal();
        String total = txtTotal.getText().replaceAll("[^0-9.]","");
        double actualTotal = Double.parseDouble(total);
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }

    private static void verifyThankYouPageLoadedSuccessfully() {
        CheckoutCompletePage completePage = new CheckoutCompletePage(webDriver);
        assertThat(completePage.getPageTitle()).isEqualTo("Checkout: Complete!");
    }


    private static void clickOnOverviewCancelButton() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        overviewPage.clickCancel();
    }


    private void checkOverviewItemDetailsMatchCartDetails() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        List<Product> products = productsPage.extractProducts();
        List<OverviewProduct> overviewProducts = overviewPage.extractProducts();
        int productSize = products.size();
        for(int i=0;i<productSize;i++){
            Product product = products.get(i);
            OverviewProduct overviewProduct = overviewProducts.get(i);
            String txtProductName = product.getName();
            String txtProductDescription = product.getDescription();
            String txtProductPrice = product.getPrice();
            String txtOverviewProductName = overviewProduct.getName();
            String txtOverviewProductDescription = overviewProduct.getDescription();
            String txtOverviewProductPrice = overviewProduct.getPrice();
            assertThat(txtOverviewProductName).isEqualTo(txtProductName);
            assertThat(txtOverviewProductDescription).isEqualTo(txtProductDescription);
            assertThat(txtOverviewProductPrice).isEqualTo(txtProductPrice);
        }
    }
}
