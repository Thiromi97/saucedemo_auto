package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutCompletePage {
    final WebDriver webDriver;

    @FindBy(css = "[data-test='title']")
    WebElement txtPageTitle;

    @FindBy(css = ".complete-header")
    WebElement txtConfirmMessage;

    @FindBy(id = "back-to-products")
    WebElement btnBackToHome;

    public CheckoutCompletePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public String getPageTitle() {
        return txtPageTitle.getText();
    }

    public void verifyNavigationToThankYouPage() {
        assertThat(getPageTitle()).isEqualTo("Checkout: Complete!");
    }

    public void checkOrderConfirmationMsg() {
        assertThat(txtConfirmMessage.getText()).isEqualTo("Thank you for your order!");
    }

    public void clickBackToHomeBtn() {
        btnBackToHome.click();
    }
}
