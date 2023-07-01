package tests.stepdefinision.patch;

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

public class UpdateUserPartially {
	
	private Response response;
	private JSONObject userData;
	
	@Given("^I have the following data for an user$")
	public void getUserData(DataTable user) {
		
		userData = new JSONObject();
		
		for(Map<String, String> data : user.asMaps(String.class, String.class)) {
			
			userData.put(data.get("key"), data.get("value"));
		}
	}

	@When("^I send a PATCH request to \"(.*)\"$")
	public void updateUserInfo(String endPoint) {
		
		response = ApiConstant.getResponseWithEndPoint(RequestEnums.PATCH, userData, endPoint);
	}
	
	@Then("^Response statuscode should be (\\d+)$")
	public void getUpdatedStatusCode(String statusCode) {
		
		assertEquals(Integer.parseInt(statusCode), ApiConstant.getStatusCode(response));
		
	}
}
