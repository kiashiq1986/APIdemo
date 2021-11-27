package bdd.DemoProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

public class BasicsWithFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\ashiqahamed\\Downloads\\addPlace.json")))).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		
		JsonPath js = new JsonPath(response);
		String placeID= js.get("place_id");
		
		String newaddress ="kidston Terrace";
		
		
		//update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "   \"place_id\":\""+placeID+"\",\r\n"
				+ "   \"address\":\""+newaddress+"\",\r\n"
				+ "   \"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200).
		body("msg" , equalTo("Address successfully updated"));
		
		//get place
		String updrespone = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).
		when().get("/maps/api/place/get/json").then().
		assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReusableMethods.rawToJson(updrespone);
		String actualaddress = js1.getString("address");
		Assert.assertEquals(actualaddress, newaddress);
		
		
		
	}

}
