package com.test.endpoints;


import com.test.payloads.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;
public class UserEndpoints {

	public static Response create_user(User payload) {
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when().post(Routes.post_user);
		return response;
	}
	
	public static Response get_user(String username) {
		Response response=
				given()
				 .pathParam("username", username)
				.when()
				 .get(Routes.get_user);
		return response;
	}
	
	public static Response update_user(String username,User payload) {
		Response response=
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				 .body(payload)
				.when()
				 .put(Routes.put_user);
		return response;
		
	}
	
	public static Response delete_user(String username) {
		Response response=
				given()
				.pathParam("username", username)
			   .when()
			   .delete(Routes.delete_user);
		return response;
	}
}
