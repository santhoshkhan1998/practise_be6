package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import pojo.UserRequestPojo;
import pojo.UserResponsePojo;
import service.UserService;

public class UserTestPost {
	
	UserRequestPojo userRequest;
	Faker faker = new Faker();
	
	public void setupTestdat() {
		userRequest = new UserRequestPojo();
		System.out.println("START :: UserTestPost.setupTestdat");
		userRequest.setId(faker.idNumber().hashCode());
		userRequest.setUsername(faker.name().username());
		userRequest.setFirstName(faker.name().firstName());
		userRequest.setLastName(faker.name().lastName());
		userRequest.setEmail(faker.internet().emailAddress());
		userRequest.setPassword(faker.internet().password());
		userRequest.setPhone(faker.phoneNumber().phoneNumber());
		userRequest.setUserStatus(0);
	}
	//New Comment line added
	
	@Test
	public void postmethod() {
		System.out.println("START :: UserTestPost.postmethod");
		setupTestdat();
		UserService userService = new UserService();
		Response post = userService.postMethod(userRequest);
		post.then().log().all();
		post.then().statusCode(200);
		UserResponsePojo userRepsonse = post.as(UserResponsePojo.class);
		
		System.out.println(userRepsonse.getCode());
		Assert.assertEquals(userRepsonse.getType(), "unknown"," Should be Unkown");
		Assert.assertEquals(userRepsonse.getCode(), 200,"Should be 200");
		Assert.assertNotNull(userRepsonse.getMessage(), "Expected Value");

	}
	

}
