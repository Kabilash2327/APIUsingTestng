package org.practise_API;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.endpoints.DemoCyclosEndpoints;
import org.testng.annotations.Test;

public class DemoCyclos implements DemoCyclosEndpoints {

	public String queryPathforTC02 = "?fields=status";

	/**
	 * 25-5
	 */

	@Test
	public void tc01_get_accounts() {

		given().header("accept", "application/json").header("Authorization", "Basic ZGVtbzoxMjM0").when()
				.get(BASEURLDEMOCYCLOS + GET_GETACCOUNT).then().statusCode(200)
				.body("currency[0].name", equalTo("Units")).log().all();

	}

	/**
	 * 25-5
	 */

	@Test
	public void tc02_get_accounts_passing_query() {

		given().header("accept", "application/json").header("Authorization", "Basic ZGVtbzoxMjM0").when()
				.get(BASEURLDEMOCYCLOS + GET_GETACCOUNT + queryPathforTC02).then().statusCode(200)
				.body("status[0].balance", equalTo("11380.99")).log().all();

	}

	/**
	 * 25-5
	 */

	@Test
	public void tc03_get_accounts_listdata() {
		given().header("accept", "application/json").header("Authorization", "Basic a2FiaToyMzI3").when()
				.get(BASEURLDEMOCYCLOS + GET_LISTOFACCOUNTS).then().statusCode(200)
				.body("user.display", equalTo("Kabilesh")).log().all();

	}

	/**
	 * 26-5
	 */

	@Test
	public void tc04_get_account_accountType() {

		given().header("accept", "application/json").header("Authorization", "Basic a2FiaToyMzI3").when()
				.get(BASEURLDEMOCYCLOS + GET_ACCOUNTTYPE).then().statusCode(200)
				.body("type.name", equalTo("Member account")).log().all();
	}

	/**
	 * 26-05
	 */
	@Test
	public void tc05_get_address_list_data() {

		given().header("accept", "application/json").header("Authorization", "Basic a2FiaToyMzI3")
		       .when().get(BASEURLDEMOCYCLOS + GET_ADDRESS_LIST_DATA)
			   .then().statusCode(200).body("enablePrivacy", is(true)).log().all();

	}

	/**
	 * 26-05
	 */
	@Test	
	public void tc06_get_auth_session() {
		given().header("accept", "application/json").header("Authorization", "Basic a2FiaToyMzI3").when()
				.get(BASEURLDEMOCYCLOS + GET_AUTH_SESSION).
				then().statusCode(204).log().all();

	}

	/**
	 * 26-05
	 */
	@Test
	public void tc07_get_ui_data() {
		given().header("accept", "application/json").header("Authorization", "Basic a2FiaToyMzI3").when()
				.get(BASEURLDEMOCYCLOS + GET_UI_DATA).then().statusCode(200)
				.body("auth.user.id", is("7762070814192302143")).log().all();
		
	}
	
	

}
