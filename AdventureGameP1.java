/* In this story mode game you will choose 3 items to take with you
   on your journey. You have 3 unique story branches to select from, 
   ranging in difficulty. Throughout these paths you are given the 
   option to swap items or an item or add items or an item. 
   Every choice determines your fate; as the name suggests, 
   you are gambling on your gear and your instincts to survive.  
*/


import java.util.Scanner;

public class AdventureGameP1{
   public static void main(String[] args){
      Scanner in = new Scanner(System.in);
      System.out.print("-------------------- The Gear Gambler ----------");
      System.out.print("----------");
      
      int count = 0; // Starts empty inv
      
      System.out.print("\n\nWelcome, this is an adventure game where you ");
      System.out.print("\nchoose 3 pieces of gear at the start.");
      System.out.print(" You then \nenter one of three play-throughs where your ");
      System.out.print("\ngear choices decide whether you survive or not!");
      System.out.print("\nHave fun and choose wisely!");
      System.out.print("\n\nPS: Press ENTER/RETURN to advance in the story.");
      in.nextLine();
      
      boolean playing = true;
      while (playing){
         String[] inventory = new String[3]; // new user inv everytime
         nextScene(in, "Choose 3 pieces of gear:\n\n  1. Old Canteen\n  2. Dry Matches\n" +
                   "  3. Rusty Machete\n  4. Bear Bell\n  5. Sleeping Bag\n  6. Pepper Spray");
         System.out.print("\nEnter your 3 choices (ex: 1 2 4): ");
         selectGear(in, inventory);
         count = inventory.length; // added this bc we have filled our bag also maybe to prevent bugs
         System.out.print("Gear Selected: \n\n");
         printInventory(inventory, count);
         System.out.print("\nPress ENTER to start game: ");
         in.nextLine();
         printDivider();
         System.out.println();

         nextScene(in, "Which pathway do you want to take?\n");
         System.out.println("A) The 'Shortcut'");
         System.out.println("B) The 'Deep Forest'");
         System.out.println("C) The 'River Rapids'\n");
         System.out.print("Enter option: ");
         String branchChoice = getValidInput(in, new String[] {"A", "B", "C"});
         printDivider();
         
         // lived or died in the method so we loop back and play again
         // middle man for the program make it easier
         boolean survived = true;
         
         if (branchChoice.equals("A")) {
         survived = theShortcutB1(in, inventory, count);
         }
         else if (branchChoice.equals("B")) {
            survived = deepForestB2(in, inventory, count);
         }
         else{
            survived = riverRapidsB3(in, inventory, count);
         }
         // this essentially calls the branch you chose
         // and puts you through that playthrough 
         
         // game over/restart logic
         if (!survived) {
            System.out.print("Game Over. Would you like to try again? (Y/N): ");
         } 
         else {
            System.out.print("You survived! Play again? (Y/N): ");
         }
            String retry = getValidInput(in, new String[]{"Y", "N"});
         if (retry.equals("N")) {
            playing = false;
            System.out.println("\nThanks for playing The Gear Gambler!");
            printDivider();
         }  
      }
   }
   
 
   // method for gear selection as we want to make sure they
   // only choose 1-6 and nothing else plus label what 1-6 is
   // number like 2 what it means within the array
   public static void selectGear(Scanner in, String[] inventory){
      while(true){
         String rawGearInput = in.nextLine().trim();
         String[] userGear = rawGearInput.split("\\s+");
        
         if(userGear.length != 3){
            System.out.print("Invalid! Enter exactly 3 numbers (ex: 1 2 4): ");
            continue;
         }
        
         boolean validNumbers = userGear[0].matches("[1-6]") && 
                                userGear[1].matches("[1-6]") && 
                                userGear[2].matches("[1-6]");
                                
         // makes sure for all cases making sure we don't have a duplicate
         boolean hasDuplicate = userGear[0].equals(userGear[1]) || 
                       userGear[1].equals(userGear[2]) || 
                       userGear[0].equals(userGear[2]);

         if (hasDuplicate) {
            System.out.print("No duplicates allowed! Pick 3 unique items: ");
            continue; // Restarts the loop so they have to try again
         }
         
         if(!validNumbers){
             System.out.print("Invalid! Only numbers 1-6 allowed: ");
             continue;
         }
         // reason we add "" because thats 0 and user isnt going to input 0
         // for canteen he inputs 1 for canteen 
         String[] gearNames = {" ", "Old Canteen", "Dry Matches", "Rusty Machete",
                               "Bear Bell", "Sleeping Bag", "Pepper Spray"};
                               
         // map each user number input to its gear name and store in inventory       
         // user inputs 4, gearNames[4] finds the String at that position,
         // then parses that String value into the user inventory
         inventory[0] = gearNames[Integer.parseInt(userGear[0])];
         inventory[1] = gearNames[Integer.parseInt(userGear[1])];
         inventory[2] = gearNames[Integer.parseInt(userGear[2])];
         break;
     }
     
   }
   
   
   
   public static void nextScene(Scanner in, String sceneText) {
      System.out.println("\n\n");
      printDivider();
      System.out.println(sceneText);
   }
   
   // Create tool for scene divisors
   public static void printDivider() {
      System.out.println("==========================================================");
   }
   
   //method for making sure user only inputs for a/b/c or d but shorter
   public static String getValidInput(Scanner in, String[] validLetters) {
      String choice = "";
      while (true) {   
         choice = in.nextLine().trim().toUpperCase();
         // does string contain their choice?
         // call it String choice = getValidInput(in, "ABC");
         for (String option : validLetters) {
            if (choice.equals(option)) {
               return choice;
            }
         }System.out.print("Invalid input, try again: ");
      }
   }
 
   
   // ----- 3 Play-throughs -----
   
   
   // The "Shortcut" (Tool Check)
   public static boolean theShortcutB1(Scanner in, String[] inventory, int count){
      System.out.println();
      nextScene(in, "                       The Shortcut");
      
      System.out.print("\nYou're walking down a trail and take the shortcut!"
                       + "\nYou make your way through some bushes and continue" 
                       + "\non with your journey.\n");
      in.nextLine();
      System.out.print("\nAs you walk down this path, you eventually end up" 
                       + "\nat a canyon with a rope bridge connecting the two sides."
                       + "\nYou are forced to cross this bridge to get over.\n");
      in.nextLine();
      System.out.print("\n\nUh-oh! Planks snap and ropes dangle as you are closing"
                       + "\nin on the other side of the canyon. There is now a"
                       + "\nlarge hole in front of you. What do you want to do?" );  
      System.out.print("\n\nA) Try to jump across the hole and hope the planks"
                       + "\n   don't snap as you land.");    
      System.out.print("\nB) Try to hold on to the rope that's dangling as a result" 
                       + "\n   of the snapping."); 
      System.out.print("\nC) Check Inventory.");                  
      System.out.print("\n\nChoose an option: ");  
      while (true){       
         String choice1B1 = getValidInput(in, new String[] {"A", "B", "C"});
         if(choice1B1.equals("B")) {
            System.out.println("\nYou try to hold on to the rope, but the bridge keeps failing."
                              + "\nYou start swing violently until all the supports fail." 
                              +"\nYou fall down the canyon to your demise.");
            in.nextLine();                   
            System.out.println("\nDeath by 'The Long Drop'."); 
                        
            return false;
         }
         else if (choice1B1.equals("C")){
            System.out.print("Your inventory: \n\n");
            printInventory(inventory, count);
            System.out.print("\nChoose an option: ");
         }
         else {break;}
      }
      
      System.out.print("\nYou make the jump, but your foot slips \nthrough"
                       + " a gap in the boards and your ankle \ngets caught by"
                       + " a rope. You are left hanging \nnear" 
                       + " the edge of the canyon.\n");
      in.nextLine();
      if (!checkInventory(inventory, count, "Rusty Machete")){
         System.out.print("\nYou can't reach the knot with your hands."
                          +"\nEventually the rope snaps and you \nfall into"
                          + " the canyon while half conscious.");
         in.nextLine();
         System.out.print("\nDeath by The 'Long Drop 1.5'");
         return false;
      }
      
      System.out.print("\nGood thing you packed a machete!"
                       + "\nThe blade makes quick work of the rope." 
                       + "\nYou barely make onto the ledge of "
                       + "the canyon wall.\n");
      in.nextLine();
      System.out.print("\nAs you traverse the ledge, something "
                       + "glimmers \nin the weeds. You find a"
                       + " Silver Compass!\n");
      in.nextLine();
      System.out.print("What shall you do?\n");
      System.out.print("\nA) Leave it be.");
      System.out.print("\nB) Swap out an item for the compass.");
      System.out.print("\nC) Check inventory.");
      System.out.print("\n\nChoose an option: ");
      while (true) {
         String choice2B1 = getValidInput(in, new String[]{"A", "B", "C"});
    
         if (choice2B1.equals("B")) {
            // swapItem here immediately
            // if we just call print invetory it prints 
            // item| item| item but just need 1 of the itemes in array
            // so we just print the spefic item at each idex
            System.out.println();
            printInventory(inventory, count);
            System.out.print("\nWhich item would like you to swap out? (1/2/3): ");
            String swapChoice = getValidInput(in, new String[]{"1","2","3"});
            switch (swapChoice) {
               case "1": inventory[0] = "Silver Compass"; break;
               case "2": inventory[1] = "Silver Compass"; break;
               case "3": inventory[2] = "Silver Compass"; break;
            }
            break; // This is the break for the while(true) loop
         }      
         else if (choice2B1.equals("A")) {
            break; // User chose to leave it, exit the loop
         } 
         else {
            // User chose C - show inventory and loop back to the choice
            System.out.println();
            printInventory(inventory, count);
            System.out.print("\nChoose an option: ");
         }
      }   
      
      System.out.print("\n\nYou keep walking down the tight ledge"
                       + " and end up \nat a sketchy exit, it's completely "
                       + "engulfed in haze!\n");
      in.nextLine();
      
      if (!checkInventory(inventory, count, "Silver Compass")){
         System.out.print("\n\nYou don't have a compass to traverse the haze!"
                          + "\nYou decide to try to cross, but end up going"
                          + "\nnowhere. You end up lost forever");
         in.nextLine();
         System.out.print("\n\nDeath by 'Forever Lost'\n"); ;
         return false;    
      }
      System.out.print("\nYou lucked out, you have a compass in your inventory!"
                       + "\nThough you are not able to see exactly where you "
                       + "are\ngoing, the compass helps you make your way out.\n");
      in.nextLine();
      System.out.print("\nVictory by 'Master Navigator'\n");
      return true;
   }
   
   
   // The "Deep Forest"
   public static boolean deepForestB2(Scanner in, String[] inventory, int count) {
      return true;
   }
   
   
   // The "River Rapids" (Requirement Fork)
   public static boolean riverRapidsB3(Scanner in, String[] inventory, int count) {
      return true;
   }
   
   
   
   
   // ----- Inventory ------
   
   public static int addItem(String[] inventory, int count, String newItem){
      //not count bc we need to check ENTIRE bag even null
      for (int i = 0; i < inventory.length; i++){
         // searches for null in array 
         //replaces it with the swapped/added item
         if (inventory[i] == null){
            inventory[i] = newItem;
            return (count + 1);
         }
      }
      return count;
   }
   
   public static boolean checkInventory(String[] inventory, int count, String requiredItem){
      // going through the invetory from index zero to 2
      // we can use inventory.length because 
      // != to prevent crash or errors for branch 2
      for (int i = 0; i < count; i++){
         if (inventory[i] != null && inventory[i].equals(requiredItem)){
         return true;
         }
      }
      return false;
   }
   
   public static void printInventory(String[] inventory, int count) {

      for (int i = 0; i < count; i++) {
         System.out.println((i + 1) + ") " + inventory[i]);
      }

      if (count == 0) {
         System.out.println("Inventory empty.");
      }
   }
   
   public static int removeItem(String[] inventory, int count, String itemToRemove){
      
      for (int i = 0; i < count; i++){
         if (inventory[i] != null && inventory[i].equals(itemToRemove)){
            inventory[i] = null;
            return (count - 1);
         }
      }
      return count;
   }
   
}