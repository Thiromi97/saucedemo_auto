package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CheckoutDetailPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import org.testng.annotations.Test;

public class CheckoutTests extends TestBase {

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
        checkoutOverviewPage.verifyNavigationToOverviewPageSuccessfully();
    }


    private static void clickOnCancelButton() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.clickCancel();
    }

    private void verifyPostalCodeFieldAllowToEnterValidData() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.verifyInputValidationOfPostalCodeField();
    }

    private static void verifyFirstNameFieldNotAllowToEnterNumbersAndSpecialCharacters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.checkNumericAndSpecialCharactersCanInputToFirstNameField();
    }

    private static void verifyLastNameFieldNotAllowToEnterNumbersAndSpecialCharacters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.checkNumericAndSpecialCharacterCanInputToLastNameField();
    }

    private static void verifyFirstNameFieldAllowToEnterLetters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.verifyAlphabeticInputValidationOfFirstNameField();
    }
    private static void verifyLastNameFieldAllowToEnterLetters() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.verifyAlphabeticInputValidationOfLastNameField();
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
        checkoutDetailPage.checkErrorMsgFirstNameIsRequired();
    }

    private static void verifyDisplayOfErrorMsgLastNameIsRequired() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.checkErrorMsgLastNameIsRequired();
    }

    private static void verifyDisplayOfErrorMsgPostalCodeIsRequired() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.checkErrorMsgPostalCodeIsRequired();
    }

    private static void clearAllDataInCheckoutPage() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.clearFirstNameField().clearLastNameField().clearPostalCodeField().clickContinue();
    }


}
