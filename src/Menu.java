import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * This class makes menu API request to search for menu
 */

public class Menu {
	
	/*
	 * This method returns the 3 different title of the menu, restaurant name, serving size, and image Url.
	 * The method takes menuItem entered by the user.
	 */

	public String [][] getMenu(String menuItem) {
		
		// 2 dimensional array named content holds 3 different items with each having 5 contents
		String [][] content = new String [3][4];
		
		// apiKey holds the API key to make request
		String apiKey = "49a44e0077174e078b66fe7875e4e43a";
		
		// menuUrl holds the website for itunes
		String menuUrl = "https://api.spoonacular.com/food/menuItems/search?query=" + 
					menuItem + "&number3&apiKey=" + apiKey;
		
		try {
			// Create the URL object
			URL url = new URL(menuUrl);
			
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
						
			// Call parseMenu method to get information and store the returned values
			// in to content array
			content = parseMenu(result.toString());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// return content array
		return content;
	}
	
	/*
	 * This method prase the JSON response to java
	 * The method receives JSON response and returns the title, restaurant chain, serving size,
	 * and image url
	 */
	
	public static String [][] parseMenu(String response) {
		
		// content array holds title, restaurant chain, serving size, and image url
		String [][] content = new String [3][4];
		
		// Create JSONObject named responseObj to get content
		JsonObject responseObj = new Gson().fromJson(response.toString(), JsonObject.class);
		
		// Create JSONArray named responseArr to get menuItems
		JsonArray responseArr = responseObj.getAsJsonArray("menuItems");
			
		// Using for loop, get each individual items in the array
		for (int i = 0; i < 3; i++) {
			
			// Create resultObj in the responseArr
			JsonObject resultObj = responseArr.get(i).getAsJsonObject();
			
			// Using the inner for loop to get individual items in the object
			for (int j = 0; j < 4; j++) {
				
				if (j == 0) {
					
					// Assign the title of menu into content array
					content[i][j] = resultObj.get("title").getAsString();
				}
				else if (j == 1) {
					
					// Assign the name of the restaurant into content array
					content[i][j] = resultObj.get("restaurantChain").getAsString();
				}
				else if (j == 2) {
					
					// Check if "servingSize" is null or not
					if (resultObj.get("servingSize").isJsonNull()) {
						
						// Assign "Not found" to content
						content[i][j] = "Not found";
					}
					else {
						
						// "servingSize" found
						// Assign the content of servingSize
						content[i][j] = resultObj.get("servingSize").getAsString().toString();
					}
				}
				else if (j == 3) {
					
					// Assign the imageUrl to content
					content[i][j] = resultObj.get("image").getAsString();
				}
			} // end of inner for loop

		} // end of outer for loop
		
		// return content array
		return content;
	}
}
