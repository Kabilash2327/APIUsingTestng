package org.practise_API;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.endpoints.EndpointforMethods;
import org.json.simple.JSONObject;

/**
 * 
 * @author Kabilesh
 *
 */
public class HTTPMethods implements EndpointforMethods {

	/**
	 * Verify Get method and verify status code using assert
	 */
	@Test
	public void tc01_Get_positive_case() {
		Response response = get("https://reqres.in/api/users?page=2");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		String asPrettyString = response.getBody().asPrettyString();
		System.out.println(asPrettyString);
		System.out.println(response.getTime());
		Assert.assertEquals(statusCode, 200, "Verified the status code succesfully");

	}

	@Test
	public void tc02_Get_positiva_case_using_static() {

		// baseURI="https://reqres.in/api";

		given().get(GETUSERURL).then().assertThat().statusCode(200)
		.body("data[1].email", equalTo("lindsay.ferguson@reqres.in"))
		.body("data.last_name", hasItems("Edwards", "Fields")).log().all();
	}

	@Test
	public void tc03_PostMethod() {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", "raj");
		jsonObject.put("class", "12");
		jsonObject.put("status", "promoted");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).

		body(jsonObject.toJSONString()).when().post(POSTUSERURL).then().body("name", equalTo("raj")).statusCode(201).log().all();
	}

	@Test
	public void tc04_PutMethod() {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", "ram");
		jsonObject.put("class", "12");
		jsonObject.put("status", "promoted");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).

		body(jsonObject.toJSONString()).when().post(PUTUSERURL).then().statusCode(201).log().all();

	}

	@Test
	public void tc05_PatchMethod() {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", "ranjith");
		jsonObject.put("class", "12");
		jsonObject.put("status", "promoted");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)

		.body(jsonObject.toJSONString()).when().patch(PATCHUSERURL).then().assertThat().statusCode(200).log()
		.all();
	}

	@Test
	public void tc06_DeleteMethod() {

		when().delete(DELETEUSERURL).then().assertThat().statusCode(204).log().all();

	}
}
