package org.endpoints;

/**
 * 
 * @author Kabilesh
 * Endpoints for the demo website DEMO_CYCLOS
 * created on 25-05
 */

public interface DemoCyclosEndpoints {
	
	public final static String BASEURLDEMOCYCLOS = "https://demo.cyclos.org/api";

	public final static String GET_GETACCOUNT = "/self/accounts";
	
	public final static String GET_LISTOFACCOUNTS = "/self/accounts/list-data";
	
	public final static String GET_ACCOUNTTYPE = "/self/accounts/memberaccount";
	
	public final static String GET_ADDRESS_LIST_DATA = "/self/addresses/list-data";
	
	public final static String GET_AUTH_SESSION = "/auth/session";
	
	public final static String GET_UI_DATA = "/ui/data-for-ui";
		
	
}
