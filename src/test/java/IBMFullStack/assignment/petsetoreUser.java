package IBMFullStack.assignment;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class petsetoreUser {
	
	@DataProvider(name = "userDetails")
	public Object[][] data(){
		Object[][] userData = new Object[2][6];
		userData[0][0] = "gargs";
		userData[0][1] = "parikshit";
		userData[0][2] = "garg";
		userData[0][3] = "gargparikshit1@gmail.com";
		userData[0][4] = "tumhihoe";
		userData[0][5] = "1234567890";
		userData[1][0] = "muneem";
		userData[1][1] = "anurag";
		userData[1][2] = "garg";
		userData[1][3] = "anuraggarg03@gmail.com";
		userData[1][4] = "sabBtaDunTumhein";
		userData[1][5] = "0987654321";
		return userData;
	}

	@Test(enabled = true, dataProvider = "userDetails", priority = 1)
	public void createUser(String userName, String fName, String lName, String id, String pass, String num){
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("username", userName);
		obj.put("firstName", fName);
		obj.put("lastName", lName);
		obj.put("email", id);
		obj.put("password", pass);
		obj.put("phone", num);
		
		given()
			.headers("content-type","application/json")
			.body(obj.toJSONString()).
		when()
			.post("/user").
		then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(enabled = true, priority = 2)
	public void getUser(){
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		given()
			.get("user/muneem").
		then()
			.statusCode(200)
			.log().all();
		
		given()
			.get("user/gargs").
		then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(enabled = true, priority = 3)
	public void loginUser(){
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		given()
			.queryParam("username", "gargs")
			.queryParam("password", "tumhihoe").
		when()
			.get("user/login").
		then()
			.statusCode(200)
			.log().all();
		
		given()
		.queryParam("username", "muneem")
		.queryParam("password", "sabBtaDunTumhein").
	when()
		.get("user/login").
	then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(enabled = true, priority = 4)
	public void modifyUser(){
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		JSONObject obj = new JSONObject();
		obj.put("firstname", "smriti");
		obj.put("email", "sgsmriti121@gmail.com");
		obj.put("phone", "2223334444");
		
		given()
			.headers("content-type","application/json")
			.body(obj.toJSONString()).
		when()
			.put("user/muneem").
		then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(enabled = true, priority = 6)
	public void deleteUser(){
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		
		given()
			.delete("user/muneem").
		then()
			.statusCode(200)
			.log().all();
	}
}
