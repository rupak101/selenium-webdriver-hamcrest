package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of home page of urban sports
 */
public class HomePage extends BasePage<HomePage> {

    private final By acceptButton = By.xpath("//*[@id='onetrust-accept-btn-handler']");
    private final By companyLogo = By.xpath("//*[@class='smm-header__brand-link']");
    private final By header = By.xpath("//*[@class='smm-header__menu left']//*[@class='smm-header__menu-item']");
    private final By fitnessFamilySports = By.xpath("//*[@class='smm-category-group-icon normal fitness-n-family-sports']");
    private final By howItWorks = By.xpath("//*[@class='usc-simple-section__heading usc-simple-section--center']");
    private final By pricesTitle = By.cssSelector("h2.title");
    private final By exploreLiveClasses = By.xpath("//*[@class='form-button online-courses-btn']");
    private final By loginButton = By.xpath("//*[@class='smm-header__menu-item login']");
    private final By languageButton = By.xpath("//*[@class='smm-header__menu-item language-switch']");
    private final By selectGermanLanguage = By.xpath("//*[@class='smm-header__menu-item language-switch' ]//*[@data-locale='de']");

    @Step("Click on accept")
    public HomePage clickAccept() {
        //Click on accept cookies
        click(acceptButton);
        waitForVisibilityOf(companyLogo);
        return this;
    }

    @Step("Click on sports offer tab")
    public HomePage clickSportsOffer() {
        //Click on sports offer tab
        findElements(header).get(0).click();
        return this;
    }

    @Step("Click on how it work tab")
    public HomePage clickHowToWork() {
        //Click on how to work tab
        findElements(header).get(1).click();
        return this;
    }

    @Step("Click on prices tab")
    public HomePage clickPrices() {
        //Click on prices tab
        findElements(header).get(2).click();
        return this;
    }

    @Step("Click on corporates sports tab")
    public CorporateSportPage clickCorporatesSports() {
        //Click on corporates sports tab
        findElements(header).get(3).click();
        switchTab();
        return new CorporateSportPage();
    }

    @Step("Click on partners tab")
    public PartnersPage clickPartners() {
        //Click on partners tab
        findElements(header).get(4).click();
        switchTab();
        return new PartnersPage();
    }

    @Step("Click on online classes tab")
    public LiveClassesPage clickOnLiveClasses() {
        //Click on online classes tab
        findElements(header).get(5).click();
        switchTab();
        return new LiveClassesPage();
    }

    @Step("Click on explore live classes")
    public HomePage clickExploreLiveClass() {
        click(exploreLiveClasses);
        getWait().until(urlContains("online-courses"));
        return this;
    }

    @Step("Click on login button from top right corner of the page")
    public LoginPage clickLoginButton() {
        //Click on login button
        click(loginButton);
        return new LoginPage();
    }

    @Step("Click on language drop down and select German language")
    public HomePage selectGermanLanguage() {
        //Click on language button
        click(languageButton);
        //Select german language
        click(selectGermanLanguage);
        getWait().until(urlContains("de"));
        return this;
    }

    @Step("Validate the fitness family sports options")
    public boolean isFitnessFamilySportsDisplayed() {
        return findElement(fitnessFamilySports).isDisplayed();
    }

    @Step("Validate the how it works")
    public boolean isHowItWorksDisplayed() {
        return findElement(howItWorks).isDisplayed();
    }

    @Step("Validate the price")
    public boolean isPriceTitleDisplayed() {
        return findElement(pricesTitle).isDisplayed();
    }

    public String getLanguageInfo() {
        return findElement(languageButton).getText();
    }
}
