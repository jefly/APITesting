package tests.stepdefinision.put;

//import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.json.JSONObject;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import tests.util.ApiConstant;
import tests.util.RequestEnums;

public class UpdateUser {
	
	private Response response;
	private JSONObject userData;
	
	@Given("^I have the below User info$")
	public void getUserData(DataTable user) {
		
		userData = ApiConstant.getJsonObject(user, "key", "value");
	}

	@When("^I send a PUT request to \"(.*)\"$")
	public void updateUserInfo(String endPoint) {
		
		response = ApiConstant.getResponseWithEndPoint(RequestEnums.PUT, userData, endPoint);
	}
	
	@Then("^Statuscode of the response should be (\\d+)$")
	public void getUpdatedStatusCode(String statusCode) {
		
		assertEquals(Integer.parseInt(statusCode), response.getStatusCode());
		
	}
	
//	@And("^the response body should contain the updated user info$")
//	public void confirmUserDetails(DataTable dataTable) {
//
//		String objectName = "data";
//		
//		Map<String, String> responseData = response.as(new TypeRef<Map<String, String>>(){});
//		
//		for(Map<String, String> featureInfo : dataTable.asMaps(String.class, String.class)) {
//			
//			String key = featureInfo.get("key");
//	        String value = featureInfo.get("value"); 
//	        
//	        String s = responseData.get(key);
//	        assertEquals(value, s);
//		}
//		
//	}
}
