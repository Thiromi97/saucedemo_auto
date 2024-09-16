package com.saucedemo.pages;

import com.saucedemo.tests.LoginTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    final WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    @FindBy(id = "user-name")
    WebElement txtUsername;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(id="login-button")
    WebElement btnLogin;

    @FindBy(tagName = "h3")
    WebElement txtError;

    @FindBy(className = "login_logo")
    WebElement loginLogo;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public LoginPage enterUsername(String username) {
        txtUsername.sendKeys(username);
        logger.info("Enter username");
        return this;
    }

    public LoginPage enterPassword(String password) {
       txtPassword.sendKeys(password);
        logger.info("Enter password");
       return this;
    }

    public void clickLogin() {
        btnLogin.click();
        logger.info("Click Login");
    }

    public LoginPage enterInvalidUsername(String username) {
        txtUsername.sendKeys(username);
        Actions actions = new Actions(webDriver);
        actions.click(txtUsername)
                .sendKeys(Keys.ARROW_LEFT)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        logger.info("Enter invalid username");
        return this;
    }

    public LoginPage enterInvalidPassword(String password) {
        txtPassword.sendKeys(password);
        Actions actions = new Actions(webDriver);
        actions.click(txtPassword)
                .sendKeys(Keys.ARROW_LEFT)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        logger.info("Enter invalid password");
        return this;
    }

    public LoginPage clearUsername() {
        txtUsername.clear();
        logger.info("Clear username");
        return this;
    }

    public LoginPage clearPassword() {
        txtPassword.clear();
        logger.info("Clear password");
        return this;
    }


    public WebElement getErrorMsg() {
        logger.info("Get the error message");
        return txtError;
    }

    public WebElement getLoginTitle() {
        logger.info("Get Login Page title");
        return loginLogo;
    }
}

