package com.saucedemo.tests;

import com.github.javafaker.Faker;
import com.saucedemo.TestBase;
import com.saucedemo.pages.CheckoutDetailPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutTest extends TestBase {

    @Test
    public void verifyEmptyInputFieldsSubmission(){
        getPreconditions();
        clearAllDataInCheckoutPage();
        verifyDisplayOfErrorMsgFirstNameIsRequired();
    }

    @Test
    public void verifyEmptyFirstNameFieldSubmission(){
        getPreconditions();
        fillAllFieldsExceptFirstName();
        verifyDisplayOfErrorMsgFirstNameIsRequired();
    }

    @Test
    public void verifyEmptyLastNameFieldSubmission(){
        getPreconditions();
        fillAllFieldsExceptLastName();
        verifyDisplayOfErrorMsgLastNameIsRequired();
    }

    @Test
    public void verifyEmptyPostalCodeFieldSubmission(){
        getPreconditions();
        fillAllFieldsExceptPostalCode();
        verifyDisplayOfErrorMsgPostalCodeIsRequired();
    }

    @Test
    public void verifyValidFieldSubmission(){
        getPreconditions();
        fillAllCheckoutFieldsOfCheckoutDetailPage();
        verifyOverviewPageLoadedSuccessfully();
    }

    @Test
    public void verifyFirstNameFieldAcceptValidInput(){
        getPreconditions();
        verifyFirstNameFieldAllowToEnterLetters();
    }

    @Test
    public void verifyFirstNameFieldNotAcceptInvalidInput(){
        getPreconditions();
        verifyFirstNameFieldNotAllowToEnterNumbersAndSpecialCharacters();
    }

    @Test
    public void verifyLastNameFieldAcceptValidInput(){
        getPreconditions();
        verifyLastNameFieldAllowToEnterLetters();
    }

    @Test
    public void verifyLastNameFieldNotAcceptInvalidInput(){
        getPreconditions();
        verifyLastNameFieldNotAllowToEnterNumbersAndSpecialCharacters();
    }

    @Test
    public void verifyPostalCodeFieldAcceptValidInput(){
        getPreconditions();
        verifyPostalCodeFieldAllowToEnterValidData();
    }

    @Test
    public void verifyCancelButtonFunctionality(){
        getPreconditions();
        clickOnCancelButton();
        verifyCartPageLoadedSuccessfully();
    }

    private void getPreconditions() {
        loginAsStandardUser();
        addProductsToCart();
        clickCheckoutButtonOfCartPage();
    }

    private static void verifyOverviewPageLoadedSuccessfully() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);
        WebElement txtOverviewTitle = checkoutOverviewPage.getPageTitle();
        assertThat(txtOverviewTitle.getText()).isEqualTo("Checkout: Overview");
    }


    private static void clickOnCancelButton() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.clickCancel();
    }

    private void verifyPostalCodeFieldAllowToEnterValidData() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        Faker faker = new Faker();
        String postalCode = faker.address().zipCode();
        WebElement postalCodeField = checkoutDetailPage.getPostalCode();
        postalCodeField.sendKeys(postalCode);
        assertThat(postalCodeField.getAttribute("value")).isEqualTo(postalCode);
    }

    private static void verifyFirstNameFieldNotAllowToEnterNumbersAndSpecialCharacters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement firstNameField = checkoutDetailPage.getFirstName();
        enterInvalidInput(firstNameField);
    }

    private static void verifyLastNameFieldNotAllowToEnterNumbersAndSpecialCharacters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement lastNameField = checkoutDetailPage.getLastName();
        enterInvalidInput(lastNameField);
    }

    private static void enterInvalidInput(WebElement field) {
        String input = "1234@#$";
        field.sendKeys(input);
        assertThat(field.getAttribute("value")).isNotEqualTo(input);
    }


    private static void verifyFirstNameFieldAllowToEnterLetters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement txtFirstName = checkoutDetailPage.getFirstName();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        txtFirstName.sendKeys(firstName);
        assertThat(txtFirstName.getAttribute("value")).isEqualTo(firstName);
    }
    private static void verifyLastNameFieldAllowToEnterLetters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement txtLastName = checkoutDetailPage.getLastName();
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        txtLastName.sendKeys(lastName);
        assertThat(txtLastName.getAttribute("value")).isEqualTo(lastName);
    }

        private static void fillAllFieldsExceptFirstName() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.clearFirstNameField().fillLastName().fillPostalCode().clickContinue();
    }

    private static void fillAllFieldsExceptLastName() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.fillFirstName().clearLastNameField().fillPostalCode().clickContinue();
    }

    private static void fillAllFieldsExceptPostalCode() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.fillFirstName().fillLastName().clearPostalCodeField().clickContinue();
    }

    private static void verifyDisplayOfErrorMsgFirstNameIsRequired() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement txtErrorMsg = checkoutDetailPage.getErrorMsg();
        assertThat(txtErrorMsg.getText()).contains("First Name is required");
    }

    private static void verifyDisplayOfErrorMsgLastNameIsRequired() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement txtErrorMsg = checkoutDetailPage.getErrorMsg();
        assertThat(txtErrorMsg.getText()).contains("Last Name is required");
    }

    private static void verifyDisplayOfErrorMsgPostalCodeIsRequired() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        WebElement txtErrorMsg = checkoutDetailPage.getErrorMsg();
        assertThat(txtErrorMsg.getText()).contains("Postal Code is required");
    }

    private static void clearAllDataInCheckoutPage() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.clearFirstNameField().clearLastNameField().clearPostalCodeField().clickContinue();
    }


}
