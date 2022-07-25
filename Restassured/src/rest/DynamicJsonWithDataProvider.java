package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJsonWithDataProvider {
	
	@Test(dataProvider="BooksData")
	public static  void addDeleteBook(String isbn ,String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type","application/json")
		.body(Payload.addBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
		given().log().all().contentType("application/json")
		.body("{\n"
				+ "    \"ID\": \""+id+"\"\n"
				+ "}")
				.when().delete("Library/DeleteBook.php").then().assertThat()
				.statusCode(200).body("msg",equalTo("book is successfully deleted"))
				.extract().response().asString();
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"edif","4256"},{"etdf","4156"},{"egdf","4576"},{"eygdf","47856"}};
	}
	
}
