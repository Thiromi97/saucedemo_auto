package com.saucedemo;
import com.saucedemo.pages.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBase {
    public static WebDriver webDriver;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); ;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser, Method method) {
        webDriver = BrowserFactory.getBrowser(browser);
        System.out.println("browser =" + browser);
        webDriver.get("https://saucedemo.com");
        webDriver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public void loginAsStandardUser(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterUsername("standard_user").enterPassword("secret_sauce").clickLogin();
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
        AssertionsForClassTypes.assertThat(cartItemCount).isEqualTo(5);
    }

    public static void verifyCartPageLoadedSuccessfully() {
        CartPage cartPage = new CartPage(webDriver);
        WebElement cartPageTitle = cartPage.getPageTitle();
        assertThat(cartPageTitle.getText()).isEqualTo("Your Cart");
    }

    public void verifyProductPageLoadedSuccessfully() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        WebElement productTitle = wait.until(ExpectedConditions.visibilityOf(productsPage.getPageTitle()));
        assertThat(productTitle.getText()).isEqualTo("Products");
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
