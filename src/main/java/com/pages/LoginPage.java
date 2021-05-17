package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of login page
 */
public class LoginPage extends BasePage<LoginPage> {

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//*[@id=\"login-group\"]/input");
    private final By passwordError = By.xpath("//*[@id='password-group']/div");
    private final By emailError = By.xpath("//*[@id='email-group']/div");

    @Step("Enter email")
    public LoginPage enterEmail(String email) {
        clearAndFill(emailInput, email);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        clearAndFill(passwordInput, password);
        return this;
    }

    @Step("Click login button")
    public LoginPage clickLoginButton() {
        click(loginButton);
        return this;
    }

    @Step("Verify the error message for empty email")
    public boolean isEmailErrorDisplayed() {
        return findElement(emailError).isDisplayed();
    }

    @Step("Verify the error message for empty password ")
    public boolean isPasswordErrorDisplayed() {
        return findElement(passwordError).isDisplayed();
    }
}