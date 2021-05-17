package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of dashboard page
 */
public class CorporateSportPage extends BasePage<CorporateSportPage> {

    @Step("Validate corporate sports page")
    public boolean validateCorporateSportPage() {
        getWait().until(urlContains("corporate"));
        return true;
    }
}
