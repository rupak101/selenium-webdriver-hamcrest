package com.api.clients;

import com.api.model.Comment;
import com.api.model.UserPost;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.Builder;
import org.apache.http.HttpStatus;

import static com.api.RestAssuredHelper.statusMatcherFor;
import static com.api.framework.utils.getJsonSchemaFactory;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RestAssuredUserPostClient extends RestAssuredClient {

    private static final String POST_ID_PATH_PARAMETER = "postId";
    private static final String USER_ID_QUERY_PARAMETER = "userId";

    private static final String POSTS_PATH = "/posts";
    private static final String POST_ID_PATH_PLACEHOLDER = "{" + POST_ID_PATH_PARAMETER + "}";
    private static final String COMMENTS_PATH = POSTS_PATH + "/" + POST_ID_PATH_PLACEHOLDER + "/comments";

    private static final String GET_POST_ERROR_MESSAGE = "Get user post error message";

    @Builder
    public RestAssuredUserPostClient(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    //Get the user post client without login
    public static RestAssuredUserPostClient getClientForPost() {
        return RestAssuredUserPostClient.builder()
                .loggingEnabled(false)
                .build();
    }

    @Step
    public ValidatableResponse getUserPosts(int userId) {
        return given()
                .spec(getBaseSpec())
                .queryParam(USER_ID_QUERY_PARAMETER, userId)
                .when()
                .get(POSTS_PATH)
                .then();
    }

    @Step
    public UserPost[] getUserPostsAsExtractedResponse(int userId) {
        return getUserPosts(userId)
                .assertThat()
                //validate user post schema
                .body(matchesJsonSchemaInClasspath("userPostSchema.json")
                        .using(getJsonSchemaFactory()))
                .statusCode(
                        statusMatcherFor(HttpStatus.SC_OK, GET_POST_ERROR_MESSAGE)
                )
                .extract()
                .as(UserPost[].class);
    }

    @Step
    public Comment[] getUserComments(int postId) {
        return given()
                .spec(getBaseSpec())
                .pathParam(POST_ID_PATH_PARAMETER, postId)
                .when()
                .get(COMMENTS_PATH)
                .then()
                .assertThat()
                //validate user comment schema
                .body(matchesJsonSchemaInClasspath("userCommentSchema.json")
                        .using(getJsonSchemaFactory()))
                .statusCode(
                        statusMatcherFor(HttpStatus.SC_OK, GET_POST_ERROR_MESSAGE)
                )
                .extract()
                .as(Comment[].class);
    }
}
