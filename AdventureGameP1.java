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
         System.out.print("Gear Selected: \n\n");
         printInventory(inventory);
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
         survived = theShortcutB1(in, inventory);
         }
         else if (branchChoice.equals("B")) {
            survived = deepForestB2(in, inventory);
         }
         else{
            survived = riverRapidsB3(in, inventory);
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
   
   //method for making sure user only inputs for a/b/c/ somtimes d/e
   public static String getValidInput(Scanner in, String[] validLetters) {
      String choice = "";
      while (true) {   
         choice = in.nextLine().trim().toUpperCase();
         // does string contain their choice?
         // call it String choice = getValidInput(in, "ABCDE");
         for (String option : validLetters) {
            if (choice.equals(option)) {
               return choice;
            }
         }System.out.print("Invalid input, try again: ");
      }
   }
 
   
   // ----- 3 Play-throughs -----
   
   
   // The "Shortcut" (Tool Check)
   public static boolean theShortcutB1(Scanner in, String[] inventory){
      System.out.println();
      nextScene(in, "                       The Shortcut");
      
      // prompt 1: walking the trail
      System.out.print("\nYou're walking down a trail and take the shortcut!"
                       + "\nYou make your way through some bushes and continue" 
                       + "\non with your journey.\n");
      in.nextLine();
      // prompt 2: the rope bridge
      System.out.print("\nAs you walk down this path, you eventually end up" 
                       + "\nat a canyon with a rope bridge connecting the two sides."
                       + "\nYou are forced to cross this bridge to get over.\n");
      in.nextLine();
      // prompt 3: plank snaps - jump or hold on
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
            printInventory(inventory);
            System.out.print("\nChoose an option: ");
         }
         else {break;}
      }
      // prompt 4: ankle caught in rope - machete check
      System.out.print("\nYou make the jump, but your foot slips \nthrough"
                       + " a gap in the boards and your ankle \ngets caught by"
                       + " a rope. You are left hanging \nnear" 
                       + " the edge of the canyon.\n");
      in.nextLine();
      if (!checkInventory(inventory, ("Rusty Machete"))){
         System.out.print("\nYou can't reach the knot with your hands."
                          +"\nEventually the rope snaps and you \nfall into"
                          + " the canyon while half conscious.");
         in.nextLine();
         System.out.print("\nDeath by The 'Long Drop 1.5'.");
         return false;
      }
      
      // prompt 5: machete success, find compass
      System.out.print("\nGood thing you packed a machete!"
                       + "\nThe blade makes quick work of the rope." 
                       + "\nYou barely make onto the ledge of "
                       + "the canyon wall.\n");
      in.nextLine();
      System.out.print("\nAs you traverse the ledge, something "
                       + "glimmers \nin the weeds. You find a"
                       + " Silver Compass!\n");
      in.nextLine();
      // prompt 6: swap compass or leave it
      System.out.print("What shall you do?\n");
      System.out.print("\nA) Leave it be.");
      System.out.print("\nB) Swap out an item for the compass.");
      System.out.print("\nC) Check inventory.");
      System.out.print("\n\nChoose an option: ");
      while (true) {
         String choice2B1 = getValidInput(in, new String[]{"A", "B", "C"});
    
         if (choice2B1.equals("B")) {
            System.out.println();
            printInventory(inventory);
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
            printInventory(inventory);
            System.out.print("\nChoose an option: ");
         }
      }   
      
      // prompt 7: haze exit - compass check
      System.out.print("\n\nYou keep walking down the tight ledge"
                       + " and end up \nat a sketchy exit, it's completely "
                       + "engulfed in haze!\n");
      in.nextLine();
      
      if (!checkInventory(inventory, "Silver Compass")){
         System.out.print("\n\nYou don't have a compass to traverse the haze!"
                          + "\nYou decide to try to cross, but end up going"
                          + "\nnowhere. You end up lost forever");
         in.nextLine();
         System.out.print("\n\nDeath by 'Forever Lost'.\n"); ;
         return false;    
      }
      System.out.print("\nYou lucked out, you have a compass in your inventory!"
                       + "\nThough you are not able to see exactly where you "
                       + "are\ngoing, the compass helps you make your way out.\n");
      in.nextLine();
      System.out.print("\nVictory by 'Master Navigator'.\n");
      return true;
   }
   
   
   // The "Deep Forest"
   public static boolean deepForestB2(Scanner in, String[] inventory) {
      nextScene(in, "                     The Deep Forest\n");
      // prompt 1: enter the forest
      System.out.print("\nYou enter into thick pine woods to start your journey."
                       + "\nYou begin traversing the forest down a trail.\n");
      in.nextLine();
      // prompt 2: backpack discovery
      System.out.print("\nYou keep walking down this trail, and eventually"
                       + "\nstumble upon a backpack in some bushes. "
                       + "You decide\nto check what's in the bag and find"
                       + " an Energy Bar \nand a First Aid Kit.\n");
      in.nextLine();
      System.out.print("\nWhat shall you do?\n");
      System.out.print("\nA) Swap out an item for the Energy Bar.");
      System.out.print("\nB) Swap out an item for the First Aid Kit.");
      System.out.print("\nC) Swap out 2 items for both.");
      System.out.print("\nD) Leave the items alone.");
      System.out.print("\nE) Check inventory.");
      System.out.print("\n\nChoose an option: ");
      //instead of huge block of nested if-else stuff use nested switches
      while (true) {
         String choice1B2 = getValidInput(in, new String[]{"A", "B", "C", "D", "E"});
         switch (choice1B2) {
            case "A":
               System.out.println();
               System.out.println("1) " + inventory[0]);
               System.out.println("2) " + inventory[1]);
               System.out.println("3) " + inventory[2]);
               System.out.print("\nWhich item to swap out? (1/2/3): ");
               String swapA = getValidInput(in, new String[]{"1","2","3"});
               switch (swapA) {
                  case "1": inventory[0] = "Energy Bar"; break;
                  case "2": inventory[1] = "Energy Bar"; break;
                  case "3": inventory[2] = "Energy Bar"; break;
               }
            break;
         
            case "B":
               System.out.println();
               System.out.println("1) " + inventory[0]);
               System.out.println("2) " + inventory[1]);
               System.out.println("3) " + inventory[2]);
               System.out.print("\nWhich item to swap out? (1/2/3): ");
               String swapB = getValidInput(in, new String[]{"1","2","3"});
               switch (swapB) {
                  case "1": inventory[0] = "First Aid Kit"; break;
                  case "2": inventory[1] = "First Aid Kit"; break;
                  case "3": inventory[2] = "First Aid Kit"; break;
               }
            break;
            
            case "C":
               System.out.println();
               System.out.println("1) " + inventory[0]);
               System.out.println("2) " + inventory[1]);
               System.out.println("3) " + inventory[2]);
               System.out.print("\nWhich item to swap for Energy Bar? (1/2/3): ");
               String swapC1 = getValidInput(in, new String[]{"1","2","3"});
               System.out.println();
               switch (swapC1) {
                  case "1": inventory[0] = "Energy Bar"; break;
                  case "2": inventory[1] = "Energy Bar"; break;
                  case "3": inventory[2] = "Energy Bar"; break;
               }
               // after swapC1 is chosen, offer only the remaining 2 slots
               // shows updated inventory so they can see what they just swapped in
               String[] remaining;
               if (swapC1.equals("1")) remaining = new String[]{"2","3"};
               else if (swapC1.equals("2")) remaining = new String[]{"1","3"};
               else remaining = new String[]{"1","2"};
               
               System.out.println("1) " + inventory[0]);
               System.out.println("2) " + inventory[1]);
               System.out.println("3) " + inventory[2]);
               
               // ensures they dont swapout the item they just swapped in
               System.out.print("\nWhich item to swap for First Aid Kit? (" 
                                + remaining[0] + "/" + remaining[1] + "): ");
               String swapC2 = getValidInput(in, remaining);
               System.out.println();
               switch (swapC2) {
                  case "1": inventory[0] = "First Aid Kit"; break;
                  case "2": inventory[1] = "First Aid Kit"; break;
                  case "3": inventory[2] = "First Aid Kit"; break;
               }
            break;
            
            case "D":
               break;
            case "E":
               System.out.print("Your inventory: \n\n");
               printInventory(inventory);
               System.out.print("\nChoose an option: ");
               continue;
         }
         break; 
      }
      
      // prompt 3: sunset
      System.out.print("\n\nYou continue walking through the forest as the sun begins to set.\n");
      in.nextLine();

      // prompt 4: Night falls - fire or keep walking 
      System.out.print("\nNight falls and the temperature drops. What do you do?\n");
      System.out.print("\nA) Build a fire and rest.");
      System.out.print("\nB) Keep walking through the dark.");
      System.out.print("\nC) Check Inventory.\n");
      System.out.print("\nChoose an option: ");
      
      while (true) {
         String nightChoice = getValidInput(in, new String[]{"A", "B", "C"});
            if (nightChoice.equals("B")) {
               System.out.print("\nYou push on through the darkness. The trail disappears"
                                + "\nand you don't notice the ravine until it's too late."
                                + "\nYou tumble off the edge.");
               in.nextLine();
               System.out.print("\n\nDeath by 'Into the Ravine'\n");
               return false;
            } 
            else if (nightChoice.equals("C")) {
               System.out.print("\nYour inventory:\n\n");
               printInventory(inventory);
               System.out.print("\nChoose an option: ");
               continue;
            } 
            else { break; }
       }
      // prompt 5: build a fire, fall asleep
      System.out.print("\n\nYou gather wood and get a fire going. The warmth is a relief."
                       + "\nYou drift off to sleep beside the flames.\n");
      in.nextLine();

      // prompt 6: Snake bite - first aid kit check
      System.out.print("\nYou wake up in a cold sweat. Something bit you in the night."
                       + "\nYou look down and see two puncture marks on your leg. Snake bite.\n");
      in.nextLine();

      if (!checkInventory(inventory, "First Aid Kit")) {
         System.out.print("\nYou rummage through your gear but have nothing to treat the venom."
                          + "\nYour vision blurs. Your limbs go heavy. The forest spins.");
         in.nextLine();
         System.out.print("\n\nDeath by 'Stinging Agony'.\n");
         return false;
      }

      System.out.print("\nLucky! You pull out your First Aid Kit and treat the wound."
                       + "\nIt's not perfect but it buys you time.\n");
      removeItem(inventory, "First Aid Kit");
      in.nextLine();
      
      // prompt 7: Golden Statue - slot is now free
      System.out.print("\nSunrise. You press on and stumble upon a stone altar outside a cave."
                       + "\nSitting on it is a Golden Statue, glinting in the morning light.\n");
      in.nextLine();
      System.out.print("\nYour inventory now has a free slot. Take the statue?\n");
      System.out.print("\nA) Take the Golden Statue.");
      System.out.print("\nB) Leave it alone.");
      System.out.print("\nC) Check Inventory.\n");
      System.out.print("\nChoose an option: ");

      boolean hasStatue = false;
      while (true) {
         String statueChoice = getValidInput(in, new String[]{"A", "B", "C"});
            if (statueChoice.equals("A")) {
               addItem(inventory, "Golden Idol");
               hasStatue = true;
               System.out.print("\n\nYou pocket the idol. It's heavy but could be worth something.\n");
               in.nextLine();
               break;
            } 
            else if (statueChoice.equals("B")) {
               System.out.print("\nYou leave it. Something about it feels off.\n");
               in.nextLine();
               break;
            } 
            else {
               System.out.print("\nYour inventory:\n\n");
               printInventory(inventory);
               System.out.print("\nChoose an option: ");
            }
         }
         
         // prompt 8: Whispering Cave - matches check
         System.out.print("\nThe only way forward is through the cave. It's pitch black inside.\n");
         in.nextLine();

         if (!checkInventory(inventory, "Dry Matches")) {
            System.out.print("\nYou step inside and immediately can't see a thing."
                             + "\nYou inch forward and the ground vanishes beneath your foot."
                             + "\nYou fall into a deep pit.");
            in.nextLine();
            System.out.print("\n\nDeath by 'Blind Man's Trap'.\n");
            return false;
         }

         System.out.print("\nYou strike a match and light a makeshift torch from a branch."
                        + "\nThe cave is treacherous but you navigate the pitfalls safely.\n");
         in.nextLine();

         // prompt 9: Bandits at the exit
         System.out.print("\nLight ahead! You emerge from the cave to find the exit gate."
                         + "\nBut two armed bandits are blocking the path, eyeing your gear.\n");
         in.nextLine();

         if (hasStatue) {
            System.out.print("\nOne of them spots the Golden Statue peeking out of your bag."
                            + "\nHis eyes go wide. They don't ask twice.");
            in.nextLine();
            System.out.print("\n\nDeath by 'The Price of Greed'.\n");
            return false;
         }

         System.out.print("\nYou walk calmly past. Nothing worth stealing, nothing worth killing for."
                          + "\nThey wave you through without a word.\n");
         in.nextLine();
         System.out.print("\nVictory by 'Forest Survivor'.\n");
         return true;
   }
   
   
   // The "River Rapids" (Requirement Fork)
   public static boolean riverRapidsB3(Scanner in, String[] inventory) {
      return true;
   }
   
   
   
   
   // ----- Inventory ------
   
   public static void addItem(String[] inventory, String newItem){
      //not count bc we need to check ENTIRE bag even null
      for (int i = 0; i < inventory.length; i++){
         // searches for null in array 
         //replaces it with the swapped/added item
         if (inventory[i] == null){
            inventory[i] = newItem;
            return;
         }
      }
   }
   
   public static boolean checkInventory(String[] inventory, String requiredItem){
      // going through the invetory from index zero to 2
      // != to prevent crash or errors for branch 2
      for (int i = 0; i < inventory.length; i++){
         if (inventory[i] != null && inventory[i].equals(requiredItem)){
         return true;
         }
      }
      return false;
   }
   
   public static void printInventory(String[] inventory) {
      int displayed = 0;
      for (int i = 0; i < inventory.length; i++) {
         if (inventory[i] != null) {
            System.out.println((++displayed) + ") " + inventory[i]);
         }
      }
      if (displayed == 0) System.out.println("Inventory empty.");
   }
   
   public static void removeItem(String[] inventory, String itemToRemove){
      
      for (int i = 0; i < inventory.length; i++){
         if (inventory[i] != null && inventory[i].equals(itemToRemove)){
            inventory[i] = null;
            return;
         }
      }
   }
   
}