package rest;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String args[]) {
		/*
		 * 1. Print No of courses returned by API
		 * 
		 * 2.Print Purchase Amount
		 * 
		 * 3. Print Title of the first course
		 * 
		 * 4. Print All course titles and their respective Prices
		 * 
		 * 5. Print no of copies sold by RPA Course
		 * 
		 * 6. Verify if Sum of all Course prices matches with Purchase Amount
		 */
		
		JsonPath js = ReusableMethods.rawToJson(Payload.coursePrice());
		
		//number of courses
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase amount is :"+totalAmount);
		
		//title of course
		String titleOfFirstCourse = js.getString("courses[0].title");
		System.out.println("titleOfFirstCourse is : "+titleOfFirstCourse);
		
		//allcourses title and respective prices
		for(int i=0;i<count;i++)
		{
			System.out.println("titlesOfCourses are : ");
			String title = js.getString("courses["+i+"].title");
			int price = js.getInt("courses["+i+"].price");
			System.out.println(title + "  " + price);
		}
		
		//no of copies sold by RPA Course
//		int numOfCopies = js.getInt("courses[2].copies");
//		System.out.println("no of copies sold by RPA Course : "+numOfCopies);
		
		//or
		for(int i=0 ;i<count;i++)
		{
			String title = js.getString("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA"))
			{
				int numOfCopies = js.getInt("courses["+i+"].copies");
				System.out.println("no of copies sold by RPA Course : "+numOfCopies);	
			}
		}
		
		//Sum of all Course prices matches with Purchase Amount
		int sum=0;
		for(int i=0;i<count;i++)
		{
			int priceOfCourse = js.getInt("courses["+i+"].price");
			int copiesOfCourse = js.getInt("courses["+i+"].copies");
			int totalPurchaseAmount = priceOfCourse * copiesOfCourse;
			sum = sum + totalPurchaseAmount;
		}
		System.out.println("Total price for all courses is : " +sum);
		Assert.assertEquals(sum, totalAmount);
	}

}
