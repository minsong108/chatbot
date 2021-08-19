import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * This class makes menu API request to search for recipe
 */

public class Recipe {

	/*
	 * This method returns the 3 different title of the recipe, recipe url, and image url
	 * The method takes recipeItem entered by the user.
	 */
	
	public String [][] getRecipe(String recipeItem) {
		
		// 2 dimensional array named content holds 3 different items with each having 3 contents
		String [][] content = new String [3][3];
		
		// apiKey holds the API key to make request
		String apiKey = "49a44e0077174e078b66fe7875e4e43a";
		
		// idUrl holds the website for menu search
		String idUrl = "https://api.spoonacular.com/recipes/complexSearch?query=" + 
				recipeItem + "&number3&apiKey=" + apiKey;
		
		try {
			// Create the URL object
			URL url = new URL(idUrl);
			
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
			
			// Call parseID to get 3 IDs needed to find recipe'
			// store 3 IDs in recipeID array
			int [] recipeID = parseID(result.toString());
			
			// Using for loop, call recipeInfo method to get information about the entered recipe item
			for (int i = 0; i < recipeID.length; i++) {
				
				for (int j = 0; j < 3; j++) {
					
					// Call recipeInfo method and store the values in content array
					content[i] = recipeInfo(recipeID, i);
				}
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// return content array
		return content;
	}

	/*
	 * This method parse the JSON response to java
	 * The method receives JSON response and returns 3 IDs of the recipe title
	 */
	
	public static int [] parseID(String response) {
		
		// recipeID array holds 3 IDs
		int [] recipeID = new int [3];
		
		// Create JSONObject named idObj to get content
		JsonObject idObj = new Gson().fromJson(response.toString(), JsonObject.class);
		
		// Create a JSONArray named idArr that contains the result of the recipe
		JsonArray idArr = idObj.getAsJsonArray("results");
		
		// Using the for loop, only get the ID
		for (int index = 0; index < recipeID.length; index++) {
			
			// Create JSONObject named resultObj for each result
			JsonObject resultObj = idArr.get(index).getAsJsonObject();
			
			// Get id in the resultObj and store the value into recipeID
			recipeID[index] = resultObj.get("id").getAsInt();
		}
		
		// return recipeID array
		return recipeID;
	}
	
	/*
	 * This method returns the 3 different title of the recipe, recipe url, and image url
	 * The method takes recipeID and index from getRecipe method.
	 */
	
	public static String [] recipeInfo (int [] recipeID, int index) {
		
		// array named content that contains the title, recipe Url, and image Url
		String [] content = new String [3];
		
		// apiKey holds the API key to make request
		String apiKey = "56d78738d1a547229b67e98f3de1742f";
		
		// recipeIrl holds the website for recipe search
		String recipeUrl = "https://api.spoonacular.com/recipes/" + recipeID[index] + "/information?include" +
				"Nutrition=false&number3&apiKey=" + apiKey;
		
		try {
			// Create the URL object
			URL url = new URL(recipeUrl);
			
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

			// Call parseRecipe method to parse JSON to java
			// store the returned values in content
			content = parseRecipe(result.toString(), index);
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// return content array
		return content;
	}
	
	/*
	 * This method parse the JSON response to java
	 * The method receives JSON response and returns title, recipe url (source url), and image url
	 */
	public static String [] parseRecipe(String response, int index) {
		
		// recipeContent array holds title, recipe url (source url), and image url
		String [] recipeContent = new String [3];
		
		// Create JSONObject named recipeObj to get content
		JsonObject recipeObj = new Gson().fromJson(response, JsonObject.class);
		
		// Assign title of the recipe to recipeContent[0] by using get
		recipeContent[0] = recipeObj.get("title").getAsString();
		
		// Assign recipe url of the recipe to recipeContent[1] by using get
		recipeContent[1] = recipeObj.get("sourceUrl").getAsString();
		
		// Assign image url of the recipe to recipeContent[2] by using get
		recipeContent[2] = recipeObj.get("image").getAsString();
		
		// return recipeContent array
		return recipeContent;
	}
}
