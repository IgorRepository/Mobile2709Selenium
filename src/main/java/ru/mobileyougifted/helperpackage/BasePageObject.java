package ru.mobileyougifted.helperpackage;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePageObject {

    public static WebDriverWait wait;
    protected AppiumDriver driver;

    public BasePageObject(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 12);
    }

    public void typeIntoField(By pathToElement, String text) {
        find(pathToElement).sendKeys(text);
    }

    public WebElement find(By pathToElement) {
        return driver.findElement(pathToElement);
    }

//    public WebElement finds(By pathToElement,Integer index) {
//        return (WebElement) driver.findElements(pathToElement).get(index);
//    }

    protected void click(By pathToElementClick) {
        find(pathToElementClick).click();
    }

    protected void waitingVisibilityOfElement(By pathToElement, Integer... timeSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitForElement(ExpectedConditions.visibilityOfElementLocated(pathToElement)
                        , (timeSeconds.length > 0 ? timeSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }

        }
    }

    private void waitForElement(ExpectedCondition<WebElement> condition, Integer timeInSeconds) {
        timeInSeconds = timeInSeconds != null ? timeInSeconds : 10;
        WebDriverWait wait = new WebDriverWait(driver,timeInSeconds );
        wait.until(condition);
    }



}