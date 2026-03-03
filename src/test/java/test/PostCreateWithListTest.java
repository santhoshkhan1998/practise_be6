package test;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import pojo.UserRequestCreateWithList;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class PostCreateWithListTest {

    @Test
    public void testPostCreateWithList() {
        // Initialize Faker for random data
        Faker faker = new Faker();

        // Create list of users
        List<UserRequestCreateWithList> users = new ArrayList<>();

        // Populate user objects (generating two sample users for demonstration)
        for (int i = 0; i < 2; i++) {
            UserRequestCreateWithList user = new UserRequestCreateWithList();
            user.setId((long) faker.idNumber().hashCode());
            user.setUsername(faker.name().username());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setUserStatus(0);
            users.add(user);
        }

        UserService userService = new UserService();
        Response postResponse = userService.postCreateWithList(users);
        postResponse.then().log().all();

        // A successful create returns 200, response type should be "unknown"
        Assert.assertEquals(postResponse.getStatusCode(), 200, "Status code should be 200");
        String type = postResponse.jsonPath().getString("type");
        Assert.assertEquals(type, "unknown", "Type should be unknown");
        Assert.assertNotNull(postResponse.jsonPath().getString("message"), "Message should not be null");
    }
}
    


