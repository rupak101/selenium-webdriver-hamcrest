package com.urbansports.uiTest;

import com.base.BaseTest;
import com.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.framework.driverconfiguration.DriverCreator.getDriver;
import static com.pages.BasePage.openUrlAndAcceptCookies;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: Rupak Mansingh
 * @Desc: Test cases for User login for Urban sports
 */
@Owner("Rupak Mansingh")
@Feature("User login feature for Urban sports")
public class LoginTest extends BaseTest {

    private static final String pageTitle = "Urban Sports Club | Sports where and whenever you want";

    @DataProvider(parallel = true)
    public Object[] userEmailData() {
        return new Object[]{
                "abc@urbansports.com",
                "abc@urbansports"
        };
    }

    @Test(dataProvider = "userEmailData")
    @Description("Login with the user with email address without password")
    public void loginWithoutPassword(String email) {
        LoginPage loginPage = openUrlAndAcceptCookies()
                .clickLoginButton()
                .enterEmail(email)
                .clickLoginButton();

        assertThat("Login page title didn't display", getDriver().getTitle(), is(pageTitle));
        assertThat("Error message for empty password didn't displayed", loginPage.isPasswordErrorDisplayed(), is(true));
    }

    @Test
    @Description("Login with the user with password and without email address ")
    public void loginWithoutEmail() {
        LoginPage loginPage = openUrlAndAcceptCookies()
                .clickLoginButton()
                .enterPassword("abc@12345")
                .clickLoginButton();

        assertThat("Login page title didn't display", getDriver().getTitle(), is(pageTitle));
        assertThat("Error message for empty email didn't displayed", loginPage.isEmailErrorDisplayed(), is(true));
    }
}
