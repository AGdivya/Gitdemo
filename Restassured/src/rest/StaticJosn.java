package rest;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import io.restassured.RestAssured;


public class StaticJosn {


	@Test
	public static  void addBook() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type","application/json")
		//.body(Payload.addBook("edf","456"))
		.body(new String(Files.readAllBytes(Paths.get("/Users/divyaupadhyay/eclipse-personal/Restassured/src/files/jsonvalidator.json"))))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().asString();
		
		System.out.println(response);
		
//		given().log().all().contentType("application/json")
//		.body("{\n"
//				+ "    \"ID\": \""+id+"\"\n"
//				+ "}")
//				.when().delete("Library/DeleteBook.php").then().assertThat()
//				.statusCode(200).body("msg",equalTo("book is successfully deleted"))
//				.extract().response().asString();
	}
}
