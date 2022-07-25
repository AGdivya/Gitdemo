package rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import files.Payload;
import files.ReusableMethods;

public class Basic_API_Integration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Add Place --> Update Place using new Address --> Get Place to validate if new address is updated or not
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").contentType("application/json").body(Payload.addPlace())
		.when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String Place_Id = js.getString("place_id");
		System.out.println(Place_Id);
		
		//Update
		String newAddress = "Summer walk africa";
		given().log().all().contentType("application/json").body("{\n"
				+ "    \"place_id\":\""+Place_Id+"\",\n"
				+ "    \"address\": \""+newAddress+"\",\n"
				+ "    \"key\": \"qaclick123\"\n"
				+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"))
		.header("Server","Apache/2.4.41 (Ubuntu)");
		
		//Get
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", Place_Id)
		.contentType("application/json").when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(getPlaceResponse);
		
		//JsonPath js1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		
        System.out.println(actualAddress);
        
        Assert.assertEquals(actualAddress, newAddress);
        System.out.println("assertion done successfully");
				
	}

}
