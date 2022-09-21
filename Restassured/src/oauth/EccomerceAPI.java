package oauth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;

import static io.restassured.RestAssured.*;
import java.io.File;
import org.testng.Assert;
public class EccomerceAPI {

	public static void main(String[] args) {
		
	
	String projectPath = System.getProperty("user.dir");
	
	//login in app
//	RequestSpecification loginRequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType("multipart/form-data").build();
	
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
			//.addHeader("Content-Type", "multipart/form-data").build();
	
	LoginRequest loginRequest = new LoginRequest();
	loginRequest.setUserEmail("postman9693@gmail.com");
	loginRequest.setUserPassword("Abcd@2011");
	
	LoginResponse loginResponse = given().log().all().spec(req).body(loginRequest)
	.when().post("api/ecom/auth/login").then().log().all().
	//.assertThat().statusCode(200).
	extract().response().as(LoginResponse.class);
	
    String token = loginResponse.getToken();
    System.out.println(token);
    
    String userId = loginResponse.getUserId();
    System.out.println(userId);
    
    String actualMessage = loginResponse.getMessage();
    
    Assert.assertEquals(actualMessage, "Login Successfully");
    
    //add Product
    RequestSpecification addProductBaseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
    
//    RequestSpecification addProductReq = given().log().all().spec(addProductBaseReq).param("productName", "Lamp").param("productAddedBy", userId)
//    		.param("productCategory", "Decoration").param("productSubCategory", "Lightining").formParam("productPrice", "1150")
//    		.param("productDescription", "Light lamp").param("productFor", "Home")
//    	//	.multiPart("ProductImage" ,new File(projectPath + "projectPath\\testImages\\31Kk2yy1x5L.jpg"));
//    		.multiPart("ProductImage" , new File("C:\\Users\\HP\\Documents\\Gitdemo\\Restassured\\testImages\\31Kk2yy1x5L.jpg"));
    
    RequestSpecification reqAddProductReq = given().log().all().spec(addProductBaseReq).param("productName", "Lamp").param("productAddedBy", userId).param("productCategory", "Decoration")
    .param("productSubCategory", "Lightining").param("productPrice", "1150").param("productDescription", "Light lamp").param("productFor", "Home")
   // .multiPart("ProductImage", new File("C:\\Users\\HP\\Documents\\Gitdemo\\Restassured\\src\\files\\Test.png"));
    .multiPart("ProductImage", new File("/Restassured/src/files/l.JPG"));
    String addProdResponse = reqAddProductReq.when().post("api/ecom/product/add-product").then().log().all()
    		.assertThat().statusCode(201)
    		.extract().response().asString();

    JsonPath addProductResponse = new JsonPath(addProdResponse);
    String productId = addProductResponse.get("productId");
    System.out.println(productId);
     
	}
	
	//C:\Users\HP\Documents\Gitdemo\Restassured\testImages\31Kk2yy1x5L.jpg
	
}
