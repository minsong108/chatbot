import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*
 * This class makes itunes API request to search for music and movie
 */

public class Itunes {

	/*
	 * This function returns the temperature, low temperature, and high temperature in
	 * farenheit.
	 * The function takes the location as a parameter, which can be either zipcode or city name
	 * entered by the user.
	 */
	
	public String [][] getItunes(String entity, String type) {
		
		// 2 dimensional array named content holds 3 different items with each having 5 contents
		String [][] content = new String [3][5];
		
		// itunesUrl holds the website for itunes
		String itunesUrl = "https://itunes.apple.com/search?term=" + entity;
		
		// Check whether the type is whether movie or music
		if (type.equals("movie")) {
			
			// type is movie
			// add the specification and limit of 3 to itunesUrl
			itunesUrl = itunesUrl + "&entity=movie&limit=3";
		}
		else if (type.equals("music")) {
			
			// type is music
			// add limit of 3 response to itunesUrl
			itunesUrl = itunesUrl + "&limit=3";
		}
		
		try {
			// Create the URL object
			URL url = new URL(itunesUrl);
			
			// Create an HTTPURLConnection object
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// Use connection to create GET request
			connection.setRequestMethod("GET");
			
			//StringBuilder result = new StringBuilder();
			BufferedReader read = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			
			String input;
			StringBuffer result = new StringBuffer();
			
			// Convert BufferReader to String and store in a result variable
			while ((input = read.readLine()) != null) {
				result.append(input);
			}
			
			// Close BufferReader
			read.close();
			
			// Close the connection
			connection.disconnect();
			
			// Call parseItunes function to get information and store the returned values
			// in to content array
			
			content = parseItunes(result.toString());
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		

		return content;
	}
	
	/*
	 * This function prase the JSON response to java
	 * The function receives JSON response and returns the kind, artist name, collection name,
	 * preview url, and cover image url
	 */
	
	public static String [][] parseItunes(String response) {
		
		// content array holds kind, artist name, collection name, preview url, and cover image url
		String [][] content = new String [3][5];
		
		// Create JSONObject named responseObj to get content
		JsonObject responseObj = new Gson().fromJson(response.toString(), JsonObject.class);
		
		// Create JSONArray named responseArr to get array in the object
		JsonArray responseArr = responseObj.getAsJsonArray("results");
		
		// Using for loop, get each individual items in the array
		for (int i = 0; i < 3; i++) {
				
			// Create resultObj in the responseArr
			JsonObject resultObj = responseArr.get(i).getAsJsonObject();
			
			// Using the inner for loop to get individual items in the object
			for (int j = 0; j < 5; j++) {

				if (j == 0) {
					
					if (resultObj.get("kind").isJsonNull()) {
						content[i][j] = "Not found";
					}
					else {
						content[i][j] = resultObj.get("kind").getAsString();
					}
					
				}
				else if (j == 1) {
					
					if (resultObj.get("artistName").isJsonNull()) {
						content[i][j] = "Not found";
					}
					else {
						content[i][j] = resultObj.get("artistName").getAsString();
					}
				}
				else if (j == 2) {
					
					if (resultObj.get("collectionName").isJsonNull()) {
						content[i][j] = "Not found";
					}
					else {
						content[i][j] = resultObj.get("collectionName").getAsString();
					}
				}
				else if (j == 3) {
					
					if (resultObj.get("previewUrl").isJsonNull()) {
						content[i][j] = "Not found";
					}
					else {
						content[i][j] = resultObj.get("previewUrl").getAsString();
					}
				}
				else if (j == 4) {
					
					if (resultObj.get("artworkUrl100").isJsonNull()) {
						content[i][j] = "Not found";
					}
					else {
						content[i][j] = resultObj.get("artworkUrl100").getAsString();
					}
				}
			}
		}
		// return content array
		return content;
	}
}
