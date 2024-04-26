package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import object.Credentials;
import object.User;


import static constants.UserAPI.*;
import static constants.UserAPI.AUTH_USER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UserSteps extends Specification {

    @Step("create new user")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(AUTH_REGISTER_USER)
                .then().log().all();

    }

    @Step("delete user")
    public void deleteUser(String accessToken) {
        given()
                .header("authorization", accessToken)
                .spec(getSpec())
                .when()
                .delete(AUTH_USER)
                .then().log().all();
    }

    @Step("login user")
    public ValidatableResponse login(Credentials credentials) {
        return
                given()
                        .spec(getSpec())
                        .body(credentials)
                        .when()
                        .post(AUTH_LOGIN)
                        .then();

    }

    @Step("get token")
    public String getToken(ValidatableResponse validatableResponse) {
        return validatableResponse.extract().path("accessToken");
    }

    @Step("Delete data after test")
    public void deleteUserDataTests(String accessToken) {
        if (accessToken != null) {
            deleteUser(accessToken);
        } else {
            given().spec(getSpec())
                    .when()
                    .delete(AUTH_USER);
        }
    }
}

