package com.urbansports.uiTest;

import com.base.BaseTest;
import com.pages.CorporateSportPage;
import com.pages.HomePage;
import com.pages.LiveClassesPage;
import com.pages.PartnersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.framework.driverconfiguration.DriverCreator.getDriver;
import static com.pages.BasePage.openUrlAndAcceptCookies;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: Rupak Mansingh
 * @Desc: Test cases for Urban sports home page
 */
@Owner("Rupak Mansingh")
@Feature("Test cases for Urban sports home page")
public class MenuHeaderTest extends BaseTest {

    private static final String pageTitle = "Urban Sports Club | Sports where and whenever you want";
    private static final String pageTitleForLiveClasses = "Live Classes | Do Sports at your home | Urban Sports Club";

    @Test
    @Description("Open sports offer tab")
    public void openAndVerifySportsOfferTab() {
        HomePage homePage = openUrlAndAcceptCookies()
                .clickSportsOffer();

        verifyPageTitle("Sports offer title didn't display", pageTitle);
        verifyPage("Sports offers is not displayed", homePage.isFitnessFamilySportsDisplayed());
    }

    @Test
    @Description("Open and verify how it works tab")
    public void openAndVerifyHowItWorksTab() {
        HomePage homePage = openUrlAndAcceptCookies()
                .clickHowToWork();

        verifyPageTitle("How to open tab title didn't display", pageTitle);
        verifyPage("How to open tab is not displayed", homePage.isHowItWorksDisplayed());
    }

    @Test
    @Description("Open and verify prices tab")
    public void openAndVerifyPricesTab() {
        HomePage homePage = openUrlAndAcceptCookies()
                .clickPrices();

        verifyPageTitle("Price tab title didn't display", pageTitle);
        verifyPage("Price is not displayed", homePage.isPriceTitleDisplayed());
    }

    @Test
    @Description("Open and verify corporates sports tab")
    public void openAndVerifyCorporateSportsTab() {
        String title = "Company Wellness made easy and flexible by Urban Sports Club";
        CorporateSportPage corporateSportPage = openUrlAndAcceptCookies()
                .clickCorporatesSports();

        verifyPageTitle("Corporates sports page title didn't display", title);
        verifyPage("Corporates sports page is not displayed", corporateSportPage.validateCorporateSportPage());
    }

    @Test
    @Description("Open and verify Partners tab")
    public void openAndVerifyPartnersTab() {
        PartnersPage partnersPage = openUrlAndAcceptCookies()
                .clickPartners();

        verifyPageTitle("Partners page title didn't display", "Urban Sports Club | Become a partner");
        verifyPage("Partners page is not displayed", partnersPage.validatePartnersPage());
    }

    @Test
    @Description("Open and verify live classes tab")
    public void openAndVerifyLiveClassesTab() {
        LiveClassesPage liveClassesPage = openUrlAndAcceptCookies()
                .clickOnLiveClasses();

        verifyPageTitle("Live classes page title didn't display", pageTitleForLiveClasses);
        verifyPage("Live classes page is not displayed", liveClassesPage.validateLiveClassesPage());
    }

    @Test
    @Description("Explore live classes")
    public void exploreLiveClasses() {
        openUrlAndAcceptCookies()
                .clickExploreLiveClass();

        verifyPageTitle("Explore live classes title didn't display", pageTitleForLiveClasses);
    }

    @Test
    @Description("Select the language as German")
    public void selectLanguageFromDropDown() {
        HomePage homePage = openUrlAndAcceptCookies()
                .selectGermanLanguage();

        assertThat("Language Url didn't change", getDriver().getCurrentUrl(), containsString("de"));
        assertThat("Language didn't change", homePage.getLanguageInfo(), is("DE"));
    }

    private void verifyPageTitle(String errorMessage, String pageTitle) {
        assertThat(errorMessage, getDriver().getTitle(), is(pageTitle));
    }

    private void verifyPage(String errorMessage, boolean fitnessFamilySportsDisplayed) {
        assertThat(errorMessage, fitnessFamilySportsDisplayed, is(true));
    }
}
