package com.pages;

import io.qameta.allure.Step;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of dashboard page
 */
public class PartnersPage extends BasePage<PartnersPage> {

    @Step("Validate partners page")
    public boolean validatePartnersPage() {
        getWait().until(urlContains("partners"));
        return true;
    }
}
