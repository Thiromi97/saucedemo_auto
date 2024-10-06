package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    final WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(css = "p.css-sere2z")
    WebElement txtAbout;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver,this);
    }


    public WebElement getAbout() {
        return txtAbout;
    }
}
