/*
 * This program connects to irc connect, irc.freenode.net, and displays the weather, menu, and 
 * recipe specified by the user.
 * The program calls 2 APIs, weather and menu/recipe.
 * The user enters in the format specified below and the program displays the weather, menu, or 
 * recipe of the item entered by the user in the browser.
 */

public class BotMain {

	public static void main(String [] args) throws Exception {
		
		// Create the object of Bot
		Bot bot = new Bot();
		
		// Allow debugging output
		bot.setVerbose(true);
		
		// Connect to the server
		bot.connect("irc.freenode.net");
		
		// Join the #NewBot channel
		bot.joinChannel("#NewBot");
		
		// Display the functions that this bot can do
		bot.sendMessage("#NewBot", "There are 3 functions that this bot can do");
		bot.sendMessage("#NewBot", "You can do the following using the format below");
		bot.sendMessage("#NewBot", "Look up for weather - \"weather zipcode/city\"");
		bot.sendMessage("#NewBot", "Look up for menu - \"menu menuName\"");
		bot.sendMessage("#NewBot","Look up for recipe - \"recipe recipeName/menuName\"");
		
	}
}
