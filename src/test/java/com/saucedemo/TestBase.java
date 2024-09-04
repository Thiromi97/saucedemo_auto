package com.saucedemo;
import com.saucedemo.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {
    public static WebDriver webDriver;

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
        cartPage.verifyCartItemsDeductByOne();
    }

    public void verifyCartPageLoadedSuccessfully() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.verifyNavigationToCartPage();
    }

    public void verifyProductPageLoadedSuccessfully() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifySuccessNavigationToProductPage();
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
