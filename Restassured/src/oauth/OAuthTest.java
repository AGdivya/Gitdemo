package oauth;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
public class OAuthTest {
	
	public static void main(String args[])
	{
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
			    
		/*
		 * 		String projectPath = System.getProperty("user.dir");
		System.out.println("Project Path is"+projectPath);
		    
		//System.setProperty("webdriver.chrome.driver",projectPath +"/lib/chromedriver");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\Gitdemo\\Restassured\\lib\\chromedriver.exe");
		
	    WebDriver driver = new ChromeDriver();
	    driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
	    driver.manage().window().maximize();
	    driver.findElement(By.id("identifierId")).sendKeys("agrawal210594@gmail.com");
	    driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span")).click();
	    
	    NOTE :- The above code is no more required because of Google policy .Google is not allowing login via automation script
	    Open in browser below URL
	    https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		 */
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qgGEA2OHAnQB_7GqthAZK0g8RziEgNmKHGW7S3QrRVupHGoq7E7VCM9h04N6g_Ujg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
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
		
		String response = given().log().all().queryParam("access_token", access_token).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		//.extract().response().asString();
		
		System.out.println(response);
	
	}

}
