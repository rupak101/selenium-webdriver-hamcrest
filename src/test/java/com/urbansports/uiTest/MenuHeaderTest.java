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

    @Test
    @Description("Open sports offer tab")
    public void openAndVerifySportsOfferTab() {
        HomePage homePage = openUrlAndAcceptCookies()
                .clickSportsOffer();

        assertThat("Sports offer title didn't display", getDriver().getTitle(), is(pageTitle));
        assertThat("Sports offers is not displayed", homePage.isFitnessFamilySportsDisplayed(), is(true));
    }

    @Test
    @Description("Open and verify how it works tab")
    public void openAndVerifyHowItWorksTab() {
        HomePage homePage = openUrlAndAcceptCookies()
                .clickHowToWork();

        assertThat("How to open tab title didn't display", getDriver().getTitle(), is(pageTitle));
        assertThat("How to open tab is not displayed", homePage.isHowItWorksDisplayed(), is(true));
    }

    @Test
    @Description("Open and verify prices tab")
    public void openAndVerifyPricesTab() {
        HomePage homePage = openUrlAndAcceptCookies()
                .clickPrices();

        assertThat("Price tab title didn't display", getDriver().getTitle(), is(pageTitle));
        assertThat("Price is not displayed", homePage.isPriceTitleDisplayed(), is(true));
    }

    @Test
    @Description("Open and verify corporates sports tab")
    public void openAndVerifyCorporateSportsTab() {
        CorporateSportPage corporateSportPage = openUrlAndAcceptCookies()
                .clickCorporatesSports();

        assertThat("Corporates sports page title didn't display", getDriver().getTitle(), is("Company Wellness made easy and flexible by Urban Sports Club"));
        assertThat("Corporates sports page is not displayed", corporateSportPage.validateCorporateSportPage(), is(true));
    }

    @Test
    @Description("Open and verify Partners tab")
    public void openAndVerifyPartnersTab() {
        PartnersPage partnersPage = openUrlAndAcceptCookies()
                .clickPartners();

        assertThat("Partners page title didn't display", getDriver().getTitle(), is("Urban Sports Club | Become a partner"));
        assertThat("Partners page is not displayed", partnersPage.validatePartnersPage(), is(true));
    }

    @Test
    @Description("Open and verify live classes tab")
    public void openAndVerifyLiveClassesTab() {
        LiveClassesPage liveClassesPage = openUrlAndAcceptCookies()
                .clickOnLiveClasses();

        assertThat("Live classes page title didn't display", getDriver().getTitle(), is("Live Classes | Do Sports at your home | Urban Sports Club"));
        assertThat("Live classes page is not displayed", liveClassesPage.validateLiveClassesPage(), is(true));
    }

    @Test
    @Description("Explore live classes")
    public void exploreLiveClasses() {
        openUrlAndAcceptCookies()
                .clickExploreLiveClass();

        assertThat("Explore live classes title didn't display", getDriver().getTitle(), is("Live Classes | Do Sports at your home | Urban Sports Club"));
    }

    @Test
    @Description("Select the language as German")
    public void selectLanguageFromDropDown() {
        HomePage homePage = openUrlAndAcceptCookies()
                .selectGermanLanguage();

        assertThat("Language Url didn't change", getDriver().getCurrentUrl(), containsString("de"));
        assertThat("Language didn't change", homePage.getLanguageInfo(), is("DE"));
    }
}
