package tests.stepdefinision.get;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import tests.util.ApiConstant;
import tests.util.RequestEnums;

public class GetSingleUserData {
	
	private Response response;

	@When("^I send a GET request to \"(.*)\"$")
	public void getRequestInfo(String endPoint) {
		
		response = ApiConstant.getResponseWithEndPoint(RequestEnums.GET, null, endPoint);
	}
	
	@Then("^the response status code should be (\\d+)$")
	public void getStatusCode(String statusCode) {
		
		assertEquals(Integer.parseInt(statusCode), ApiConstant.getStatusCode(response));
		
	}
	
	@And("^the response body should contain the user details$")
	public void getUserDetails(DataTable userData) {

		String objectName = "data";
		
		Map<String, Map<String, String>> user = response.as(new TypeRef<Map<String, Map<String, String>>>(){});
		
		for (Map<String, String> data : userData.asMaps(String.class, String.class)) {
			
	        String key = data.get("key");
	        String expectedValue = data.get("value");
	        
	        assertEquals(expectedValue, user.get(objectName).get(key));
	    }
	}
}
