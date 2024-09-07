package com.saucedemo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    final WebDriver webDriver;

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
        return this;
    }

    public LoginPage enterPassword(String password) {
       txtPassword.sendKeys(password);
       return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public LoginPage enterInvalidUsername(String username) {
        txtUsername.sendKeys(username);
        Actions actions = new Actions(webDriver);
        actions.click(txtUsername)
                .sendKeys(Keys.ARROW_LEFT)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        return this;
    }

    public LoginPage enterInvalidPassword(String password) {
        txtPassword.sendKeys(password);
        Actions actions = new Actions(webDriver);
        actions.click(txtPassword)
                .sendKeys(Keys.ARROW_LEFT)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        return this;
    }

    public LoginPage clearUsername() {
        txtUsername.clear();
        return this;
    }

    public LoginPage clearPassword() {
        txtPassword.clear();
        return this;
    }


    public WebElement getErrorMsg() {
        return txtError;
    }

    public WebElement getLoginTitle() {
        return loginLogo;
    }
}

