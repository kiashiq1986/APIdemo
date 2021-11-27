package bdd.DemoProject;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {


	JsonPath js = new JsonPath(Payload.CoursePrice());
	int count = js.getInt("courses.size()");
	System.out.println(count);
	
	int totalamount = js.getInt("dashboard.purchaseAmount");
	System.out.println(totalamount);
	
	String fristcourse = js.getString("courses[0].title");
	System.out.println(fristcourse);
	
	for (int i=0;i<count;i++)
	{
		String title = js.get("courses["+i+"].title");
		int price = js.getInt("courses["+i+"].price");
		System.out.println(title);
		System.out.println(price);
		
	}
	
	for (int i=0;i<count;i++)
	{
		String titles = js.get("courses["+i+"].title");
		if(titles.equalsIgnoreCase("RPA"))
		{
			int copies = js.getInt("courses["+i+"].copies");
			System.out.println("no.of rPA copies sold "+copies);
			break;
			
		}
		
	}
	
	
	}
	
}
