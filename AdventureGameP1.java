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
      
      String[] inventory = new String[3]; // The 3-slot bag
      int count = 0; // Starts empty
      
      System.out.print("\n\nWelcome, this is an adventure game where you ");
      System.out.print("\nchoose 3 pieces of gear at the start.");
      System.out.print(" You then \nenter one of three play-throughs where your ");
      System.out.print("\ngear choices decide whether you survive or not!");
      System.out.print("\nHave fun and choose wisely!");
      
      
      System.out.print("\n\nPS: Press ENTER/RETURN to advance in the story.");
      
      nextScene(in, "Choose 3 pieces of gear:\n\n  1. Old Canteen\n  2. Dry Matches\n" +
                "  3. Rusty Machete\n  4. Bear Bell\n  5. Sleeping Bag\n  6. Pepper Spray");
      System.out.print("\nEnter your 3 choices (ex: 1 2 4): ");
      selectGear(in, inventory);
      System.out.print("Gear Selected: ");
      printInventory(inventory, count);
      System.out.println();
      printDivider();
      
      nextScene(in, "Which pathway do you want to take?\n");
      System.out.println("A) The 'Shortcut'");
      System.out.println("B) The 'Deep Forest'");
      System.out.println("C) The 'River Rapids'\n");
      System.out.print("Enter option: ");
      String branchChoice = getValidInput(in, new String[] {"A", "B", "C"});
      printDivider();
      if (branchChoice.equals("A")) {
         theShortcutB1(in, inventory, count);
      }
      else if (branchChoice.equals("B")) {
         deepForestB2(in, inventory, count);
      }
      else {
         riverRapidsB3(in, inventory, count);
      }
      // this esstials calls the branch you chose
      // and puts you through that playthrough 

      boolean playing = true;
      while (playing){
      
      
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
      in.nextLine();
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
   public static void theShortcutB1(Scanner in, String[] inventory, int count){
      nextScene(in, "                       The Shortcut");
      
      System.out.print("\nYou're walking down a trail and take the shortcut!"
                       + "\nYou make your way through some bushes and continue" 
                       + "\non with your jounrey.");
      System.out.println();
      in.nextLine();
      System.out.print("\nAs you walk down this path, you eventually end up" 
                       + "\nat a cayon with a rope bridge connecting the two sides."
                       + "\nYou are forced to cross this bridge to get over.");
      in.nextLine();               
      printDivider();
   }
   
   
   // The "Deep Forest" (Resource Loop)
   public static void deepForestB2(Scanner in, String[] inventory, int count) {
        
   }
   
   
   // The "River Rapids" (Requirement Fork)
   public static void riverRapidsB3(Scanner in, String[] inventory, int count) {
        
   }
   
   
   
   
   // ----- Invetory ------
   
   public static int addItem(String[] inventory, int count, String newItem){
   return 0;
   }
   
   public static boolean checkInventory(String[] inventory, int count, String requiredItem){
      
   return false;
   }
   
   public static void printInventory(String[] inventory, int count){
      int i = 0; //set 'counter' = 0
      // no need for int in for loop, we defined it here
      // if our array is less than 3 (it's 2) it prints each index
      for(i = 0; i < 3; i++){
         if (i < 2) {
            System.out.print(inventory[i] + " | "); // only prints | after 2 slots
         } 
         else {
            System.out.print(inventory[i]); // since now it's greater than two it stops
         }
      }
   }
   
   public static int removeItem(String[] inventory, int count, String itemToRemove){
   return 0;
   }
   
}