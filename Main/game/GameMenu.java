package Main.game;

import java.util.Scanner;
import Main.character.enemy.Enemy;
import Main.character.player.Player;
import Main.character.player.classes.*;
import Main.item.*;
import Main.game.BattleSystem;
import Main.worldBuilder.WorldMap;
import Main.worldBuilder.Town;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.clearScreen.ClearScreen;
import Main.styles.animationHub.TypeWriter;
import Main.styles.textColor.TextColorHub;

public class GameMenu {

    private Player player;
    private Enemy enemy;
    private BattleSystem battleSystem = new BattleSystem();
    private CenterHub centerHub = new CenterHub();
    private TypeWriter typeWriter = new TypeWriter();
    private TextColorHub textColor = new TextColorHub();
    private ClearScreen clearScreen = new ClearScreen();
    private WorldMap worldMap = new WorldMap();
    private String name;

    public Scanner input = new Scanner(System.in);
   
    public void showMainMenu() {
        String text;
        if (player == null) {
            System.out.println("=====================================================================================================================================================");
            text = "Create Character";
            centerHub.printCenteredText(text);
            System.out.print("Enter your name: ");
            name = input.nextLine();
        }
        while (player == null) {
            showClasses();
            inputMessager();
            int classChoice;
            try {
                classChoice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                input.nextLine();
                clearScreen.clear();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (classChoice) {
                case 1:
                    int confirmChoice1 = 0;
                    do {
                        confirmationMessager();
                        try {
                            confirmChoice1 = input.nextInt();
                            input.nextLine();
                        } catch (Exception e) {
                            input.nextLine();
                            clearScreen.clear();
                            System.out.println("Invalid input. Please enter a number.");
                            continue;
                        }
                        if (confirmChoice1 == 1) {
                            clearScreen.clear();
                            player = new Bruid(name);
                            System.out.println(textColor.GREEN + "You have selected the Bruid class." + textColor.RESET);
                            break;
                        } 
                        else if (confirmChoice1 == 2) {
                            clearScreen.clear();
                            player = new Bruid(name);
                            System.out.println("======================================================================== SKILLS =====================================================================");
                            for (int i = 0; i < player.getAttackMoves().length; i++) {
                                String move = player.getAttackMoves()[i];
                                centerHub.printCenteredText(move);
                            }
                            player = null;
                            System.out.println("=====================================================================================================================================================");
                        } 
                        else if (confirmChoice1 == 3) {
                            clearScreen.clear();
                            player = null;
                            continue;
                        } else {
                            clearScreen.clear();
                            System.out.println("Invalid input. Try again.");
                        }
                    } while (confirmChoice1 != 3 && player == null);
                    break;
                
                case 2:
                    int confirmChoice2 = 0;
                    do {
                        confirmationMessager();
                        try {
                            confirmChoice2 = input.nextInt();
                            input.nextLine();
                        } catch (Exception e) {
                            input.nextLine();
                            clearScreen.clear();
                            System.out.println("Invalid input. Please enter a number.");
                            continue;
                        }
                        if (confirmChoice2 == 1) {
                            clearScreen.clear();
                            player = new Mage(name);
                            System.out.println(textColor.BLUE + "You have selected the Mage class." + textColor.RESET);
                            break;
                        }
                        else if (confirmChoice2 == 2) {
                            clearScreen.clear();
                            player = new Mage(name);
                            System.out.println("======================================================================== SKILLS =====================================================================");
                            for (int i = 0; i < player.getAttackMoves().length; i++) {
                                String move = player.getAttackMoves()[i];
                                centerHub.printCenteredText(move);
                            }
                            player = null;
                            System.out.println("=====================================================================================================================================================");
                        } 
                        else if (confirmChoice2 == 3) {
                            clearScreen.clear();
                            player = null;
                            continue;
                        } else {
                            clearScreen.clear();
                            System.out.println("Invalid input. Try again.");
                        }
                    } while (confirmChoice2 != 3 && player == null);
                    break;
                
                case 3:
                    int confirmChoice3 = 0;
                    do {
                        confirmationMessager();
                        try {
                            confirmChoice3 = input.nextInt();
                            input.nextLine();
                        } catch (Exception e) {
                            input.nextLine();
                            clearScreen.clear();
                            System.out.println("Invalid input. Please enter a number.");
                            continue;
                        }
                        if (confirmChoice3 == 1) {
                            clearScreen.clear();
                            player = new TagalogMonk(name);
                            System.out.println(textColor.ORANGE + "You have selected the Tagalog Monk class." + textColor.RESET);
                        break;
                        }
                        else if (confirmChoice3 == 2) {
                            clearScreen.clear();
                            player = new TagalogMonk(name);
                            System.out.println("======================================================================== SKILLS =====================================================================");
                            for (int i = 0; i < player.getAttackMoves().length; i++) {
                                String move = player.getAttackMoves()[i];
                                centerHub.printCenteredText(move);
                            }
                            player = null;
                            System.out.println("=====================================================================================================================================================");
                        } 
                        else if (confirmChoice3 == 3) {
                            clearScreen.clear();
                            player = null;
                            continue;
                        } 
                        else {
                            clearScreen.clear();
                            System.out.println("Invalid input. Try again.");
                        }
                    } while (confirmChoice3 != 3 && player == null);
                    break;
                
                case 4:
                    int confirmChoice4 = 0;
                    do {
                        confirmationMessager();
                        try {
                            confirmChoice4 = input.nextInt();
                            input.nextLine();
                        } catch (Exception e) {
                            input.nextLine();
                            clearScreen.clear();
                            System.out.println("Invalid input. Please enter a number.");
                            continue;
                        }
                        if (confirmChoice4 == 1) {
                            clearScreen.clear();
                            player = new Thief(name);
                            System.out.println(textColor.PURPLE + "You have selected the Thief class." + textColor.RESET);
                            break;
                        }
                        else if (confirmChoice4 == 2) {
                            clearScreen.clear();
                            player = new Thief(name);
                            System.out.println("======================================================================== SKILLS =====================================================================");
                            for (int i = 0; i < player.getAttackMoves().length; i++) {
                                String move = player.getAttackMoves()[i];
                                centerHub.printCenteredText(move);
                            }
                            player = null;
                            System.out.println("=====================================================================================================================================================");
                        } 
                        else if (confirmChoice4 == 3) {
                            clearScreen.clear();
                            player = null;
                            continue;
                        } 
                        else {
                            clearScreen.clear();
                            System.out.println("Invalid input. Try again.");
                        }
                    } while (confirmChoice4 != 3 && player == null);
                    break;
                
                case 5:
                    int confirmChoice5 = 0;
                    do {
                        confirmationMessager();
                        try {
                            confirmChoice5 = input.nextInt();
                            input.nextLine();
                        } catch (Exception e) {
                            input.nextLine();
                            clearScreen.clear();
                            System.out.println("Invalid input. Please enter a number.");
                            continue;
                        }
                        if (confirmChoice5 == 1) {
                            clearScreen.clear();
                            player = new Warrior(name);
                            System.out.println(textColor.RED + "You have selected the Warrior class." + textColor.RESET);
                            break;
                        }
                        else if (confirmChoice5 == 2) {
                            clearScreen.clear();
                            player = new Warrior(name);
                            System.out.println("======================================================================== SKILLS =====================================================================");
                            for (int i = 0; i < player.getAttackMoves().length; i++) {
                                String move = player.getAttackMoves()[i];
                                centerHub.printCenteredText(move);
                            }
                            player = null;
                            System.out.println("=====================================================================================================================================================");
                        } 
                        else if (confirmChoice5 == 3) {
                            clearScreen.clear();
                            player = null;
                            continue;
                        } 
                        else {
                            clearScreen.clear();
                            System.out.println("Invalid input. Try again.");
                        }
                    } while (confirmChoice5 != 3 && player == null);
                    break;

                default:
                    clearScreen.clear();
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        }
        
        int choice = 0;
 		do {
            System.out.println("=====================================================================================================================================================");
            text = "( 1 ) Start Battle";
            centerHub.printCenteredText(text);
            text = "( 2 ) Show Stats";
            centerHub.printCenteredText(text);
            text = "( 3 ) Show Inventory";
            centerHub.printCenteredText(text);
            text = "( 4 ) Exit";
            centerHub.printCenteredText(text);
            System.out.println("=====================================================================================================================================================");
            inputMessager();
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                input.nextLine();
                clearScreen.clear();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    choosePath();
                    break;

                case 2:
                    clearScreen.clear();
                    player.showStats();
                    break;

                case 3:
                    clearScreen.clear();
                    Item[] inventory = player.getInventory();
                    boolean isEmpty = true;
                    if (inventory != null && inventory.length > 0) {
                        for (int i = 0; i < inventory.length; i++) {
                            if (inventory[i] != null) {
                                isEmpty = false;
                                break;
                            }
                        }
                    }
                    if (inventory == null || isEmpty) {
                        System.out.println("Your inventory is empty!");
                    } else {
                        player.showInventory();
                    }
                    break;

                case 4:
                    clearScreen.clear();
                    System.out.println("Exiting the Game...");
                    break;

                default:
                    clearScreen.clear();
                    System.out.println("Invalid choice. Please try again.");
                    break;     
            }
        } while (choice != 4);
    }
   
	public void inputMessager() {
  		System.out.print("Enter your choice: ");
    } 

    public void showClasses() {
        Player warrior = new Warrior("Temp");
        Player mage = new Mage("Temp");
        Player thief = new Thief("Temp");
        Player bruid = new Bruid("Temp");
        Player tagalogMonk = new TagalogMonk("Temp");
        String text;

        System.out.println("====================================================================== SELECT CLASS ===================================================================\n");
        text = "( 1 ) Bruid - The Banana Druid";
        centerHub.printCenteredText(textColor.GREEN + text + textColor.RESET);
        centerHub.printCenteredText(bruid.description);
        System.out.println();
        text = "( 2 ) Mage - The Arcane Panadero";
        centerHub.printCenteredText(textColor.BLUE + text + textColor.RESET);
        centerHub.printCenteredText(mage.description);
        System.out.println();
        text = "( 3 ) Tagalog Monk - The Disciple of Katahimikan";
        centerHub.printCenteredText(textColor.ORANGE + text + textColor.RESET);
        centerHub.printCenteredText(tagalogMonk.description);
        System.out.println();
        text = "( 4 ) Thief - The Shadow of the Kanto";
        centerHub.printCenteredText(textColor.PURPLE + text + textColor.RESET);
        centerHub.printCenteredText(thief.description);
        System.out.println();
        text = "( 5 ) Warrior - The Honor-bound Mandirigma";
        centerHub.printCenteredText(textColor.RED + text + textColor.RESET);
        centerHub.printCenteredText(warrior.description);
        System.out.println("\n=====================================================================================================================================================");
    }

    public void confirmationMessager() {
        System.out.println("=====================================================================================================================================================");
        centerHub.printCenteredText("( 1 ) Confirm Character");
        centerHub.printCenteredText("( 2 ) View Skills");
        centerHub.printCenteredText("( 3 ) Re-select Class");
        System.out.println("=====================================================================================================================================================");
        inputMessager();
    }

    public void choosePath() {
        
        clearScreen.clear();
        
        // Check if player has already chosen a path
        if (player.getChosenPath() != null) {
            System.out.println("=====================================================================================================================================================");
            centerHub.printCenteredText("You are currently on the " + player.getChosenPath().toUpperCase() + " path.");
            centerHub.printCenteredText("Resuming your progress...");
            System.out.println("=====================================================================================================================================================");
            
            Town resumeTown = player.getChosenPath().equals("west") ? 
                worldMap.buildWestPath(player) : worldMap.buildEastPath(player);
            
            // Navigate to the current town
            Town currentTown = resumeTown;
            for (int i = 0; i < player.getCurrentTownIndex(); i++) {
                currentTown = currentTown.getNextTown();
                if (currentTown == null) {
                    System.out.println("Error: Could not resume progress. Starting from beginning.");
                    player.setCurrentTownIndex(0);
                    currentTown = resumeTown;
                    break;
                }
            }
            
            currentTown.enterTown(player, player.getCurrentTownIndex());
            return;
        }
        
        spawnPointMessager();

        // First time choosing a path
        System.out.println("=====================================================================================================================================================");
        centerHub.printCenteredText("\nWhich path would you like to take?");
        centerHub.printCenteredText(textColor.RED + "1. West Side Path" + textColor.RESET);
        centerHub.printCenteredText(textColor.YELLOW + "Lucban" + textColor.RESET + " → " + textColor.BLUE + "Lucena (Miniboss)" + textColor.RESET + " → " + textColor.GREEN + "Sariaya" + textColor.RESET + " → " + textColor.ORANGE + "Candelaria" + textColor.RESET + " → " + textColor.RED + "Tiaong (Boss)" + textColor.RESET);
        System.out.println();
        centerHub.printCenteredText(textColor.BLUE + "2. East Side Path" + textColor.RESET);
        centerHub.printCenteredText(textColor.YELLOW + "Gumaca" + textColor.RESET + " → " + textColor.BLUE + "Lopez (Miniboss)" + textColor.RESET + " → " + textColor.GREEN + "Calauag" + textColor.RESET + " → " + textColor.ORANGE + "Infanta" + textColor.RESET + " → " + textColor.RED + "Real (Boss)" + textColor.RESET);
        System.out.println("\n=====================================================================================================================================================");
        inputMessager();
        int choice;
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            input.nextLine();
            clearScreen.clear();
            typeWriter.typeWriterFast("Invalid input. Defaulting to West Side.");
            choice = 1;
        }
        Town startingTown;

        if (choice == 1) {
            clearScreen.clear();
            typeWriter.typeWriterFast(textColor.RED + "You chose the West Side path!" + textColor.RESET);
            player.setChosenPath("west");
            player.setCurrentTownIndex(0);
            startingTown = worldMap.buildWestPath(player);
        } else if (choice == 2) {
            clearScreen.clear();
            typeWriter.typeWriterFast(textColor.BLUE + "You chose the East Side path!" + textColor.RESET);
            player.setChosenPath("east");
            player.setCurrentTownIndex(0);
            startingTown = worldMap.buildEastPath(player);
        } else {
            clearScreen.clear();
            typeWriter.typeWriterFast("Invalid choice. Defaulting to West Side.");
            player.setChosenPath("west");
            player.setCurrentTownIndex(0);
            startingTown = worldMap.buildWestPath(player);
        }

        startingTown.enterTown(player, 0);
    }

    public void spawnPointMessager() {

        centerHub.printCenteredText("@@@@@@@  @@@@@@@   @@@@@@  @@@       @@@@@@  @@@  @@@  @@@@@@@  @@@@@@@@ "); 
        centerHub.printCenteredText("@@!  @@@ @@!  @@@ @@!  @@@ @@!      @@!  @@@ @@!  @@@ !@@       @@!      ");        
        centerHub.printCenteredText("@!@@!@!  @!@!!@!  @!@  !@! @!!      @!@  !@! @!@  !@! !@! @!@!@ @!!!:!   ");        
        centerHub.printCenteredText("!!:      !!: :!!  !!:  !!! !!:      !!:  !!! !!:  !!! :!!   !!: !!:      ");   
        centerHub.printCenteredText(" :        :   : :  : :. :  : ::.: :  : :. :   :.:: :   :: :: :  : :: ::  ");        
        System.out.println();
        System.out.println();   
        System.out.println("====================================================================== " + textColor.GREEN + "ATIMONAN" + textColor.RESET + " ===================================================================\n");
        String text = "Atimonan rests peacefully between the rising cliffs of the Sierra mountain pass and the gentle waves of the eastern gulf, serving as a calm\n" + 
                      "refuge for travelers beginning their journey. The town is known for its quiet coastal mornings, where fishermen cast their nets at dawn\n" + 
                      "and the scent of fresh sea wind drifts across its humble wooden piers. Though modest in size, Atimonan holds a warm and welcoming\n" + 
                      "spirit, a place where adventurers gather their courage before stepping into the wider world.\n\n" +
                      "From here, your journey truly begins. You may take the western path, leading toward bustling towns and lively trade, or venture east along the coast,\n" +
                      "where quiet beaches and forested trails await. Which way will you go?\n";
        centerHub.printCenteredTextWithTypeWriter(text);
    }
}
