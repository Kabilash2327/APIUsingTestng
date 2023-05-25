package org.endpoints;

/**
 * 
 * @author Kabilesh
 * Used to store endpoints and token
 *
 */

public interface EndpointforMethods {

	public static String GETUSERURL = "https://reqres.in/api/users?page=2";
	public static String POSTUSERURL = "https://reqres.in/api/users";
	public static String PUTUSERURL = "https://reqres.in/api/users/2";
	public static String PATCHUSERURL = "https://reqres.in/api/users/2";
	public static String DELETEUSERURL = "https://reqres.in/api/users/2";
	
	public static String PUTUSERURLforChaining = "https://reqres.in/api/users/";
	public static String DELETEUSERURLforChaining = "https://reqres.in/api/users/";
	
	
	public static String BEARERTOKEN_FROM_GIT = "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";
	
	
	
	public static String GETUSERURL_FROM_GIT = "https://api.github.com/user/repos";
	
	public static String POSTUSERURL_FROM_GIT = "https://api.github.com/user/repos";
	
	public static String PATCHUSERURL_FROM_GIT = "https://api.github.com/repos/Kabilash2327/practise_repo_from_postman";
	
	public static String DELETEUSERURL_FROM_GIT = "https://api.github.com/repos/Kabilash2327/repo_from_postman";
	
	
}
