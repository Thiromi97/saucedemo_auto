package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginTests extends TestBase {

    static WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));

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
        verifyInCorrectProductDetailPageNavigation();
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
        verifyDisplayingInCorrectProductImage();
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
        WebElement txtErrorMsg = loginPage.getErrorMsg();
        assertThat(txtErrorMsg.getText()).contains("Username and password do not match");
    }

    private static void sendInvalidCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterInvalidUsername("standard_user").enterInvalidPassword("secret_sauce").clickLogin();
    }

    private static void verifyErrorMessageForUsernameIsRequired() {
        LoginPage loginPage = new LoginPage(webDriver);
        WebElement txtErrorMsg = loginPage.getErrorMsg();
        assertThat(txtErrorMsg.getText()).contains("Username is required");
    }

    private static void verifyErrorMessageForLockedOutUser() {
        LoginPage loginPage = new LoginPage(webDriver);
        WebElement txtErrorMsg = loginPage.getErrorMsg();
        assertThat(txtErrorMsg.getText()).contains("Sorry, this user has been locked out");
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
        WebElement txtErrorMsg = loginPage.getErrorMsg();
        assertThat(txtErrorMsg.getText()).contains("Password is required");
    }

    private static void sendBlankCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clearUsername().clearPassword().clickLogin();
    }

    private static void verifyInCorrectProductDetailPageNavigation() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> productNames = productsPage.getProductNamesList();
        for (int i = 0; i < productNames.size(); i++) {
            String originalTitle = getElementText(productNames, i);
            ProductDetailPage productDetailPage =new ProductDetailPage(webDriver);
            String detailPageTitle = productDetailPage.getProductName();
            assertThat(detailPageTitle).isEqualTo(originalTitle);
            webDriver.navigate().back();
        }
    }

    private static void verifyProductImagesAreUnique() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List <WebElement> productImages = productsPage.getProductImagesList();
        String firstImageSrc = productImages.getFirst().getAttribute("src");
        for(WebElement productImage:productImages){
            String imageSrc = productImage.getAttribute("src");
            assertThat(imageSrc).isEqualTo(firstImageSrc);
        }
    }

    private static void verifyDisplayingInCorrectProductImage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> productImages = productsPage.getProductImagesList();
        for (int i = 0; i < productImages.size(); i++) {
            //relocate image
            productImages = webDriver.findElements(By.cssSelector(".inventory_item img"));
            WebElement productImage = productImages.get(i);
            String productImageSrc = productImage.getAttribute("src");
            productImage.click();
            ProductDetailPage productDetailPage =new ProductDetailPage(webDriver);
            String productDetailImgSrc = productDetailPage.getProductImageSrc();
            assertThat(productImageSrc).isEqualTo(productDetailImgSrc);
            webDriver.navigate().back();
        }
    }

    private static void verifyCompletionOfCheckoutSuccessfully() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        WebElement btnShoppingCart = productsPage.getShoppingCartButton();
        btnShoppingCart.click();
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickCheckOut();
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.fillFirstName().fillLastName().fillPostalCode().clickContinue();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);
        checkoutOverviewPage.clickFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage(webDriver);
        String completePageTitle = completePage.getPageTitle();
        assertThat(completePageTitle).isEqualTo("Checkout: Complete!");
    }

    private static void verifyRemoveButtonsFunctionProperly() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List <WebElement> removeButtons = productsPage.getRemoveButtonsList();
        verifyButtonFunctionality(removeButtons);
    }

    private static void verifyAddToCartButtonsFunctionProperly() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List <WebElement> addToCartButtons = productsPage.getAddToCartButtonsList();
        verifyButtonFunctionality(addToCartButtons);
    }

    private static void verifyButtonFunctionality(List<WebElement> buttonList) {
        boolean buttonsFunctioning = true;
        for (int i = 0; i < buttonList.size(); i++) {
            String btnInitialText = getElementText(buttonList, i);
            WebElement button;
            // Re-find the button to ensure it hasn't changed in the DOM(it take the list again and fetch the correct btn)
            button = buttonList.get(i);
            if (button.getText().equals(btnInitialText)) {
                buttonsFunctioning = false;
                break;
            }
        }
        assertThat(buttonsFunctioning).isFalse();
    }
    private static String getElementText(List<WebElement> buttonList, int i) {
        WebElement button = buttonList.get(i);
        String btnInitialText = button.getText();
        button.click();
        return btnInitialText;
    }

    private static void verifyLoginPageLoadedSuccessfully() {
        LoginPage loginPage = new LoginPage(webDriver);
        WebElement loginTitle = loginPage.getLoginTitle();
        String loginPageTitle = loginTitle.getText();
        assertThat(loginPageTitle).isEqualTo("Swag Labs");
    }

    private static void selectLogout() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickLogout();
    }

    private static void verifyAllRemoveButtonResetToAddToCartButtons() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> removeButtons = productsPage.getRemoveButtonsList();
        for(WebElement button:removeButtons){
            String buttonName = button.getText();
            assertThat(buttonName).isEqualTo("Add to cart");
        }
    }

    private static void verifyCartIsReset() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        Integer cartItemCount = productsPage.getCartItemCount();
        assertThat(cartItemCount).isEqualTo(0);
    }

    private static void selectResetAppState() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.addProductToCart().clickMenu().selectResetAppState();
    }

    private static void verifyAllItemsAreDisplaying() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> productNames = productsPage.getProductNamesList();
        assertThat(productNames.size()).isEqualTo(6);
    }

    private static void selectAllItems() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickAllItem();
    }

    private static void verifyHomePageLoadedSuccessfully() {
        HomePage homePage = new HomePage(webDriver);
        WebElement txtAbout = homePage.getAbout();
        wait.until(ExpectedConditions.visibilityOf(txtAbout));
        assertThat(txtAbout.getText()).contains("The world relies on your code");
    }

    private static void selectAbout() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickAbout();
    }


}
