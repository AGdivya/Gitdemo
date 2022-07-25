package rest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Add place API
		given().log().all().queryParam("key", "qaclick123").contentType("application/json")
		.body("{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -38.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Frontline row house khandwa\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"1234, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shohousep\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"Eng-IN\"\n"
				+ "}\n"
				+ "")
		.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200);
		
		//Get Place API
		given().log().all().queryParams("key", "qaclick123", "place_id","24ef00a841db556428912669ce9ca1cb")
		.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200);
		
		//Update/Put Place API
		given().log().all().contentType("application/json").body("{\n"
				+ "    \"place_id\":\"24ef00a841db556428912669ce9ca1cb\",\n"
				+ "    \"address\": \"09, front side layout, cohen 20\",\n"
				+ "    \"key\": \"qaclick123\"\n"
				+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200);

		//Delete Place API
		given().log().all().queryParam("key", "qaclick123").contentType("application/json")
		.body("{\n"
				+ "    \"place_id\":\"24ef00a841db556428912669ce9ca1cb\"\n"
				+ "}").when().delete("maps/api/place/delete/json").then().log().all().assertThat().statusCode(200);
	}

}
