package tests.stepdefinision.delete;

import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import tests.util.ApiConstant;
import tests.util.RequestEnums;

public class DeleteUser {
	
	private Response response;
	
	@When("I send a DELETE request to \"(.*)\"$")
	public void updateUserInfo(String endPoint) {
		
		response = ApiConstant.getResponseWithEndPoint(RequestEnums.DELETE, null, endPoint);
	}
	
	@Then("^the response statuscode must be (\\d+)$")
	public void getUpdatedStatusCode(String statusCode) {
		
		assertEquals(Integer.parseInt(statusCode), ApiConstant.getStatusCode(response));
		
	}
}
