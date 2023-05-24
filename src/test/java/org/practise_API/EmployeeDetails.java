package org.practise_API;

import org.endpoints.EndpointforMethods;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Kabilesh
 * API_practise
 */

public class EmployeeDetails implements EndpointforMethods {
	
	/**
	 * Create using hardcode and verify assert
	 */

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
	
	/**
	 * Update using hardcode and verify assert
	 */


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
	
	/**
	 * Delete and verify assert
	 */
	

	@Test
	public void tc03_deleteEmployee() {

		int delete_statuscode = 204;

		baseURI = "https://reqres.in/api/users/4";

		RequestSpecification requestSpecification = given();
		Response response = requestSpecification.request(Method.DELETE);

		System.out.println(response.statusLine());
		Assert.assertEquals(response.getStatusCode(), delete_statuscode, "Deleted");

	}

	/**
	 * Get using Bdd style
	 */
	@Test
	public void tc04_bDD_getAll() {

		given().baseUri("https://reqres.in/api/").when().get("users?page=2").prettyPrint();
	}

	
	
	@Test(enabled = false)
	public void tc05_postData_jsonFile() {

		File json_reader = new File("Files\\postdata.json");

		given().baseUri("https://reqres.in/api").header("Content-Type", "applictaion/json").body(json_reader).when()
				.post("/user").then().statusCode(201).log().all();

	}

	/**
	 * Basic auth-- to pass username and password to verify true 
	 */
	@Test
	public void tc06_basic_Auth() {

		 String prettyPrint = given().baseUri("https://postman-echo.com").auth().basic("postman", "password").when().get("/basic-auth")
				.prettyPrint();
		JSONObject jsonObject = new JSONObject();
       
	}
	
	/**
	 * Get all repo from GIT
	 */

	@Test
	public void tc07_Get_all_repo() {

		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT).when().get(GETUSERURL_FROM_GIT).prettyPrint();
	}
	

	
	/**
	 * Create new repo in GIT using bearer token
	 */
	
	@Test
	public void tc08_Post_Repo() {
		
		File file = new File ("Files\\postdata.json");
		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT).body(file).when().post(POSTUSERURL_FROM_GIT)
		.then().statusCode(201).log().all();


	}
	
	/**
	 * Update the created repo in GIT using bearer token
	 */
	@Test
	public void tc09_Patch_repo() {
      
		File file = new File("Files\\patchdata.json");
		
		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT)
		.body(file).when().patch(PATCHUSERURL_FROM_GIT).then().statusCode(200).log().all();

	}
	/**
	 * Delete the created repo in GIT using bearer token
	 */
	@Test
	public void tc10_Delete_repo() {
		 
		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT)
		.when().delete(DELETEUSERURL_FROM_GIT).then().assertThat().statusCode(204).log().all();

	}
	
	
}
