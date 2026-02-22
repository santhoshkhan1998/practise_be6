package test;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import pojo.UserRequestPojo;
import service.UserService;

public class GetUserNameTest {

    UserRequestPojo userRequest;

    public void setupTestdat() {
        System.out.println("START :: UserTest.setupTestdat");
		userRequest = new UserRequestPojo();
		
		Faker faker = new Faker();
        userRequest.setId(faker.idNumber().hashCode());
		userRequest.setUsername(faker.name().username());
		userRequest.setFirstName(faker.name().firstName());
		userRequest.setLastName(faker.name().lastName());
		userRequest.setEmail(faker.internet().emailAddress());
		userRequest.setPassword(faker.internet().password());
		userRequest.setPhone(faker.phoneNumber().phoneNumber());
		userRequest.setUserStatus(0);
	}


    @Test(priority = 1)
    public void testPostUserName() {
        setupTestdat();
        System.out.println("START :: GetUserNameTest.testPostUserName");
        UserService userService = new UserService();
        Response response = userService.postMethod(userRequest);
        response.then().log().all();
        response.then().statusCode(200);
    }

    @Test(priority = 2)
    public void testGetUserName() {
        System.out.println("START :: GetUserNameTest.testGetUserName");
        UserService userService = new UserService();
        Response response = userService.getUserName(userRequest.getUsername());
        response.then().log().all();
        response.then().statusCode(200);
    }

}
