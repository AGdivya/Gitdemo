package oauth;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

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
	   
      // RestAssured.baseURI = "https://rahulshettyacademy.com";
       
      RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
      
       RequestSpecification reqspec = given().log().all().spec(req)
    		   .body(a);
 
       ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
       
       Response responseSpec = reqspec.when().post("/maps/api/place/add/json").then().spec(response).extract().response();
       
       String responseString = responseSpec.asString();
       System.out.println(responseString);     
	}

}
