package stepDefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =  new TestDataBuild();
	static String placeId;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {

		data =  new TestDataBuild();

		
		res=given().spec(requestSpecification())
		.body(data.addPlacePayLoad(name,language,address));
		
		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String requestMethod) {
		
		APIResources resourceAPI = APIResources.valueOf(resource.toUpperCase()); 
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(requestMethod.equalsIgnoreCase("POST")) {
		response =res.when().post(resourceAPI.getResource());
		}
		else if(requestMethod.equalsIgnoreCase("GET")) {
			response =res.when().get(resourceAPI.getResource());
			}
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(int statusCode) {
	    assertEquals(response.getStatusCode(), statusCode);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    String value = getJsonPath(response, keyValue);
	    assertEquals(value,expectedValue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String name, String api) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		placeId = getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(api,"GET");
		System.out.println(response.asString());
		String keyValueName = getJsonPath(response, "name");
		assertEquals(keyValueName, name);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		data =  new TestDataBuild();
	    res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

}
