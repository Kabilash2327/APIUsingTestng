package org.practise_API;

import org.apache.commons.io.FileUtils;
import org.endpoints.EndpointforMethods;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.IOException;

import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Kabilesh API_practise
 */

public class EmployeeDetails implements EndpointforMethods {

	int tc13_id;

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
	 * Basic auth-- to pass username and password and verify the authentication
	 */
	@Test
	public void tc06_basic_Auth() {

		given().baseUri("https://postman-echo.com").auth().basic("postman", "password").when().get("/basic-auth").then()
				.assertThat().body("authenticated", is(true));

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

		File file = new File("Files\\postdata.json");
		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT).body(file).when().post(POSTUSERURL_FROM_GIT)
				.then().assertThat().statusCode(201).log().all();
	}

	/**
	 * Update the created repo in GIT using bearer token
	 */
	@Test
	public void tc09_Patch_repo() {

		File file = new File("Files\\patchdata.json");

		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT).body(file).when().patch(PATCHUSERURL_FROM_GIT)
				.then().statusCode(200).log().all();

	}

	/**
	 * Delete the created repo in GIT using bearer token
	 */
	@Test
	public void tc10_Delete_repo() {

		given().header("Authorization", "Bearer " + BEARERTOKEN_FROM_GIT).when().delete(DELETEUSERURL_FROM_GIT).then()
				.assertThat().statusCode(204).log().all();

	}

	/**
	 * Schema validation in post method
	 */
	@Test
	public void tc11_SchemaValidation() {

		File file = new File("src\\main\\resources\\tc11_reqBody.json");

		given().header("Content-type", "application/json").body(file).when().post(POSTUSERURL).then()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("tc11_schemavalidator.json")).log().all();

	}

	@Test
	public void tc12_SchemaValidation_in_hamcrest() throws IOException {

		File inputJson = new File("src\\main\\resources\\tc12_resBody.json");
		String readFileToString = FileUtils.readFileToString(inputJson, "UTF-8");

		File inputSchema = new File("src\\main\\resources\\tc11_schemavalidator.json");
		MatcherAssert.assertThat(readFileToString, JsonSchemaValidator.matchesJsonSchema(inputSchema));

	}

	@Test
	public void tc13_createuser_reqChaining() {

		File file = new File("Files\\tc13_reqbody.json");

		tc13_id = given().contentType("application/json").body(file).when().post(POSTUSERURL).jsonPath().getInt("id");

	}

	@Test(dependsOnMethods = { "tc13_createuser_reqChaining" })
	public void tc14_updateuser_chaining() {

		File file = new File("Files\\tc14_reqbody.json");

		given().contentType("application/json").body(file).when().put(PUTUSERURLforChaining + tc13_id).then()
				.statusCode(200).log().all();

	}

	@Test(dependsOnMethods = { "tc14_updateuser_chaining" })
	public void tc15_deleteuser_chaining() {

		given().when().delete(DELETEUSERURLforChaining + tc13_id).then().statusCode(204).log().all();

	}
}
