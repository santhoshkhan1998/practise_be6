package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import pojo.UserLoginResponse;
import pojo.UserRequestPojo;
import service.UserService;

public class GetLoginUserTest {

    UserRequestPojo userRequest;
    Faker faker = new Faker();

    public void setupTestdata() {
        userRequest = new UserRequestPojo();
        System.out.println("START :: GetLoginUserTest.setupTestdata");
        userRequest.setId(faker.idNumber().hashCode());
        userRequest.setUsername(faker.name().username());
        userRequest.setFirstName(faker.name().firstName());
        userRequest.setLastName(faker.name().lastName());
        userRequest.setEmail(faker.internet().emailAddress());
        userRequest.setPassword(faker.internet().password());
        userRequest.setPhone(faker.phoneNumber().phoneNumber());
        userRequest.setUserStatus(0);
    }

    @Test
    public void testLoginUser() {
        System.out.println("START :: GetLoginUserTest.testLoginUser");

        // 1. Create a user with POST
        setupTestdata();
        UserService userService = new UserService();
        Response postResponse = userService.postMethod(userRequest);
        postResponse.then().statusCode(200);

        // 2. Login with username and password of just created user
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();

        Response response = userService.loginUser(username, password);

        response.then().log().all().statusCode(200);

        UserLoginResponse loginResponse = response.as(UserLoginResponse.class);

        Assert.assertEquals(loginResponse.getCode(), Integer.valueOf(200), "Code should be 200");
        Assert.assertEquals(loginResponse.getType(), "unknown", "Type should be unknown");
        Assert.assertTrue(
            loginResponse.getMessage() != null && loginResponse.getMessage().startsWith("logged in user session:"),
            "Message should indicate session login");
    }
}
