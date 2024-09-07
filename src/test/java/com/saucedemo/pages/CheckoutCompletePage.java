package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void clickBackToHomeBtn() {
        btnBackToHome.click();
    }

    public WebElement getConfirmationMsg() {
        return txtConfirmMessage;
    }
}
