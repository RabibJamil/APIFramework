package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import pojo.deletePlace;

public class TestDataBuild {

	
	public AddPlace addPlacePayLoad(String name, String language, String address){
		
		AddPlace payLoad =new AddPlace();
		payLoad.setAccuracy(50);
		payLoad.setAddress(address);
		payLoad.setLanguage(language);
		payLoad.setPhone_number("(+91) 983 893 3937");
		payLoad.setWebsite("https://rahulshettyacademy.com");
		payLoad.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		payLoad.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		payLoad.setLocation(l);
		
		return payLoad;
		
		
	}
	
	public deletePlace deletePlacePayload(String placeID) {
		deletePlace payload = new deletePlace();
		payload.setPlace_id(placeID);
		return payload;
	}
	
	
}
