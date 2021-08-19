/*
 * This class reads and send the message to the user.
 * When the user includes weather in the message, the program displays the weather of the location.
 * When the user includes menu in the message, the program displays 3 menus, including menu name,
 * restaurant name, serving size, and image URL, of the specified item.
 * When the user includes recipe in the message, the program displays 3 recipes, including the
 * title of the menu, recipe URL, and image URL.
 */

import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {
	
	public Bot() {
		this.setName("NewBot");
	}
	
	/*
	 * onMessage function sends the message to user
	 * It takes channel, sender, login, and hostname, and message in pararmeter
	 * The function does not return a value
	 */
	
	public void onMessage(String channel, String sender, String login, String hostname, 
				String message) {
		
		// Check whether the user input includes "hello" or "hi" ignoring the case
		if (message.equalsIgnoreCase("Hello") || message.equalsIgnoreCase("Hi")) {
			
			// Send hello with their name
			sendMessage(channel, "Hi " + sender);
		}
		// Check whether the user input includes weather
		else if(message.contains("weather")) {
			
			// location holds the location that user wants to know the weather of
			String location = "allen";
			
			// Using message.split, separate "weather" and location that user entered
			String [] userInputs = message.split(" ");
			
			// Using the for loop, find the location that user entered
			for (int i = 0; i < userInputs.length; i++) {
				
				if (userInputs[i].equals("weather")) {
					
					// store the location that user entered into variable location
					location = userInputs[i + 1];
				}
				else if (userInputs[i].equals("in")) {
					
					// store the location that user entered into variable location
					location = userInputs[i+1];
				}
			}
			
			// Create a weather instance
			Weather weather = new Weather();
			
			// Call getWeather function and store the returned double value in
			// variable temperature
			// temperature[0] = temperature in the location
			// temperature[1] = low temperature
			// temperature[2] = high temperature
			double [] temperature = weather.getWeather(location);
			
			// Send message with temperature information
			sendMessage(channel, "Weather in " + location + " is " + temperature[0] + " degree " +
						"with a low of " + temperature[1] + " degree and a high of " +
						temperature[2] + " degree");
		}
		// Check if the user input contains "menu"
		else if (message.contains("menu")) {
			
			// menuItem holds the menu that user want to search for
			String menuItem = "0";
			
			// Using message.split, to separate keyword and menu that user entered
			String [] menuArr = message.split(" ");
			
			// Assign the menu item that user wants to search in the variable menuItem
			for (int i = 0; i < menuArr.length; i++) {
				
				// Check whether the user entered "menu"
				if (menuArr[i].equals("menu")) {
					
					// Using the for loop, store the specified menu into menuItem
					for (int j = i + 1; j < menuArr.length; j++) {
						
						// Check whether the word is the first word or not
						if (j == i + 1) {
							
							// Store the menu that user entered into variable menuItem
							menuItem = menuArr[j];
						}
						else {
							
							// Add to menuItem by using + sign
							menuItem += "+" + menuArr[j];
						}
					}
				}
				// Check whether the user entered a sentence including "for"
				else if (menuArr[i].equals("for")) {
					
					// Using the for loop, store the specified menu into menuItem
					for (int j = i + 1; j < menuArr.length; j++) {
						
						// Check whether the word is the first word or not
						if (j == i + 1) {
							
							// Store the menu that user entered into variable menuItem
							menuItem = menuArr[j];
						}
						else {
							
							// Add to menuItem by using + sign
							menuItem += "+" + menuArr[j];
						}
					}
				}
			} // end of outer for loop
			
			// Create an instance of Menu class
			Menu menu = new Menu();
			
			// get output values by calling getMenu function and store returned value
			// in 2 dimensional array named menuContent
			String menuContent [][] = menu.getMenu(menuItem.toString());
			
			// menuNumber holds the section number
			String menuNumber;
			
			// Using the for loop, display the content in menuContent array
			for (int i = 0; i < menuContent.length; i++) {
				
				// Display the menu number by parsing from int to string
				menuNumber = Integer.toString(i + 1);
				sendMessage(channel, menuNumber);
				
				// Using the for loop, display each content in section
				for (int j = 0; j < menuContent[i].length; j++) {
					
					if (j == 0) {
						
						// Display the title of menu
						sendMessage(channel, "Title of menu: " + menuContent[i][j]);
					}
					else if (j == 1) {
						
						// Display the restaurant name that has that menu
						sendMessage(channel, "Restaurant name: " + menuContent[i][j]);
					}
					else if (j == 2) {
						
						// Display the serving size
						sendMessage(channel, "Serving Size: " + menuContent[i][j]);
					}
					else if (j == 3) {
						
						// Display the image Url
						sendMessage(channel, "Image URL: " + menuContent[i][j]);
					}
				} // end of inner for loop
			} // end of outer for loop
		}
		// Check if the user input contains "recipe"
		else if (message.contains("recipe")) {
			
			// recipeItem holds the user input value that user want to search
			String recipeItem = "pasta";
			
			// Using message.split, to separate keyword and recipe that user entered
			String [] recipeArr = message.split(" ");
			
			// Store the plate name that is in recipeArr[i] to variable recipeItem
			for (int i = 0; i < recipeArr.length; i++) {
				
				if (recipeArr[i].equals("recipe")) {
					
					// Using the for loop, store the specified recipe into recipeItem
					for (int j = i + 1; j < recipeArr.length; j++) {
						
						// Check whether the word is the first word or not
						if (j == i + 1) {
							
							// Store the recipe that user entered into variable recipeItem
							recipeItem = recipeArr[j];
						}
						else {
							
							// Add to menuItem by using + sign
							recipeItem += "+" + recipeArr[j];
						}
					}
				}
				else if (recipeArr[i].equals("for")) {
					
					// Using the for loop, store the specified menu into menuItem
					for (int j = i + 1; j < recipeArr.length; j++) {
						
						// Check whether the word is the first word or not
						if (j == i + 1) {
							
							// Store the menu that user entered into variable menuItem
							recipeItem = recipeArr[j];
						}
						else {
							
							// Add to menuItem by using + sign
							recipeItem += "+" + recipeArr[j];
						}
					}
				}
			}
			
			// Create an instance of Recipe class
			Recipe recipe = new Recipe();
			
			// get recipe information by calling getRecipe function
			// store the returned value in 2 dimensional array named recipeContent
			String recipeContent [][] = recipe.getRecipe(recipeItem.toString());
			
			// recipe Number holds the number of recipe displayed
			String recipeNumber;
			
			for (int i = 0; i < recipeContent.length; i++) {
				
				// Display the recipe number by parsing from int to string
				recipeNumber = Integer.toString(i + 1);
				sendMessage(channel, recipeNumber);
				
				// Using the for loop, display the recipe information
				for (int j = 0; j < 3; j++) {
					
					if (j == 0) {
						
						// Display the title of menu
						sendMessage(channel, "Title of menu: " + recipeContent[i][j]);
					}
					else if (j == 1) {
						
						// Display the recipe Url
						sendMessage(channel, "Recipe Url: " + recipeContent[i][j]);
					}
					else if (j == 2) {
						
						// Display the image Url
						sendMessage(channel, "Image URL: " + recipeContent[i][j]);
					}
				}// end of inner loop
			} // end of outer for loop
		}
	}
}