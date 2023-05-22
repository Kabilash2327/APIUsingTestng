package org.practise_API;

import org.endpoints.EndpointforMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeeDetails implements EndpointforMethods {

	@Test
	public void tc01_createMethod_usingHardCode() {

		int post_statuscode = 201;

		baseURI = "https://reqres.in/api/users";

		RequestSpecification requestSpecification = given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"name\": \"Jeeva\",\r\n" + "    \"job\": \"Admin\"\r\n" + "}");

		Response response = requestSpecification.request(Method.POST);
		System.out.println(response.getStatusLine());
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.statusCode(), post_statuscode, "Created");

	}

	@Test
	public void tc02_updateEmployee() {

		int put_statuscode = 200;

		baseURI = "https://reqres.in/api/users/2";
		RequestSpecification requestSpecification = given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"name\": \"Kumar\",\r\n" + "    \"job\": \"Admin\"\r\n" + "}");

		Response response = requestSpecification.request(Method.PUT);
		System.out.println(response.getStatusLine());
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), put_statuscode, "Updated");

	}

	@Test
	public void tc03_deleteEmployee() {

		int delete_statuscode = 204;

		baseURI = "https://reqres.in/api/users/4";

		RequestSpecification requestSpecification = given();
		Response response = requestSpecification.request(Method.DELETE);

		System.out.println(response.statusLine());
		Assert.assertEquals(response.getStatusCode(), delete_statuscode, "Deleted");

	}

	@Test
	public void tc04_bDD_getAll() {

		given().baseUri("https://reqres.in/api/").when().get("users?page=2").prettyPrint();
	}

	@Test
	public void tc05_postData_jsonFile() {

		File json_reader = new File("Files\\postdata.json");

		given().baseUri("https://reqres.in/api").header("Content-Type", "applictaion/json").body(json_reader).when()
				.post("/user").then().statusCode(201).log().all();

	}

	@Test
	public void tc06_basic_Auth() {

		given().baseUri("https://postman-echo.com").auth().basic("postman", "password").when().get("/basic-auth")
				.prettyPrint();

	}

	@Test
	public void tc07_bearer_Token() {

		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT).when().get(GETUSERURL_FROM_GIT).prettyPrint();
	}
}
