package com.saucedemo.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    public void verifyNavigateToCheckoutPageSuccessfully() {
        assertThat(txtCheckoutTitle.getText()).isEqualTo("Checkout: Your Information");
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

    public void checkErrorMsgFirstNameIsRequired() {
        assertThat(txtErrorMsg.getText()).contains("First Name is required");
    }

    public void checkErrorMsgLastNameIsRequired() {
        assertThat(txtErrorMsg.getText()).contains("Last Name is required");
    }

    public void checkErrorMsgPostalCodeIsRequired() {
        assertThat(txtErrorMsg.getText()).contains("Postal Code is required");
    }

    public void verifyAlphabeticInputValidationOfFirstNameField() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        txtFirstName.sendKeys(firstName);
        assertThat(txtFirstName.getAttribute("value")).isEqualTo(firstName);
    }

    public void checkNumericAndSpecialCharactersCanInputToFirstNameField() {
        enterInvalidInput(txtFirstName);
    }

    public void checkNumericAndSpecialCharacterCanInputToLastNameField() {
        enterInvalidInput(txtLastName);
    }

    private void enterInvalidInput(WebElement txtFirstName) {
        String input = "1234@#$";
        txtFirstName.sendKeys(input);
        assertThat(txtFirstName.getAttribute("value")).isNotEqualTo(input);
    }

    public void verifyAlphabeticInputValidationOfLastNameField() {
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        txtLastName.sendKeys(lastName);
        assertThat(txtLastName.getAttribute("value")).isEqualTo(lastName);
    }

    public void verifyInputValidationOfPostalCodeField() {
        Faker faker = new Faker();
        String postalCode = faker.address().zipCode();
        txtPostalCode.sendKeys(postalCode);
        assertThat(txtPostalCode.getAttribute("value")).isEqualTo(postalCode);
    }

    public void clickCancel() {
        btnCancel.click();
    }
}
