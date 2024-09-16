package com.saucedemo.support.ui;

import com.saucedemo.support.ui.ICheck;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WrapsElement;

import java.time.Duration;

public class Check implements ICheck, WrapsElement {
    private final WebElement element;
    private final WebDriver driver;
    private final WebDriverWait wait;



    public Check(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        if (!"input".equals(element.getTagName()) || !"checkbox".equals(element.getAttribute("type"))) {
            throw new IllegalArgumentException("The provided element is not a checkbox.");
        }
    }

    @Override
    public void check() {
        waitUntilClickable();
        if (!isChecked()) {
            element.click();
        }
    }

    @Override
    public void uncheck() {
        waitUntilClickable();
        if (isChecked()) {
            element.click();
        }
    }

    @Override
    public void toggle() {
        waitUntilClickable();
        element.click();
    }

    @Override
    public boolean isChecked() {
        return element.isSelected();
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }


    private void waitUntilClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}