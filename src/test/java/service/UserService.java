package service;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.UserRequestPojo;
import utilities.ConfigReader;

public class UserService {
	/*
	 * postman request POST 'https://petstore.swagger.io/v2/user' \ --header
	 * 'accept: application/json' \ --header 'Content-Type: application/json' \
	 * --body '{ "id": 0, "username": "string", "firstName": "string", "lastName":
	 * "string", "email": "string", "password": "string", "phone": "string",
	 * "userStatus": 0 }'
	 */	
	static {
		baseURI = ConfigReader.getProperties("base_uri");
	}
	
	
	public Response postMethod(UserRequestPojo userRequest) {
		System.out.println("START :: UserService.postMethod");
		Response post = given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(userRequest)
		.log().all()
		.when()
		.post(ConfigReader.getProperties("post"));
		return post;
		

	}

}
