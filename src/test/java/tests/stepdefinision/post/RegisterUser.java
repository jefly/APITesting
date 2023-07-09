package tests.stepdefinision.post;

import static org.testng.Assert.assertEquals;

import java.util.Map;
import java.util.Set;

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

public class RegisterUser {
	
	private Response response;
	private JSONObject userData;
	
	@Given("^I have the following User Data$")
	public void getUserData(DataTable user) {
		
		userData = new JSONObject();
		
		for(Map<String, String> data : user.asMaps(String.class, String.class)) {
			
			userData.put(data.get("username"), data.get("value"));
		}
	}

	@When("^I send a POST request to \"(.*)\"$")
	public void getRequestInfo(String endPoint) {
		
		response = ApiConstant.getResponseWithEndPoint(RequestEnums.POST, userData, endPoint);
	}
	
	@Then("^the statuscode should be (\\d+)$")
	public void getCreatedStatusCode(String statusCode) {
		
		assertEquals(Integer.parseInt(statusCode), response.getStatusCode());
		
	}
	
	@And("^the response should contain the user details$")
	public void verifyTheRegistration(DataTable dataTable) {

 		Set<String> keySet = ApiConstant.getReturnedJsonKeys(response);
		
		for(String key : keySet) {
			if(key.equals(ApiConstant.JSON_RETURN))
				assertEquals(ApiConstant.JSON_RETURN, key);
				break;
		}
		
	}
}
