/**
 * 
 */
package com.json.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author gslab
 *
 */
public class ReadDataFRomJSON {
	public static void main(String[] args) throws IOException, ParseException {
		
		//Read Firstname and lastname
		
		JSONParser parse=new JSONParser();
		FileReader reader=new FileReader("/home/gslab/Desktop/Files/json/json/employee.json");
		
		Object obj=parse.parse(reader);
		
		JSONObject json=(JSONObject) obj;
		
		String fname=(String)json.get("firstName");
		String lname=(String)json.get("lastName");
		
		System.out.println(fname);
		System.out.println(lname);
		
		JSONArray array=(JSONArray)json.get("address");
		for(int i=0;i<array.size();i++) {
			JSONObject json1=(JSONObject) array.get(i);
			
			String street=(String) json1.get("street");
			String city=(String) json1.get("city");
			String state=(String) json1.get("state");
			
			System.out.println(street);
			System.out.println(city);
			System.out.println(state);
			
			
		}
		
		
	}
}
