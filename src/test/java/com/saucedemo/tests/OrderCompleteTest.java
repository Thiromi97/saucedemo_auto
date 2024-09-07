package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CheckoutCompletePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderCompleteTest extends TestBase {
    @Test
    public void verifyOrderConfirmationMsg(){
        getPreconditions();
        verifyDisplayOfOrderConfirmedMessage();
    }

    @Test
    public void verifyBackToHomeButtonFunctionality(){
        getPreconditions();
        clickOnBackToHomeButton();
        verifyProductPageLoadedSuccessfully();
    }

    private static void clickOnBackToHomeButton() {
        CheckoutCompletePage completePage = new CheckoutCompletePage(webDriver);
        completePage.clickBackToHomeBtn();
    }

    private void getPreconditions() {
        loginAsStandardUser();
        addProductsToCart();
        clickCheckoutButtonOfCartPage();
        fillAllCheckoutFieldsOfCheckoutDetailPage();
        clickOnFinishButtonOfOverviewPage();
    }

    private static void verifyDisplayOfOrderConfirmedMessage() {
        CheckoutCompletePage completePage = new CheckoutCompletePage(webDriver);
        WebElement txtConfirmationMsg = completePage.getConfirmationMsg();
        assertThat(txtConfirmationMsg.getText()).isEqualTo("Thank you for your order!");
    }
}
