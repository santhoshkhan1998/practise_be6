package test;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

import java.util.List;

import org.testng.Assert;
import service.UserService;


public class PostCreateWithArrayTest {


 @Test
 public void postCreateWithArrayTest(){
        // Initialize Faker for data generation
        Faker faker = new Faker();

        // Create a list to represent users (array for the API)
        List<pojo.CreateWithArrayRequest> users = new java.util.ArrayList<>();

        // Generate and add two users as test data
        for (int i = 0; i < 2; i++) {
            pojo.CreateWithArrayRequest user = new pojo.CreateWithArrayRequest();
            user.setId(faker.idNumber().hashCode());
            user.setUsername(faker.name().username());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setUserStatus(0);
            users.add(user);
        }

        // Send POST /user/createWithArray request
        UserService userService = new service.UserService();
        Response response = userService.postCreateWithArray(users);
        response.then().log().all();

        // Assert the response
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertEquals(response.jsonPath().getString("type"), "unknown", "Type should be unknown");
        Assert.assertNotNull(response.jsonPath().getString("message"), "Message should not be null");
    
 }


}
