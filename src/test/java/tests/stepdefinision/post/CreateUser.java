package tests.stepdefinision.post;

import static org.testng.Assert.assertEquals;
import org.json.JSONObject;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import tests.util.ApiConstant;
import tests.util.RequestEnums;

public class CreateUser {
	
	private Response response;
	private JSONObject userData;
	
	@Given("^I want to create a new user$")
	public void getUserData(DataTable user) {
		
		userData = ApiConstant.getJsonObject(user, ApiConstant.JSON_KEY, ApiConstant.JSON_VALUE);
	}

	@When("^I send a new POST request to \"(.*)\"$")
	public void getRequestInfo(String endPoint) {
		
		response = ApiConstant.getResponseWithEndPoint(RequestEnums.POST, userData, endPoint);
	}
	
	@Then("^again the status code of the response should be (\\d+)$")
	public void getCreatedStatusCode(String statusCode) {
		
		int actualValue = ApiConstant.getStatusCode(response); 
		
		try {
			
			assertEquals(Integer.parseInt(statusCode), actualValue);
			
		} catch(AssertionError e) {
			
			System.out.println();
			System.out.println("Expected Value: " + statusCode);
			System.out.println("Actual Value: " + actualValue);
			
			System.out.println("=========================== ASSERTION ERROR OCCURED IN CreateUser.java  ============================== ");
			e.printStackTrace();
			
			System.out.println();
			System.out.println("=========================== END OF THE ASSERTION ERROR!!! ============================== ");
			System.out.println();
		}
	}
}
