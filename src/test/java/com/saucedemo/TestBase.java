package com.saucedemo;

import com.saucedemo.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBase {
    public static WebDriver webDriver;
    public Logger logger;
    public static Properties properties;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public static void takeScreenShotFullPage(String testName) {
        TakesScreenshot shot = (TakesScreenshot) webDriver;
        File sourceFile = shot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(System.getProperty("user.dir") + "\\screenshots\\" + testName + ".png");
        sourceFile.renameTo(targetFile);
    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
        logger = LogManager.getLogger(TestBase.class);
        FileReader file = new FileReader(".//src/main/resources/config.properties");
        properties = new Properties();
        properties.load(file);
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser, Method method) {
        webDriver = BrowserFactory.getBrowser(browser);
        System.out.println("browser =" + browser);
        webDriver.get(properties.getProperty("baseUrl"));
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterUsername(properties.getProperty("stdUsername")).enterPassword(properties.getProperty("password")).clickLogin();
    }

    public void addProductsToCart() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.addProductToCart().clickCart();
    }

    public void clickCheckoutButtonOfCartPage() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickCheckOut();
    }

    public void goToCartPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickCart();
    }

    public void verifyProductIsRemovedFromTheCart() {
        CartPage cartPage = new CartPage(webDriver);
        Integer cartItemCount = cartPage.getCartItemSize();
        logger.info(cartItemCount);
        logger.info(5);
        AssertionsForClassTypes.assertThat(cartItemCount).isEqualTo(5);
    }

    public void verifyCartPageLoadedSuccessfully() {
        CartPage cartPage = new CartPage(webDriver);
        WebElement cartPageTitle = cartPage.getPageTitle();
        String actualTitle = cartPageTitle.getText();
        String expectTitle = properties.getProperty("cartPageTitle");
        logger.info(actualTitle);
        logger.info(expectTitle);
        assertThat(actualTitle).isEqualTo(expectTitle);
    }

    public void verifyProductPageLoadedSuccessfully() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        WebElement productTitle = wait.until(ExpectedConditions.visibilityOf(productsPage.getPageTitle()));
        String actualTitle = productTitle.getText();
        String expectTitle = properties.getProperty("productPageTitle");
        logger.info(actualTitle);
        logger.info(expectTitle);
        assertThat(actualTitle).isEqualTo(expectTitle);
    }

    public void fillAllCheckoutFieldsOfCheckoutDetailPage() {
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.fillFirstName().fillLastName().fillPostalCode().clickContinue();
    }

    public void clickOnFinishButtonOfOverviewPage() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        overviewPage.clickFinish();
    }

}
