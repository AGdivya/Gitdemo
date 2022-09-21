package oauth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableTest {

	public static void main(String[] args) {
	
	   AddPlace a = new AddPlace();
	   a.setAccuracy(50);
	   a.setAddress("Keshav Park 19");
	   a.setLanguage("Indian");
	   a.setName("Divya AG");
	   a.setPhoneNumber("(+91) 983 893 3937");
	   a.setWebsite("http://google.com");
	   
	   List<String> ls = new ArrayList<String>();
	   ls.add("shoe park");
	   ls.add("shop bata");
	   a.setTypes(ls);
	   
	   Location l = new Location();
	   l.setLat(-38.383494);
	   l.setLng(33.427362);
	   a.setLocation(l);
	   
       RestAssured.baseURI = "https://rahulshettyacademy.com";
       Response resp = given().log().all()
    		   .queryParam("key", "qaclick123")
    		   .body(a).when().post("/maps/api/place/add/json")
       .then().assertThat().statusCode(200).extract().response();
       
       String responseString = resp.asString();
       System.out.println(responseString);     
	}

}
