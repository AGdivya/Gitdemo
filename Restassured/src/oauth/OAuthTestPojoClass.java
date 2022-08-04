package oauth;
import pojo.Api;
import pojo.GetCourses;
import pojo.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
public class OAuthTestPojoClass {
	
	public static void main(String args[])
	{
	    /*
	    NOTE :- The above code is no more required because of Google policy .Google is not allowing login via automation script
	    Open in browser below URL
	    https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		 */
		String [] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qiDmX1yL6VE2pN4gOYniKLVb129mmpLZmazJQmfUffPNgu9mB9uZBrHP1EGeOpm9Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialcode = url.split("code=")[1];
		System.out.println(partialcode);
		String completecode =partialcode.split("&scope")[0];
		System.out.println(completecode);
		
		String access_token_Response = given().urlEncodingEnabled(false).log().all()
				.queryParams("code",completecode)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code").when().post("https://www.googleapis.com/oauth2/v4/token").then().log()
				.all().extract().response().asString();
		JsonPath js = new JsonPath(access_token_Response);
		String access_token = js.getString("access_token");
		
		GetCourses gc = given().log().all().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		//.extract().response().asString();
		
		System.out.println(gc.getExpertise());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getCourses());
		
		//get me course title for first course of API
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		//get me price for SoapUI API course price
		List<Api> apiCourse = gc.getCourses().getApi();
		for(int i=0;i<apiCourse.size() ;i++)
		{
			if(apiCourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(apiCourse.get(i).getPrice());
			}
		}
		
		//get me all courses title name for web automation
		ArrayList<String> a = new ArrayList<String>();
		List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
		for(int i=0; i<webAutomationCourses.size();i++)
		{
			//System.out.println(webAutomationCourses.get(i).getCourseTitle());
			a.add((webAutomationCourses.get(i).getCourseTitle()));
		}
	
		List<String> expectedList = Arrays.asList(courseTitles);
		
		Assert.assertTrue(a.equals(expectedList), "Assertion done successfully");
	}

}
