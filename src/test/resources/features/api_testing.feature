Feature: API Testing for https://reqres.in/

	Scenario: Get a Single User status code
	When I send a GET request to "/api/users/2"
  Then the response status code should be 200
  And the response body should contain the user details
  | Key        | ExpectedValue                  |
  | id      	 | 2                              |
  | email   	 | janet.weaver@reqres.in         |
  | first_name | Janet                          |
  | last_name  | Weaver                         |
  | avatar     | https://reqres.in/img/faces/2-image.jpg |

  Scenario: Single User Not Found
  When I send a GET request to the "/api/users/23"
  Then the status code should return 404
  And the response body should contain empty JSON object
  
  Scenario: Create an User
  Given I have the following User Data
  | username | value |
  | name     | John Doe |
  | job      | Software Engineer |  
  When I send a POST request to "/api/users/"
  Then the statuscode should be 201
  And the response should contain the user details
  | Key        | ExpectedValue     |
  | name       | John Doe 				 |
  | job        | Software Engineer |  
  
  Scenario: Update User
  Given I have the below User info
  | key 		 | value |
  | name     | John Doe |
  | job      | QA Manager |  
  When I send a PUT request to "/api/users/2"
  Then Statuscode of the response should be 200
  #And the response body should contain the updated user info
  #| key 		 | value |
  #| name     | John Doe |
  #| job      | QA Manager |
  
  Scenario: Update User job
  Given I have the following data for an user
  | key 		 | value |
  | name     | John Doe |
  | job      | Carpenter |  
  When I send a PATCH request to "/api/users/2"
  Then Response statuscode should be 200
  
  Scenario: Deleting an User
  When I send a DELETE request to "/api/users/2"
  Then the response statuscode must be 204
  
  Scenario: Register an User
  Given I want to create a new user
  | key 		  | value |
  | email     | abc@gmail.com |
  | password  | helloworld | 
  When I send a new POST request to "/api/register"
  Then again the status code of the response should be 200
  