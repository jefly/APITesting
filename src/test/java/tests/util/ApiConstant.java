package tests.util;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONObject;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public interface ApiConstant {

	String BASE_URI = "https://reqres.in/";
	int SUCCESS_200 = 200;
	int CREATED_201 = 201;
	int DELETED = 204;
	String JSON_NAME = "data";
	String JSON_KEY = "key";
	String JSON_VALUE = "value";
	String JSON_RETURN =  "updatedAt";
	
	static String getBaseURI() {
		baseURI = BASE_URI;
		return baseURI;
	}
	
	static Response getResponseWithEndPoint(RequestEnums enumType, JSONObject jsonObject, String endPoint) {
		
		getBaseURI();
		
		if(enumType == RequestEnums.GET) {
			
			return  given().when().get(endPoint);
			
		} else if(enumType == RequestEnums.POST) {
			
			return  given().when().body(jsonObject).post(endPoint);
			
		} else if(enumType == RequestEnums.PUT) {
			
			return  given().when().body(jsonObject).put(endPoint);
			
		} else if(enumType == RequestEnums.PATCH) {
			
			return  given().when().body(jsonObject).patch(endPoint);
			
		} else {
			
			return  RestAssured.delete(endPoint);
		}
		
	}
	
	static int getStatusCode(Response response) {
		
		return response.getStatusCode();
	}
	
	static JSONObject getJsonObject(DataTable user, String key, String value) {
		
		JSONObject userData = new JSONObject();
		
		for(Map<String, String> data : user.asMaps(String.class, String.class)) {
			
			userData.put(data.get(JSON_KEY), data.get(JSON_VALUE));
		}

		return userData;
	}
	
	
}
