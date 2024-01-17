package com.test.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.test.endpoints.UserEndpoints;
import com.test.payloads.User;
import com.test.utility.Utilities;

import io.restassured.response.Response;

public class UserTest {

	User payload;
	Utilities util;
	Faker faker;
	String sheetname="Sheet1";     
	String sheetname2="Sheet2";
	@BeforeClass
	public void setUp() {
		payload=new User();
		 util=new Utilities();
//		faker=new Faker();
//		payload.setId(faker.idNumber().hashCode());
//		payload.setUsername(faker.name().username());
//		payload.setFirstName(faker.name().firstName());
//		payload.setLastName(faker.name().lastName());
//		payload.setEmail(faker.internet().safeEmailAddress());
//		payload.setPassword(faker.internet().password(5, 10));
//		payload.setPhone(faker.phoneNumber().cellPhone());
	}
	@DataProvider(name = "getdata")
	public Object[][] getData() throws Exception{
		Object[][] data=util.getExcelData(sheetname);
		return data;
	}
	@Test(priority=1,dataProvider = "getdata")
	public void create_user(String id,String username,String firstname,String lastname,String email,String password,String phone,String userstatus) {
		// payload=new User();
		 payload.setId(Integer.parseInt(id));
		 payload.setUsername(username);
		 payload.setFirstName(firstname);
		 payload.setLastName(lastname);
		 payload.setEmail(email);
		 payload.setPassword(password);
		 payload.setPhone(phone);
		 payload.setUserStatus(0);
			
		Response response=UserEndpoints.create_user(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@DataProvider(name = "getUsername")
	public Object[][] getUser() throws IOException{
		Object[][] data=util.getusername();
		return data;
	}
	
	@Test(priority = 2, dataProvider = "getUsername")
	public void get_user(String username) {
		Response response=UserEndpoints.get_user(username);
		System.out.println("username name is "+username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@DataProvider(name = "update")
	public Object[][] upDate() throws Exception{
		Object[][] data=util.getExcelData(sheetname2);
		return data;
	}
	@Test(priority = 3,dataProvider = "upDate")
	public void update_user(String id,String username,String firstname,String lastname,String email,String password,String phone,String userstatus) {
		 payload=new User();
		 
		 payload.setId(Integer.parseInt(id));
		 payload.setUsername(username);
		 payload.setFirstName(firstname);
		 payload.setLastName(lastname);
		 payload.setEmail(email);
		 payload.setPassword(password);
		 payload.setPhone(phone);
		 payload.setUserStatus(0);
		
		Response response=UserEndpoints.update_user(username, payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@DataProvider(name = "deleteData")
	public Object[][] deletedata() throws IOException{
		Object[][] data=util.getusername();
		return data;
	}
	@Test(priority = 4,dataProvider = "deleteData")
	public void delete_user(String username) {
		Response response=UserEndpoints.delete_user(username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
