package com.pages;

import io.qameta.allure.Step;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of dashboard page
 */
public class LiveClassesPage extends BasePage<LiveClassesPage> {

    @Step("Validate live classes page")
    public boolean validateLiveClassesPage() {
        getWait().until(urlContains("online-classes"));
        return true;
    }
}
