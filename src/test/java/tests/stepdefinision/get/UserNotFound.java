package tests.stepdefinision.get;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;
import org.json.JSONObject;

import org.hamcrest.core.IsEqual;
import org.hamcrest.text.IsEmptyString;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import tests.util.ApiConstant;
import tests.util.RequestEnums;

public class UserNotFound {
	
	private Response response;
	
	@When("^I send a GET request to the \"(.*)\"$")
	public void sendRequest(String endPoint) {
		
		response = ApiConstant.getResponseWithEndPoint(RequestEnums.GET, null, endPoint);
		
	}
	
	@Then("the status code should return (\\d+)$")
	public void verifyStatusCode(String statusCode) {
		
		assertEquals(Integer.parseInt(statusCode), ApiConstant.getStatusCode(response));
	}
	
	@And("the response body should contain empty JSON object")
	public void verifyEmptyJson() {
		
		String responseBody = response.body().asPrettyString();
		
		JSONObject jsonResponse = new JSONObject(responseBody);
		JSONObject emptyJson = new JSONObject("{}");
		
		assertEquals(emptyJson.toString(), jsonResponse.toString());
	}
	
}
