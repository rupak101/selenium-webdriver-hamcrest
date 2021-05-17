package com.urbansports.apiTest;

import com.api.model.Comment;
import com.api.model.User;
import com.api.model.UserPost;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.api.clients.RestAssuredUserClient.getClientForUser;
import static com.api.clients.RestAssuredUserPostClient.getClientForPost;
import static com.api.framework.utils.validateEmailAddress;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Owner("Rupak Mansingh")
@Feature("To perform the validations for the comments for the post made by a specific user")
public class UserPostsAndCommentsTest {
    private static int userId;

    private static final String INVALID_USERNAME = "abc12345";
    private static final int INVALID_POST_ID = 12345678;

    @DataProvider
    public Object[] userData() {
        return new Object[]{"Delphine"};
    }

    @Story("Search for user with valid user name(GET /users)")
    @Description("Search for user ID from the User name")
    @Test(dataProvider = "userData")
    public void getUserDetailsSuccessfully(String userName) {
        User userDetailsResponse = getClientForUser().getUserDetailsAsExtractedResponse(userName)[0];
        userId = userDetailsResponse.getId();

        assertThat("User name didn't match with the response", userDetailsResponse.getUsername(), is(userName));
        assertThat("Zip code is null in the response", userDetailsResponse.getAddress().getZipcode(), is(notNullValue()));
    }

    @Story("Search for user with invalid user name(GET /users)")
    @Description("Search for user ID from invalid user name")
    @Test
    public void getUserDetailsWithInvalidUsername() {
        getClientForUser().getUserDetails(INVALID_USERNAME)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("[]"));
    }

    @Story("Get user post successfully (GET /posts) by the user")
    @Description("Get all the post by a particular user ID")
    @Test(dependsOnMethods = {"getUserDetailsSuccessfully"})
    public void getUserPostsSuccessfully() {
        //Get all post for a particular user.
        UserPost[] postResponse = getPostResponse(userId);

        //validate first user post response from list of
        assertThat("User id didn't match in the response", postResponse[0].getUserId(), is(userId));
        assertThat("User post body is null in the response", postResponse[0].getBody(), is(notNullValue()));
        assertThat("User post title is null in the response", postResponse[0].getTitle(), is(notNullValue()));
    }

    @Story("Get user post with invalid post ID (GET /posts)")
    @Description("Get the all the post by an invalid user ID")
    @Test
    public void getUserPostsWithInvalidPostId() {
        getClientForPost().getUserPosts(INVALID_POST_ID)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("[]"));
    }

    @Story("Get comments successfully(GET /posts/{postId}/comments)")
    @Description("Get comments, validate the comments and email address are in proper format")
    @Test(dependsOnMethods = {"getUserDetailsSuccessfully"})
    public void getCommentsSuccessfully() {
        getUserCommentsAndValidateEmail();
    }

    //Method to get user post by user ID
    private UserPost[] getPostResponse(int userId) {
        return getClientForPost().getUserPostsAsExtractedResponse(userId);
    }

    //Method to get all comments from user posts and validate email address
    private void getUserCommentsAndValidateEmail() {
        UserPost[] postResponse = getPostResponse(userId);
        for (UserPost i : postResponse) {
            getComments(i.getId());
        }
    }

    private void getComments(int postId) {
        Comment[] comments = getClientForPost().getUserComments(postId);
        for (Comment comment : comments) {
            assertEmailAndPostId(comment, postId);
        }
    }

    private void assertEmailAndPostId(Comment comment, int postId) {
        assertThat("Email address is invalid", validateEmailAddress(comment.getEmail()), is(true));
        assertThat("Post id didn't match in the response", comment.getPostId(), is(postId));
    }
}
