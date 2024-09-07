package com.saucedemo.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutDetailPage {

   final WebDriver webDriver;


    @FindBy(id = "first-name")
    WebElement txtFirstName;

    @FindBy(id = "last-name")
    WebElement txtLastName;

    @FindBy(id = "postal-code")
    WebElement txtPostalCode;

    @FindBy(id = "continue")
    WebElement btnContinue;

    @FindBy(css = ".title")
    WebElement txtCheckoutTitle;

    @FindBy(css = "h3[data-test='error']")
    WebElement txtErrorMsg;

    @FindBy(id = "cancel")
    WebElement btnCancel;

    public CheckoutDetailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public CheckoutDetailPage fillFirstName() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        txtFirstName.sendKeys(firstName);
        return this;
    }

    public CheckoutDetailPage fillLastName() {
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        txtLastName.sendKeys(lastName);
        return this;
    }

    public CheckoutDetailPage fillPostalCode() {
        Faker faker = new Faker();
        String postalCode = faker.address().zipCode();
        txtPostalCode.sendKeys(postalCode);
        return this;
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public CheckoutDetailPage clearFirstNameField() {
        txtFirstName.clear();
        return this;
    }

    public CheckoutDetailPage clearLastNameField() {
        txtLastName.clear();
        return this;
    }

    public CheckoutDetailPage clearPostalCodeField() {
        txtPostalCode.clear();
        return this;
    }


    public void clickCancel() {
        btnCancel.click();
    }

    public WebElement getPageTitle() {
        return txtCheckoutTitle;
    }

    public WebElement getPostalCode() {
        return txtPostalCode;
    }

    public WebElement getFirstName() {
        return txtFirstName;
    }

    public WebElement getLastName() {
        return txtLastName;
    }

    public WebElement getErrorMsg() {
        return txtErrorMsg;
    }
}
