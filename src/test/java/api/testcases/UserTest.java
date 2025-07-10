package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.userPayload;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	userPayload userpayload;

	@BeforeClass // means this method will execute before all other methods in this class
	public void generateTestData() {
		faker = new Faker();// object for generating fake data
		userpayload = new userPayload();
		// now we will set the fake data in the created user object by setters
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void testCreateUser() {

		Response response = userEndPoints.createUser(userpayload);

		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUser() {

		Response response = userEndPoints.getUser(this.userpayload.getUsername());

		// log response
		System.out.println("Read User Data");
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void testUpdateUser() {
		
        userpayload.setFirstName(faker.name().firstName());
		Response response = userEndPoints.updateUser(userpayload, this.userpayload.getUsername());

		// log response
		
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);

		// read user data to check if first name is updated or not
		Response responsePostUpdate = userEndPoints.getUser(this.userpayload.getUsername());

		System.out.println("After Update User Data");
		responsePostUpdate.then().log().all();
	}

	@Test(priority = 4)
	public void testDeleteUser() {

		Response response = userEndPoints.deleteUser(this.userpayload.getUsername());

		// log response
		System.out.println("Delete User Data");
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
