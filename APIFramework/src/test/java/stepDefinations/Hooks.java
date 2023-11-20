package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefination sd = new StepDefination();
		if(StepDefination.placeId == null) {
			sd.add_Place_Payload_with("RJamil", "English", "Dalla,GA");
			sd.user_calls_with_http_request("addPlaceAPI", "post");
			sd.verify_place_Id_created_maps_to_using("RJamil", "getPlaceapi");
		}
	}
	

}
