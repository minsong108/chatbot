import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/*
 * This class makes weather API request
 */

public class Weather {

	/*
	 * This method returns the temperature, low temperature, and high temperature in
	 * farenheit.
	 * The method takes the location as a parameter, which can be either zipcode or city name
	 * entered by the user.
	 */
	
	public double [] getWeather(String location) {
		
		String apiKey = "460c147b4347e3bd278fb803a45da856";		// API key to make request
		
		// temp array holds the returned value
		double [] temp = new double [3];
		
		// weatherUrl holds the website url
		String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?";
		
		// Check whether the location is entered as zipcode or city name
		if (Character.isDigit(location.charAt(0))) {
			
			// location is entered as zipcode
			// add zipcode and apiKey to weatherUrl
			weatherUrl = weatherUrl + "zip=" + location + ",us&appid=" + apiKey + 
					"&units=imperial";
		}
		else {
			
			// location is entered as city name
			// add city name and apiKey to weatherUrl
			weatherUrl = weatherUrl + "q=" + location + "&appid=" + apiKey + "&units=imperial";
		}
		
		try {
			
			// Create the URL object
			URL url = new URL(weatherUrl);
			
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
			
			// Call parseWeather method and store the returned value in temp array
			temp = parseWeather(result.toString());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return temp;
	}

	/*
	 * This method prase the JSON response to java
	 * The method receives JSON response and returns the temperature, low and high temperature
	 * in double array
	 */
	
	public static double [] parseWeather(String response) {
		
		// temp array holds the temperature, low and high temperature in response
		double [] temp = new double [3];
		
		// Create a JSONOBject named responseMap and get response
		JsonObject responseMap = new Gson().fromJson(response.toString(), JsonObject.class);
		
		// Create a JSONObject named mainMap that contains the temperature information
		JsonObject mainMap = new Gson().fromJson(responseMap.get("main").toString(), JsonObject.class);
		
		// Assign temperature of the city to temp[0] by using get
		temp[0] = mainMap.get("temp").getAsDouble();
		
		// Assign min temperature of the city to temp[1] by using get
		temp[1] = mainMap.get("temp_min").getAsDouble();
		
		// Assign max temperature of the city to temp[2] by using get
		temp[2] = mainMap.get("temp_max").getAsDouble();
		
		// return temp array
		return temp;
	}
}
