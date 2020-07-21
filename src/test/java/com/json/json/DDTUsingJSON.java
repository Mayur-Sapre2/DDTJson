/**
 * 
 */
package com.json.json;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author gslab
 *
 */
public class DDTUsingJSON {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@Test(dataProvider = "dp")
	void login(String data) {
		String users[]=data.split(",");
		driver.get("https://www.nopcommerce.com/login");
		driver.findElement(By.id("Username")).sendKeys(users[0]);
		driver.findElement(By.id("Password")).sendKeys(users[1]);
		driver.findElement(By.xpath("//*[@value='Log in']")).click();
		
	}
	
	@AfterClass
	void teardown() {
		driver.close();
	}
	
	@DataProvider(name="dp")
	public String[] readJson() throws IOException, ParseException {
		
		JSONParser parse=new JSONParser();
		FileReader reader=new FileReader("/home/gslab/Desktop/Files/json/json/testdata.json");
		
		Object obj=parse.parse(reader);
		
		JSONObject json=(JSONObject) obj;
		JSONArray arr=(JSONArray)json.get("userlogins");
		
		String arr1[]=new String[arr.size()];
		
		for(int i=0;i<arr.size();i++) {
			JSONObject users=(JSONObject)arr.get(i);
			String username=(String) users.get("username");
			String password= (String) users.get("password");
			
			arr1[i]=username+","+password;
			
		}
		return arr1;
		
	}
	
	
}
