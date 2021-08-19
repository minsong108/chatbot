//
//import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URLConnection;
//import java.net.URL;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//
////
////
////
//public class DictionaryGETRequestExample {
////
////public double [] weather(String location) {
////		
////		String apiKey = "460c147b4347e3bd278fb803a45da856";
////		double [] temp = new double [3];
////		String myUrl;
////		
////		if (Character.isDigit(location.charAt(0))) {
////			
////			myUrl = "http://api.openweathermap.org/data/2.5/weather?zip=" + location + ",us&appid="
////					+ apiKey + "&units=imperial";
////		}
////		else {
////			
////			myUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid="
////					+ apiKey + "&units=imperial";
////		}
////		
////		try {
////			StringBuilder result = new StringBuilder();
////			URL url = new URL(myUrl);
////			URLConnection conn = url.openConnection();
////			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////			String line;
////			
////			while ((line = rd.readLine()) != null) {
////				result.append(line);
////			}
////			
////			rd.close();
////			
////			temp = parseWeather(result.toString());
////			
////		} catch (IOException e) {
////			System.out.println(e.getMessage());
////		}
////		
////		return temp;
////	}
////
////	public static double [] parseWeather(String response) {
////		
////		double [] temp = new double [3];
////		JSONObject respMap = new JSONObject(response.toString());
////		JSONObject mainMap = new JSONObject(respMap.get("main").toString());
////		
////		temp[0] = mainMap.getDouble("temp");
////		temp[1] = mainMap.getDouble("temp_min");
////		temp[2] = mainMap.getDouble("temp_max");
////		
////		return temp;
////	}
////	
//////	public static void main(String [] args) {
//////		
//////		String apiKey = "460c147b4347e3bd278fb803a45da856";
//////		String location;
//////		double temp;
//////		String myUrl;
//////		
//////		Scanner input = new Scanner(System.in);
//////		
//////		System.out.print("Enter the city name: ");
//////		location = input.next();
//////		
//////		location.replaceAll("\\W", "");
//////		
//////		if (Character.isDigit(location.charAt(0))) {
//////			
//////			myUrl = "http://api.openweathermap.org/data/2.5/weather?zip=" + location + ",us&appid="
//////					+ apiKey + "&units=imperial";
//////		}
//////		else {
//////			
//////			myUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid="
//////					+ apiKey + "&units=imperial";
//////		}
//////		
//////		try {
//////			StringBuilder result = new StringBuilder();
//////			URL url = new URL(myUrl);
//////			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//////	
//////			// Request setup
//////			connection.setRequestMethod("GET");
//////			connection.setConnectTimeout(5000);
//////			connection.setReadTimeout(5000);
//////			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//////			String line;
//////			
//////			while ((line = rd.readLine()) != null) {
//////				result.append(line);
//////			}
//////			
//////			rd.close();
//////			
//////			temp = parse(result.toString());
//////			
//////			System.out.print("Temperature at " + location + " is " + temp);
//////			
//////		} catch (IOException e) {
//////			System.out.println(e.getMessage());
//////		}
//////	}
//////	
//////	public static double parse(String response) {
//////		
//////		JSONObject respMap = new JSONObject(response.toString());
//////		JSONObject mainMap = new JSONObject(respMap.get("main").toString());
//////		double temp = mainMap.getDouble("temp");
//////		
//////		return temp;
//////	}
////	
////	public static void main(String [] args) {
////		
////		Scanner input = new Scanner(System.in);
////		
////		System.out.print("Enter the artist's name: ");
////		String userInput = input.nextLine();
////		
////
////		String entity = "vixx";
////		String [] words = userInput.split(" ");
////		String [] entityArr = new String[10];
////		
////		int wordCount = 0;
////		
////		for (int i = 1; i < words.length; i++) {
////			
////			if (words[0].equals("music")) {
////				
////				entityArr[i -1] = words[i];
////				//entityArr[i] = words[i];
////				wordCount++;
////			}
////			
////		}
////		
////		System.out.println(entityArr[0]);
////		System.out.println(entityArr[1]);
////		
////		if (wordCount > 0) {
////			
////			for (int i = 0; i < wordCount; i++) {
////				if (i == 0) {
////					entity = entityArr[i];
////				}
////				else {
////					entity += "+" + entityArr[i];
////				}
////			}
////		}
////		
////		System.out.println(entity);
////		System.out.println();
////		
////		String myUrl = "https://itunes.apple.com/search?term=" + entity + "&limit=3";
////		
////		try {
////			
////			URL url = new URL(myUrl);
////			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
////			
////			// Request setup
////			connection.setRequestMethod("GET");
////			connection.setConnectTimeout(5000);
////			connection.setReadTimeout(5000);
////			
////			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////			String line;
////			
////			StringBuilder result = new StringBuilder();
////			
////			while ((line = rd.readLine()) != null) {
////				result.append(line);
////			}
////			
////			System.out.println(result);
////			
////			rd.close();
////			
////			String [][] array = parse(result.toString());
////			
////			for (int i = 0; i < 3; i++) {
////
////				for (int j = 0; j < 5; j++) {
////					System.out.println(array[i][j]);
////				}
////			}
////			
////		} catch (IOException e) {
////			System.out.println(e.getMessage());
////		}
////	}
////	
////	public static String [][] parse(String response) {
////		
////		String array [][] = new String [3][5];
////		
////		JsonObject obj1 = new Gson().fromJson(response, JsonObject.class);
////		
////		JsonArray results = obj1.getAsJsonArray("results");
////		
////		for (JsonElement object : results) {
////			
////			for (int i = 0; i < 3; i++) {
////				
////				for (int j = 0; j < 5; j++) {
////					
////					JsonObject obj = object.getAsJsonObject();
////					array[i][0] = obj.get("kind").getAsString();
////					array[i][1] = obj.get("artistName").getAsString();
////					array[i][2] = obj.get("collectionName").getAsString();
////					array[i][3] = obj.get("previewUrl").getAsString();
////					array[i][4] = obj.get("artworkUrl100").getAsString();
////				}
////				
////			}
////	}
////for (JsonElement object : results) {
////			
////			// Create resultObj in the responseArr
////			JsonObject resultObj = object.getAsJsonObject();
////			
////			for (int i = 0; i < 3; i++) {
////				
////				
////				
////				// Using the inner for loop to get individual items in the object
////				for (int j = 0; j < 5; j++) {
////
////					if (j == 0) {
////						
////						if (resultObj.get("kind").isJsonNull()) {
////							array[i][j] = "Not found";
////						}
////						else {
////							array[i][j] = resultObj.get("kind").getAsString();
////						}
////						
////					}
////					else if (j == 1) {
////						
////						if (resultObj.get("artistName").isJsonNull()) {
////							array[i][j] = "Not found";
////						}
////						else {
////							array[i][j] = resultObj.get("artistName").getAsString();
////						}
////					}
////					else if (j == 2) {
////						
////						if (resultObj.get("collectionName").isJsonNull()) {
////							array[i][j] = "Not found";
////						}
////						else {
////							array[i][j] = resultObj.get("collectionName").getAsString();
////						}
////					}
////					else if (j == 3) {
////						
////						if (resultObj.get("previewUrl").isJsonNull()) {
////							array[i][j] = "Not found";
////						}
////						else {
////							array[i][j] = resultObj.get("previewUrl").getAsString();
////						}
////					}
////					else if (j == 4) {
////						
////						if (resultObj.get("artworkUrl100").isJsonNull()) {
////							array[i][j] = "Not found";
////						}
////						else {
////							array[i][j] = resultObj.get("artworkUrl100").getAsString();
////						}
////					}
////				}
////			}
////		}
////			
//			
////			System.out.println("kind: " + type);
////			System.out.println("artist: " + artist);
////			System.out.println("collection: " + collection);
////			System.out.println("previewUrl: " + previewUrl);
////			System.out.println("album cover: " + image);
////			System.out.println();
////		
////		return array;
////	}
////}
//////	
//	public static void main(String [] args) {
//		
//		String myUrl = "https://api.spoonacular.com";
//		String apiKey = "96f5ecb6f8c14e04bc798c2c6188dcaa";
//		int [] recipeID = new int [3];
//		
//		Scanner input = new Scanner(System.in);
//		
//		System.out.print("Enter the dish/recipe name: ");
//		String userInput = input.nextLine();
//		
//		
////		userInput = userInput.replaceAll("[\\W]", "");
////		userInput = userInput.replaceAll(" ", "+");
//		
//		String [] words = userInput.split(" ");
//		
//		if (userInput.contains("menu")) {
//			
//			myUrl = "https://api.spoonacular.com/food/menuItems/search?query=" + 
//					 words[1] + "&number3&apiKey=" + apiKey;
//		}
//		else if (userInput.contains("recipe")) {
//			
//			myUrl = "https://api.spoonacular.com/recipes/complexSearch?query=" + 
//					words[1] + "&number3&apiKey=" + apiKey;
//		}
//		
//		
//		
//		try {
//			StringBuilder result = new StringBuilder();
//			URL url = new URL(myUrl);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			
//			// Request setup
//			connection.setRequestMethod("GET");
//			connection.setConnectTimeout(5000);
//			connection.setReadTimeout(5000);
//			
//			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			String line;
//			
//			while ((line = rd.readLine()) != null) {
//				result.append(line);
//			}
//			
//			rd.close();
//			
//			
//			
//			if (userInput.contains("menu")) {
//				
//				System.out.println(result);
//				
//				parseMenu(result.toString());
//			}
//			else if (userInput.contains("recipe")) {
//				
//				recipeID = parseID(result.toString());
//				System.out.println(result);
//
//				for (int i = 0; i < 3; i++) {
//					
//					recipeInfo(recipeID, i);
//				}
//			}
//			
//			
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public static void parseMenu(String response) {
//	
//	JSONObject obj1 = new JSONObject(response);
//	JSONArray objArr = new JSONArray(obj1.get("menuItems").toString());
//	
//	String servingSize;
//	
//	for (int i = 0; i < 3; i++) {
//		
//		JSONObject obj = objArr.getJSONObject(i);
//		String title = obj.getString("title");
//		String restaurant = obj.getString("restaurantChain");
//		
//		if (obj.isNull("servingSize")) {
//			servingSize = "Not found";
//		}
//		else {
//			servingSize = obj.getString("servingSize").toString();
//		}
//		
//		String imageUrl = obj.getString("image");
//		
//		
//		System.out.println("title: " + title);
//		System.out.println("restaurant: " + restaurant);
//		System.out.println("serving size: " + servingSize);
//		System.out.println("image URL: " + imageUrl);
//		System.out.println();
//		}
//	
//	}
//////	
//////	
//////	public static int [] parseID(String response) {
//////		
//////		JSONObject obj1 = new JSONObject(response);
//////		JSONArray objArr = new JSONArray(obj1.get("results").toString());
//////		
//////		int [] recipeID = new int [3];
//////		
//////		for (int i = 0; i < 3; i++) {
//////			JSONObject obj = objArr.getJSONObject(i);
//////			
//////			recipeID[i] = obj.getInt("id");
//////		}
//////		
//////		return recipeID;
//////	}
//////	
//////	public static void recipeInfo (int [] recipeID, int index) {
//////		
//////		String myUrl = "https://api.spoonacular.com";
//////		String apiKey = "96f5ecb6f8c14e04bc798c2c6188dcaa";
//////		
//////		myUrl = "https://api.spoonacular.com/recipes/" + recipeID[index] + "/information?include" +
//////				"Nutrition=false&number3&apiKey=" + apiKey;
//////		
//////		try {
//////		
//////			StringBuilder recipeResult = new StringBuilder();
//////			URL idUrl = new URL(myUrl);
//////			HttpURLConnection conn = (HttpURLConnection) idUrl.openConnection();
//////			
//////			// Request setup
//////			conn.setRequestMethod("GET");
//////			conn.setConnectTimeout(5000);
//////			conn.setReadTimeout(5000);
//////			
//////			BufferedReader idRd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//////			String idLine;
//////			
//////			while ((idLine = idRd.readLine()) != null) {
//////				recipeResult.append(idLine);
//////			}
//////			
//////			idRd.close();
//////
//////			parseRecipe(recipeResult.toString());
//////		
//////		} catch (IOException e) {
//////			System.out.println(e.getMessage());
//////		}
//////		
//////	}
//////	
//////	public static void parseRecipe(String response) {
//////		
//////		JSONObject obj1 = new JSONObject(response);
//////		
//////		String title = obj1.getString("title");
//////		String recipeUrl = obj1.getString("sourceUrl");
//////		String imageUrl = obj1.getString("image");
//////		String summary = obj1.getString("summary");
//////		
//////		System.out.println();
//////		System.out.println();
//////		System.out.println(title);
//////		System.out.println(recipeUrl);
//////		System.out.println(imageUrl);
//////		System.out.println(summary);
//////
//////		
//////		}
////}
