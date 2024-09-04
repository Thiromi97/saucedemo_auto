package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.pages.LoginPage;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {

    @Test
    public void verifyLoginWithValidCredentials(){
        sendUserCredentials("standard_user");
        verifyProductPageLoadedSuccessfully();
    }

    @Test
    public void verifyLoginWithInvalidUsername(){
        sendInvalidUsername();
        verifyErrorMessageForIncorrectCredentials();
    }


    @Test
    public void verifyLoginWithInvalidPassword(){
        sendInvalidPassword();
        verifyErrorMessageForIncorrectCredentials();
    }

    @Test
    public void verifyLoginWithInvalidCredentials(){
        sendInvalidCredentials();
        verifyErrorMessageForIncorrectCredentials();
    }

    @Test
    public void verifyLoginWithBlankUsername(){
      sendBlankUsername();
      verifyErrorMessageForUsernameIsRequired();
    }


    @Test
    public void verifyLoginWithBlankPassword(){
        sendBlankPassword();
        verifyErrorMessageForPasswordIsRequired();
    }


    @Test
    public void verifyLoginWithBlankCredentials(){
        sendBlankCredentials();
        verifyErrorMessageForUsernameIsRequired();
    }


    @Test
    public void verifyLoginWithLockedUserCredentials(){
        sendUserCredentials("locked_out_user");
        verifyErrorMessageForLockedOutUser();
    }


    @Test
    public void verifyLoginWithProblemUserCredentials(){
        sendUserCredentials("problem_user");
        verifyProductPageLoadedSuccessfully();
        verifyProductImagesAreUnique();
        verifyAddToCartButtonsFunctionProperly();
        verifyRemoveButtonsFunctionProperly();
        verifyCorrectProductDetailPageNavigation();
    }


    @Test
    public void verifyLoginWithPerformanceGlitchUserCredentials(){
        sendUserCredentials("performance_glitch_user");
        verifyProductPageLoadedSuccessfully();
    }

    @Test
    public void verifyLoginWithVisualUserCredentials(){
        sendUserCredentials("visual_user");
        verifyProductPageLoadedSuccessfully();
        verifyDisplayingCorrectProductImage();
    }

    @Test
    public void verifyLoginWithErrorUserCredentials(){
        sendUserCredentials("error_user");
        verifyProductPageLoadedSuccessfully();
        verifyAddToCartButtonsFunctionProperly();
        verifyRemoveButtonsFunctionProperly();
        verifyCompletionOfCheckoutSuccessfully();
    }

    @Test
    public void verifyLogoutFunctionality(){
        verifyLoginWithValidCredentials();
        selectLogout();
        verifyLoginPageLoadedSuccessfully();
    }

    @Test
    public void verifyResetAppStateFunctionality(){
        verifyLoginWithValidCredentials();
        selectResetAppState();
        verifyCartIsReset();
        verifyAllRemoveButtonResetToAddToCartButtons();
    }

    @Test
    public void verifyAboutFunctionality(){
        verifyLoginWithValidCredentials();
        selectAbout();
        verifyHomePageLoadedSuccessfully();
    }

    @Test
    public void verifyAllItemFunctionality(){
        verifyLoginWithValidCredentials();
        selectAllItems();
        verifyAllItemsAreDisplaying();
    }

    private static void sendUserCredentials(String username) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterUsername(username).enterPassword("secret_sauce").clickLogin();
    }

    private static void sendInvalidUsername() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterInvalidUsername("standard_user").enterPassword("secret_sauce").clickLogin();
    }

    private static void sendInvalidPassword() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterUsername("standard_user").enterInvalidPassword("secret_sauce").clickLogin();
    }

    private static void verifyErrorMessageForIncorrectCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.verifyUsernameAndPasswordDoNotMatchErrorMessage();
    }

    private static void sendInvalidCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterInvalidUsername("standard_user").enterInvalidPassword("secret_sauce").clickLogin();
    }

    private static void verifyErrorMessageForUsernameIsRequired() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.verifyUsernameIsRequiredErrorMessage();
    }

    private static void verifyErrorMessageForLockedOutUser() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.verifyUserIsLockedErrorMessage();
    }

    private static void sendBlankUsername() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearUsername().enterPassword("secret_sauce").clickLogin();
    }

    private static void sendBlankPassword() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterUsername("standard_user").clearPassword().clickLogin();
    }

    private static void verifyErrorMessageForPasswordIsRequired() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.verifyPasswordIsRequiredErrorMessage();
    }

    private static void sendBlankCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearUsername().clearPassword().clickLogin();
    }

    private static void verifyCorrectProductDetailPageNavigation() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyIncorrectProductDetailPageNavigation();
    }

    private static void verifyProductImagesAreUnique() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyProductImagesDuplication();
    }

    private static void verifyDisplayingCorrectProductImage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        productsPage.verifyProductImagesMismatching();
    }

    private static void verifyCompletionOfCheckoutSuccessfully() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifySuccessCompletionOfCheckout();
    }

    private static void verifyRemoveButtonsFunctionProperly() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyRemoveBtnMalfunction();
    }

    private static void verifyAddToCartButtonsFunctionProperly() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyAddToCartBtnMalfunction();
    }

    private static void verifyLoginPageLoadedSuccessfully() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.verifySuccessLoginPageNavigation();
    }

    private static void selectLogout() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickLogout();
    }

    private static void verifyAllRemoveButtonResetToAddToCartButtons() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyAllRemoveButtonsReset();
    }

    private static void verifyCartIsReset() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyCartIsEmpty();
    }

    private static void selectResetAppState() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.addProductToCart().clickMenu().selectResetAppState();
    }

    private static void verifyAllItemsAreDisplaying() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyDisplayOfAllItems();
    }

    private static void selectAllItems() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickAllItem();
    }

    private static void verifyHomePageLoadedSuccessfully() {
        HomePage homePage = new HomePage(webDriver);
        homePage.verifySuccessAboutPageNavigation();
    }

    private static void selectAbout() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickAbout();
    }


}
