package api.endpoints;

import static io.restassured.RestAssured.given;

//import com.github.scribejava.core.model.Response;

import api.payload.userPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints {

	public static Response createUser(userPayload payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(Routes.post_url); // static variable use krne k liye we have to use the classname

		return response;
	}

	public static Response getUser(String userName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", userName)

				.when().get(Routes.get_url); // static variable use krne k liye we have to use the classname

		return response;
	}

	public static Response updateUser(userPayload payload, String userName) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", userName).body(payload)

				.when().put(Routes.put_url); // static variable use krne k liye we have to use the classname

		return response;
	}

	public static Response deleteUser(String userName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", userName)

				.when().delete(Routes.delete_url); // static variable use krne k liye we have to use the classname

		return response;
	}
}
