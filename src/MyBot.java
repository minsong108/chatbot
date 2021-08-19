//import org.jibble.pircbot.*;
//
//public class MyBot extends PircBot {
//	
//	public MyBot() {
//		this.setName("ClaireSongBot");
//	}
//	
//	/*
//	 * onMessage function sends the message to user
//	 * It takes channel, sender, login, and hostname, and message in pararmeter
//	 * The function does not return a value
//	 */
//	
//	public void onMessage(String channel, String sender, String login, String hostname, 
//				String message) {
//		
//		// Check whether the user input includes "hello" or "hi" ignoring the case
//		if (message.equalsIgnoreCase("Hello") || message.equalsIgnoreCase("Hi")) {
//			
//			// Send hello with their name
//			sendMessage(channel, "Hi " + sender);
//		}
//		// Check whether the user input includes weather
//		else if(message.contains("weather")) {
//			
//			// location holds the location that user wants to know the weather of
//			String location = "allen";
//			
//			// Using message.split, separate "weather" and location that user entered
//			String [] userInputs = message.split(" ");
//			
//			// Using the for loop, find the location that user entered
//			for (int i = 0; i < userInputs.length; i++) {
//				
//				if (userInputs[i].equals("weather")) {
//					
//					// store the location that user entered into variable location
//					location = userInputs[i + 1];
//				}
//				else if (userInputs[i].equals("in")) {
//					
//					// store the location that user entered into variable location
//					location = userInputs[i+1];
//				}
//			}
//			
//			// Create a weather instance
//			Weather weather = new Weather();
//			
//			// Call getWeather function and store the returned double value in
//			// variable temperature
//			// temperature[0] = temperature in the location
//			// temperature[1] = low temperature
//			// temperature[2] = high temperature
//			double [] temperature = weather.getWeather(location);
//			
//			// Send message with temperature information
//			sendMessage(channel, "Weather at " + location + " is " + temperature[0] + " degree " +
//						"with a low of " + temperature[1] + " degree and a high of " +
//						temperature[2] + " degree");
//			
//		}
//		// Check if it user input contains a word "music" or "movie"
//		else if (message.contains("music") || message.contains("movie")){
//			
//			// Variable entity holds the user input that user want to search for
//			String entity = "vixx";
//			
//			// entityArr holds the value that user want to search for
//			// It is array because user could enter people's name with space
//			String [] entityArr = new String[10];
//			
//			// Using message.split, to separate keywords and location that user entered
//			String [] userInput = message.split(" ");
//			
//			// wordCount tracks the number of words in the user input excluding the keyword
//			int wordCount = 0;
//			
//			// Using the for loop, store the user input in to entityArr starting from 
//			// index 1 because index 0 contains the keyword
//			for (int i = 1; i < userInput.length; i++) {
//				
//				if (userInput[0].equals("music") || userInput[0].equals("movie")) {
//					
//					entityArr[i -1] = userInput[i];
//					
//					// Increment wordCount
//					wordCount++;
//				}
//			}
//			
//			// Using the for loop, store the words in entityArr to entity and connect the words
//			// using '+' sign
//			for (int i = 0; i < wordCount; i++) {
//				
//				if (i == 0) {
//					entity = entityArr[i];
//				}
//				else {
//					entity += "+" + entityArr[i];
//				}
//			}
//			
//			// store the keyWord (music or movie) in the variable keyword
//			String keyWord = userInput[0];
//			
//			// Create a Itunes instance named itunes
//			Itunes itunes = new Itunes();
//			
//			// Call getItunes to get output items and store the values in
//			// 2 dimensional array named content
//			String itunesContent [][] = itunes.getItunes(entity.toString(), keyWord);
//			
//			// variable number holds the item number for each section
//			String number;
//			
//			// Using for loop, display the output
//			for (int i = 0; i < itunesContent.length; i++) {
//				
//				// Using for loop,display the output for each items in a section
//				for (int j = 0; j < itunesContent[i].length; j++) {
//					
//					if (j == 0) {
//						
//						// Assign number and parse int to string
//						number = Integer.toString(i + 1);
//						
//						// Display number
//						sendMessage(channel, number);
//						
//						// Display the type of work, such as music and movie
//						sendMessage(channel, "Type of work: " + itunesContent[i][j]);
//					}
//					else if (j == 1) {
//						
//						// Display the artist or actor name
//						sendMessage(channel, "Artist name: " + itunesContent[i][j]);
//					}
//					else if (j == 2) {
//						
//						// Display the movie or music title
//						sendMessage(channel, "Collection name: " + itunesContent[i][j]);
//					}
//					else if (j == 3) {
//						
//						// Display the preview URL if there is
//						sendMessage(channel, "Preview URL: " + itunesContent[i][j]);
//					}
//					else if (j == 4) {
//						
//						// Display the cover image of album or movie
//						sendMessage(channel, "Cover image URL: " + itunesContent[i][j]);
//					}
//				} // end of inner for loop
//			} // end of outer for loop
//		}
//		// Check if the user input contains "menu"
//		else if (message.contains("menu")) {
//			
//			// menuItem holds the menu that user want to search for
//			String menuItem = "pasta";
//			
//			// Using message.split, to separate keyword and menu that user entered
//			String [] menuArr = message.split(" ");
//			
//			// Assign the menu item that user wants to search in the variable menuItem
//			menuItem = menuArr[1];
//			
//			// Create an instance of Menu class
//			Menu menu = new Menu();
//			
//			// get output values by calling getMenu function and store returned value
//			// in 2 dimensional array named menuContent
//			String menuContent [][] = menu.getMenu(menuItem.toString());
//			
//			// menuNumber holds the section number
//			String menuNumber;
//			
//			// Using the for loop, display the content in menuContent array
//			for (int i = 0; i < menuContent.length; i++) {
//				
//				// Display the menu number by parsing from int to string
//				menuNumber = Integer.toString(i + 1);
//				sendMessage(channel, menuNumber);
//				
//				// Using the for loop, display each content in section
//				for (int j = 0; j < menuContent[i].length; j++) {
//					
//					if (j == 0) {
//						
//						// Display the title of menu
//						sendMessage(channel, "Title of menu: " + menuContent[i][j]);
//					}
//					else if (j == 1) {
//						
//						// Display the restaurant name that has that menu
//						sendMessage(channel, "Restaurant name: " + menuContent[i][j]);
//					}
//					else if (j == 2) {
//						
//						// Display the serving size
//						sendMessage(channel, "Serving Size: " + menuContent[i][j]);
//					}
//					else if (j == 3) {
//						
//						// Display the image Url
//						sendMessage(channel, "Image URL: " + menuContent[i][j]);
//					}
//				} // end of inner for loop
//			} // end of outer for loop
//		}
//		// Check if the user input contains "recipe"
//		else if (message.contains("recipe")) {
//			
//			// recipeItem holds the user input value that user want to search
//			String recipeItem = "pasta";
//			
//			// Using message.split, to separate keyword and recipe that user entered
//			String [] recipeArr = message.split(" ");
//			
//			// Store the plate name that is in recipeArr[1] to variable recipeItem
//			recipeItem = recipeArr[1];
//			
//			// Create an instance of Recipe class
//			Recipe recipe = new Recipe();
//			
//			// get recipe information by calling getRecipe function
//			// store the returned value in 2 dimensional array named recipeContent
//			String recipeContent [][] = recipe.getRecipe(recipeItem.toString());
//			
//			// recipe Number holds the number of recipe displayed
//			String recipeNumber;
//			
//			for (int i = 0; i < recipeContent.length; i++) {
//				
//				// Display the recipe number by parsing from int to string
//				recipeNumber = Integer.toString(i + 1);
//				sendMessage(channel, recipeNumber);
//				
//				// Using the for loop, display the recipe information
//				for (int j = 0; j < 3; j++) {
//					
//					if (j == 0) {
//						
//						// Display the title of menu
//						sendMessage(channel, "Title of menu: " + recipeContent[i][j]);
//					}
//					else if (j == 1) {
//						
//						// Display the recipe Url
//						sendMessage(channel, "Recipe Url: " + recipeContent[i][j]);
//					}
//					else if (j == 2) {
//						
//						// Display the image Url
//						sendMessage(channel, "Image URL: " + recipeContent[i][j]);
//					}
//				}// end of inner loop
//			} // end of outer for loop
//		}
//	}
//}