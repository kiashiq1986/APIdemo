package bdd.DemoProject;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class SumValidation {
	
	@Test
	public void sumOfCourses()
	{	
		int sum =0;
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		
		for (int i=0;i<count;i++)
		{
			int price = js.getInt("courses["+i+"].price");
			int copies =js.getInt("courses["+i+"].copies");
			int amount = price*copies;
			sum = sum+amount;
			
			
		}
		System.out.println("Total Sum:"+sum);
		int purchaseamount =js.getInt("dashboard.purchaseAmount");
		System.out.println("Total Sum:"+purchaseamount);
		Assert.assertEquals(sum, purchaseamount);
		
	}

}
