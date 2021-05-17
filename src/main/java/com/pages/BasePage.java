package com.pages;

import com.framework.libraries.FileReaderLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.framework.driverconfiguration.DriverCreator.getDriver;

/**
 * @author Rupak Mansingh
 * Base class have all common methods for the project
 */
public class BasePage<T> {

    @Step("Find element: {locator}")
    protected static WebElement findElement(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisibilityOf(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Find elements: {locator}")
    public static List<WebElement> findElements(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    @Step("Clear and fill : {locator}")
    public static void clearAndFill(By locator, String text) {
        WebElement element = findClickableElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    @Step("Click on locator  : {locator}")
    public static void click(By locator) {
        findClickableElement(locator).click();
    }

    private static WebElement findClickableElement(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), 10, 30);
    }

    @Step("Open base URL")
    public T openURL() {
        String url = FileReaderLibrary.getInstance().getConfigReader().getBaseUrl("baseurl");
        load(url);
        return (T) this;
    }
    @Step("Switch the tab")
    public static void switchTab(){
    ArrayList<String> tabs2 = new ArrayList<String> (getDriver().getWindowHandles());
    getDriver().switchTo().window(tabs2.get(1));
    }

    public static void load(String url) {
        getDriver().get(url);
    }

    @Step("Open the url and accept the cookies")
    public  static HomePage openUrlAndAcceptCookies() {
        return new HomePage()
                .openURL()
                .clickAccept();
    }
}