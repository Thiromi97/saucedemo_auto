package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginTest extends TestBase {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    static WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));

    @Test
    public void verifyLoginWithValidCredentials(){
        logger.info("Starting test: verifyLoginWithValidCredentials");
        try {
            sendUserCredentials(properties.getProperty("stdUsername"));
            verifyProductPageLoadedSuccessfully();
            logger.info("Product page loaded successfully for valid credentials");
        } catch (Exception e) {
            logger.error("Unexpected error during valid login test", e);
        }
    }

    @Test
    public void verifyLoginWithInvalidUsername(){
        logger.info("Starting test: verifyLoginWithInvalidUsername");
        try {
            sendInvalidUsername();
            verifyErrorMessageForIncorrectCredentials();
            logger.info("Correct error message displayed for invalid username");
        } catch (Exception e) {
            logger.error("Unexpected error during invalid username verification", e);
        }
    }


    @Test
    public void verifyLoginWithInvalidPassword(){
        logger.info("Starting test: verifyLoginWithInvalidPassword");
        try {
            sendInvalidPassword();
            verifyErrorMessageForIncorrectCredentials();
            logger.info("Correct error message displayed for invalid password");
        } catch (Exception e) {
            logger.error("Unexpected error during invalid password verification", e);
        }
    }

    @Test
    public void verifyLoginWithInvalidCredentials(){
        logger.info("Starting test: verifyLoginWithInvalidCredentials");
        try {
            sendInvalidCredentials();
            verifyErrorMessageForIncorrectCredentials();
            logger.info("Correct error message displayed for invalid credentials");
        } catch (Exception e) {
            logger.error("Unexpected error during invalid credentials verification", e);
        }
    }

    @Test
    public void verifyLoginWithBlankUsername(){
        logger.info("Starting test: verifyLoginWithBlankUsername");
        try {
            sendBlankUsername();
            verifyErrorMessageForUsernameIsRequired();
            logger.info("Correct error message displayed for Blank username");
        } catch (Exception e) {
            logger.error("Unexpected error during blank username verification", e);
        }
    }


    @Test
    public void verifyLoginWithBlankPassword(){
        logger.info("Starting test: verifyLoginWithBlankPassword");
        try{
            sendBlankPassword();
            verifyErrorMessageForPasswordIsRequired();
            logger.info("Correct error message displayed for Blank password");
        } catch (Exception e) {
            logger.error("Unexpected error during blank password verification", e);
        }
    }


    @Test
    public void verifyLoginWithBlankCredentials(){
        logger.info("Starting test: verifyLoginWithBlankCredentials");
        try{
            sendBlankCredentials();
            verifyErrorMessageForUsernameIsRequired();
            logger.info("Correct error message displayed for Blank credentials");
        } catch (Exception e) {
            logger.error("Unexpected error during blank credentials verification", e);
        }
    }


    @Test
    public void verifyLoginWithLockedUserCredentials(){
        logger.info("Starting test: verifyLoginWithLockedUserCredentials");
        try{
            sendUserCredentials("locked_out_user");
            verifyErrorMessageForLockedOutUser();
            logger.info("Correct error message displayed for locked out user credentials");
        } catch (Exception e) {
            logger.error("Unexpected error during locked out user credentials verification", e);
        }

    }

    @Test
    public void verifyLoginWithProblemUserCredentials(){
        logger.info("Starting test: verifyLoginWithProblemUserCredentials");
        try{
            sendUserCredentials("problem_user");
            verifyProductPageLoadedSuccessfully();
            logger.info("Product page loaded successfully for problem user");
        } catch (Exception e) {
            logger.error("Unexpected error during problem user credentials verification", e);
        }
        try{
            verifyProductImagesAreUnique();
            logger.info("All product images are same for problem user");
        } catch (Exception e) {
            logger.error("Unexpected error during problem user credentials verification", e);
        }
        try{
            verifyAddToCartButtonsFunctionProperly();
            logger.info("Some of AddToCart buttons non functioning for problem user");
        } catch (Exception e) {
            logger.error("Unexpected error during problem user credentials verification", e);
        }
        try{
            verifyRemoveButtonsFunctionProperly();
            logger.info("Some of Remove buttons non functioning for problem user");
        } catch (Exception e) {
            logger.error("Unexpected error during problem user credentials verification", e);
        }
        try{
            verifyInCorrectProductDetailPageNavigation();
            logger.info("Some products direct  to incorrect product detail page for problem user");
        } catch (Exception e) {
            logger.error("Unexpected error during problem user credentials verification", e);
        }

    }


    @Test
    public void verifyLoginWithPerformanceGlitchUserCredentials(){
        logger.info("Starting test: verifyLoginWithPerformanceGlitchUserCredentials");
        try {
            sendUserCredentials(properties.getProperty("performanceGlitchUsername"));
            verifyProductPageLoadedSuccessfully();
            logger.info("Product page loaded successfully for performance glitch user");
        } catch (Exception e) {
            logger.error("Unexpected error during performance glitch user credentials verification", e);
        }

    }

    @Test
    public void verifyLoginWithVisualUserCredentials(){
        logger.info("Starting test: verifyLoginWithVisualUserCredentials");
        try {
            sendUserCredentials("visual_user");
            verifyProductPageLoadedSuccessfully();
            logger.info("Product page loaded successfully for visual user");
        } catch (Exception e) {
            logger.error("Unexpected error during visual user credentials verification", e);
        }
        try {
            verifyDisplayingInCorrectProductImage();
            logger.info("Display incorrect product images for visual user");
        } catch (Exception e) {
            logger.error("Unexpected error during visual user credentials verification", e);
        }

    }

    @Test
    public void verifyLoginWithErrorUserCredentials(){
        logger.info("Starting test: verifyLoginWithErrorUserCredentials");
        try {
            sendUserCredentials("error_user");
            verifyProductPageLoadedSuccessfully();
            logger.info("Product page loaded successfully for error user");
        } catch (Exception e) {
            logger.error("Unexpected error during error user credentials verification", e);
        }
        try {
            verifyAddToCartButtonsFunctionProperly();
            logger.info("Some AddToCart buttons not functioning for error user");
        } catch (Exception e) {
            logger.error("Unexpected error during error user credentials verification", e);
        }
        try {
            verifyRemoveButtonsFunctionProperly();
            logger.info("Some Remove buttons not functioning for error user");
        } catch (Exception e) {
            logger.error("Unexpected error during error user credentials verification", e);
        }
        try {
            verifyCompletionOfCheckoutSuccessfully();
            logger.info("Unsuccessful completion of checkout for error user");
        } catch (Exception e) {
            logger.error("Unexpected error during error user credentials verification", e);
        }

    }

    @Test
    public void verifyLogoutFunctionality(){
        logger.info("Starting test: verifyLogoutFunctionality");
        try {
            loginAsStandardUser();
            selectLogout();
            verifyLoginPageLoadedSuccessfully();
            logger.info("Logout page loaded successfully");
        } catch (Exception e) {
            logger.error("Unexpected error during logout functionality verification", e);
        }

    }

    @Test
    public void verifyResetAppStateFunctionality(){
        logger.info("Starting test: verifyResetAppStateFunctionality");
        try {
            loginAsStandardUser();
            selectResetAppState();
            verifyCartIsReset();
            logger.info("Cart is reset successfully");
        } catch (Exception e) {
            logger.error("Unexpected error during resetAppState functionality verification", e);
        }
        try {
            verifyAllRemoveButtonResetToAddToCartButtons();
            logger.info("Remove buttons reset to AddToCart buttons successfully");
        } catch (Exception e) {
            logger.error("Unexpected error during resetAppState functionality verification", e);
        }

    }

    @Test
    public void verifyAboutFunctionality(){
        logger.info("Starting test: verifyAboutFunctionality");
        try {
            loginAsStandardUser();
            selectAbout();
            verifyHomePageLoadedSuccessfully();
            logger.info("Home Page Loaded successfully");
        } catch (Exception e) {
            logger.error("Unexpected error during about functionality verification", e);
        }

    }

    @Test
    public void verifyAllItemFunctionality(){
        logger.info("Starting test: verifyAllItemFunctionality");
        try {
            loginAsStandardUser();
            selectAllItems();
            verifyAllItemsAreDisplaying();
            logger.info("All Items are displayed successfully");
        } catch (Exception e) {
            logger.error("Unexpected error during allItem functionality verification", e);
        }

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
        String actualErrorMsg = txtErrorMsg.getText();
        String expectedErrorMsg = "Username and password do not match";
        logger.info(actualErrorMsg);
        logger.info(expectedErrorMsg);
        assertThat(actualErrorMsg).contains(expectedErrorMsg);
    }

    private static void sendInvalidCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterInvalidUsername("standard_user").enterInvalidPassword("secret_sauce").clickLogin();
    }

    private static void verifyErrorMessageForUsernameIsRequired() {
        LoginPage loginPage = new LoginPage(webDriver);
        WebElement txtErrorMsg = loginPage.getErrorMsg();
        String actualErrorMsg = txtErrorMsg.getText();
        String expectedErrorMsg = "Username is required";
        logger.info(actualErrorMsg);
        logger.info(expectedErrorMsg);
        assertThat(actualErrorMsg).contains(expectedErrorMsg);
    }

    private static void verifyErrorMessageForLockedOutUser() {
        LoginPage loginPage = new LoginPage(webDriver);
        WebElement txtErrorMsg = loginPage.getErrorMsg();
        String actualErrorMsg = txtErrorMsg.getText();
        String expectedErrorMsg = properties.getProperty("errorMsgLockedOutUser");
        logger.info(actualErrorMsg);
        logger.info(expectedErrorMsg);
        assertThat(actualErrorMsg).contains(expectedErrorMsg);
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
        String actualErrorMsg = txtErrorMsg.getText();
        String expectedErrorMsg = "Password is required";
        logger.info(actualErrorMsg);
        logger.info(expectedErrorMsg);
        assertThat(actualErrorMsg).contains(expectedErrorMsg);
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
            logger.info(originalTitle);
            logger.info(detailPageTitle);
            assertThat(detailPageTitle).isEqualTo(originalTitle);
            webDriver.navigate().back();
        }
    }

    private static void verifyProductImagesAreUnique() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List <WebElement> productImages = productsPage.getProductImagesList();
        String firstImageSrc = productImages.getFirst().getAttribute("src");
        logger.info(firstImageSrc);
        for(WebElement productImage:productImages){
            String imageSrc = productImage.getAttribute("src");
            logger.info(imageSrc);
            assertThat(imageSrc).isEqualTo(firstImageSrc);
        }
    }

    private static void verifyDisplayingInCorrectProductImage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> productImages = productsPage.getProductImagesList();
        for (int i = 0; i < productImages.size(); i++) {
            logger.info("Locate next image");
            productImages = webDriver.findElements(By.cssSelector(".inventory_item img"));
            WebElement productImage = productImages.get(i);
            String productImageSrc = productImage.getAttribute("src");
            logger.info(productImageSrc);
            productImage.click();
            ProductDetailPage productDetailPage =new ProductDetailPage(webDriver);
            String productDetailImgSrc = productDetailPage.getProductImageSrc();
            logger.info(productDetailImgSrc);
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
        String expectTitle = "Checkout: Complete!";
        logger.info(completePageTitle);
        logger.info(expectTitle);
        assertThat(completePageTitle).isEqualTo(expectTitle);
    }

    private static void verifyRemoveButtonsFunctionProperly() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List <WebElement> buttonList = productsPage.getRemoveButtonsList();
        boolean buttonsFunctioning = true;
        for (int i = 0; i < buttonList.size(); i++) {
            WebElement button = buttonList.get(i);
            String txtBeforeClickBtn = button.getText();
            button.click();
            List<WebElement> refreshedButtonList = productsPage.getRemoveButtonsList();
            WebElement refreshedButton = refreshedButtonList.get(i);
            String actualBtnText = refreshedButton.getText();
            logger.info("Before Clicking - {}", txtBeforeClickBtn);
            logger.info("After clicking - {}", actualBtnText);
            if (actualBtnText.equals(txtBeforeClickBtn)) {
                buttonsFunctioning = false;
                break;
            }
        }
        assertThat(buttonsFunctioning).isFalse();
    }

    private static void verifyAddToCartButtonsFunctionProperly() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List <WebElement> buttonList = productsPage.getAddToCartButtonsList();
        boolean buttonsFunctioning = true;
        for (int i = 0; i < buttonList.size(); i++) {
                WebElement button = buttonList.get(i);
                String txtBeforeClickBtn = button.getText();
                button.click();
                List<WebElement> refreshedButtonList = productsPage.getAddToCartButtonsList();
                WebElement refreshedButton = refreshedButtonList.get(i);
                String actualBtnText = refreshedButton.getText();
                logger.info("Button Text Before Clicking - {}", txtBeforeClickBtn);
                logger.info("Actual after clicking - {}", actualBtnText);

                if (actualBtnText.equals(txtBeforeClickBtn)) {
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
        logger.info(loginPageTitle);
        String expectedTitle = properties.getProperty("loginPageTitle");
        logger.info(expectedTitle);
        assertThat(loginPageTitle).isEqualTo(expectedTitle);
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
            logger.info(buttonName);
            String expectBtnName = properties.getProperty("addToCartBtnName");
            logger.info(expectBtnName);
            assertThat(buttonName).isEqualTo(expectBtnName);
        }
    }

    private static void verifyCartIsReset() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        Integer cartItemCount = productsPage.getCartItemCount();
        logger.info(cartItemCount);
        logger.info(0);
        assertThat(cartItemCount).isEqualTo(0);
    }

    private static void selectResetAppState() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.addProductToCart().clickMenu().selectResetAppState();
    }

    private static void verifyAllItemsAreDisplaying() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> productNames = productsPage.getProductNamesList();
        Integer noOfProducts = productNames.size();
        logger.info(noOfProducts);
        logger.info(6);
        assertThat(noOfProducts).isEqualTo(6);
    }

    private static void selectAllItems() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickAllItem();
    }

    private static void verifyHomePageLoadedSuccessfully() {
        HomePage homePage = new HomePage(webDriver);
        WebElement txtAbout = homePage.getAbout();
        wait.until(ExpectedConditions.visibilityOf(txtAbout));
        String actualAbout = txtAbout.getText();
        logger.info(actualAbout);
        String expectAbout = "The world relies on your code";
        logger.info(expectAbout);
        assertThat(actualAbout).contains(expectAbout);
    }

    private static void selectAbout() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickMenu().clickAbout();
    }


}
