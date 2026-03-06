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

	// Method to POST a list of UserRequestCreateWithList objects
	public Response postCreateWithList(java.util.List<pojo.UserRequestCreateWithList> users) {
		System.out.println("START :: UserService.postCreateWithList");
		Response post = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(users)
				.log().all()
				.when()
				.post(ConfigReader.getProperties("postCreateWithList")); // Ensure this key is present in your config
		return post;
	}

	public Response getUserName(String username) {
		System.out.println("START :: UserService.getUserName");
		Response get = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.log().all()
				.when()
				.get(ConfigReader.getProperties("getUserName"));
		return get;
	}

	/**
	 * POST request to 'https://petstore.swagger.io/v2/user/createWithArray'
	 * 
	 * Sends a list/array of users:
	 * 
	 * [
	 *   {
	 *     "id": 0,
	 *     "username": "string",
	 *     "firstName": "string",
	 *     "lastName": "string",
	 *     "email": "string",
	 *     "password": "string",
	 *     "phone": "string",
	 *     "userStatus": 0
	 *   }
	 * ]
	 */
	public Response postCreateWithArray(java.util.List<pojo.CreateWithArrayRequest> users) {
		System.out.println("START :: UserService.postCreateWithArray");
		Response post = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(users)
				.log().all()
				.when()
				.post(ConfigReader.getProperties("postCreateWithArray"));
		return post;
	}


	public Response loginUser(String username, String password) {
		System.out.println("START :: UserService.loginUser");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.queryParam("username", username)
				.queryParam("password", password)
				.log().all()
				.when()
				.get(ConfigReader.getProperties("loginUser")); // Ensure "loginUser" property is set to "/user/login"
		return response;
	}

}
