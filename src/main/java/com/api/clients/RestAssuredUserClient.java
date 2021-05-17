package com.api.clients;

import com.api.model.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.Builder;
import org.apache.http.HttpStatus;

import static com.api.RestAssuredHelper.statusMatcherFor;
import static com.api.framework.utils.getJsonSchemaFactory;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RestAssuredUserClient extends RestAssuredClient {

    private static final String USERS_PATH = "/users";
    private static final String USERNAME_QUERY_PARAMETER = "username";
    private static final String GET_USER_ERROR_MESSAGE = "Get user error message";

    @Builder
    public RestAssuredUserClient(boolean loggingEnabled) {
       this.loggingEnabled = loggingEnabled;
    }

    //Method to get the user client without login
    public static RestAssuredUserClient getClientForUser() {
        return RestAssuredUserClient.builder()
                .loggingEnabled(false)
                .build();
    }

    @Step
    public ValidatableResponse getUserDetails(String userName) {
        return given()
                .spec(getBaseSpec())
                .queryParam(USERNAME_QUERY_PARAMETER, userName)
                .when()
                .get(USERS_PATH)
                .then();
    }

    @Step
    public User[] getUserDetailsAsExtractedResponse(String userName) {
        return getUserDetails(userName)
                .assertThat()
                //validate user schema
                .body(matchesJsonSchemaInClasspath("userSchema.json")
                        .using(getJsonSchemaFactory()))
                .statusCode(
                        statusMatcherFor(HttpStatus.SC_OK, GET_USER_ERROR_MESSAGE)
                )
                .extract()
                .as(User[].class);
    }
}
