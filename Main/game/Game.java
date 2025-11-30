package Main.game;

import Main.game.GameMenu;
import java.util.Scanner;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.clearScreen.ClearScreen;
import Main.styles.textColor.TextColorHub;

public class Game {
    private GameMenu menu;
    public Scanner input = new Scanner(System.in);
    private CenterHub centerHub = new CenterHub();
    private TextColorHub textColor = new TextColorHub();
    private ClearScreen clearScreen = new ClearScreen();

    public Game() {
        menu = new GameMenu();
    }
   
    public void start() {
        int choice = 0;
        String text;
        System.out.println("Game Starting in...");
        do {
            QuezonChronicles();
            System.out.println("=====================================================================================================================================================");
            text = "( 1 ) Start";;
            centerHub.printCenteredText(text);
            text = "( 2 ) About the Game";
            centerHub.printCenteredText(text);
            text = "( 3 ) Exit";
            centerHub.printCenteredText(text);
            System.out.println("=====================================================================================================================================================");
            System.out.print("Enter your choice: ");
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
                    clearScreen.clear();
                    menu.showMainMenu();
                    break;
                case 2:
                    clearScreen.clear();
                    System.out.println("==================================================================== ABOUT THE GAME =================================================================");
                    text = 
                        "Quezon Chronicles is an epic RPG adventure set in the Philippines.\n " +
                        "Embark on a journey through rich landscapes, " +
                        "encountering mythical creatures and historical figures.\n" +
                        "Uncover the secrets of the archipelago while battling foes, solving puzzles, " +
                        "and forging alliances.\n" +
                        "Experience a unique blend of Filipino culture and mythology in this captivating quest for glory!";
                    centerHub.printCenteredText(text);
                    System.out.println("=====================================================================================================================================================");
                    System.out.println("Press Enter to return to the main menu...");
                    input.nextLine();
                    clearScreen.clear();
                    break;
                case 3:
                    clearScreen.clear();
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    clearScreen.clear();
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    public void QuezonChronicles() {
        centerHub.printCenteredText(textColor.GREEN + "                                                                                                ");
        centerHub.printCenteredText("                @@@@@@    @@@  @@@  @@@@@@@@  @@@@@@@@   @@@@@@   @@@  @@@                     ");
        centerHub.printCenteredText("               @@@@@@@@   @@@  @@@  @@@@@@@@  @@@@@@@@  @@@@@@@@  @@@@ @@@                     ");
        centerHub.printCenteredText("               @@!  @@@   @@!  @@@  @@!            @@!  @@!  @@@  @@!@!@@@                     ");
        centerHub.printCenteredText("               !@!  @!@   !@!  @!@  !@!           !@!   !@!  @!@  !@!!@!@!                     ");
        centerHub.printCenteredText("               @!@  !@!   @!@  !@!  @!!!:!       @!!    @!@  !@!  @!@ !!@!                     ");
        centerHub.printCenteredText("               !@!  !!!   !@!  !!!  !!!!!:      !!!     !@!  !!!  !@!  !!!                     ");
        centerHub.printCenteredText("               !!:!!:!:   !!:  !!!  !!:        !!:      !!:  !!!  !!:  !!!                     ");
        centerHub.printCenteredText("               :!: :!:    :!:  !:!  :!:       :!:       :!:  !:!  :!:  !:!                     ");
        centerHub.printCenteredText("               ::::: :!   ::::: ::   :: ::::   :: ::::  ::::: ::   ::   ::                     ");
        centerHub.printCenteredText("                : :  :::   : :  :   : :: ::   : :: : :   : :  :   ::    :                      ");
        centerHub.printCenteredText("                                                                                                ");
        centerHub.printCenteredText("                                                                                                ");
        centerHub.printCenteredText(" @@@@@@@  @@@  @@@  @@@@@@@    @@@@@@   @@@  @@@  @@@   @@@@@@@  @@@       @@@@@@@@   @@@@@@   ");
        centerHub.printCenteredText("@@@@@@@@  @@@  @@@  @@@@@@@@  @@@@@@@@  @@@@ @@@  @@@  @@@@@@@@  @@@       @@@@@@@@  @@@@@@@   ");
        centerHub.printCenteredText("!@@       @@!  @@@  @@!  @@@  @@!  @@@  @@!@!@@@  @@!  !@@       @@!       @@!       !@@        ");
        centerHub.printCenteredText("!@!       !@!  @!@  !@!  @!@  !@!  @!@  !@!!@!@!  !@!  !@!       !@!       !@!       !@!        ");
        centerHub.printCenteredText("!@!       @!@!@!@!  @!@!!@!   @!@  !@!  @!@ !!@!  !!@  !@!       @!!       @!!!:!    !!@@!!     ");
        centerHub.printCenteredText("!!!       !!!@!!!!  !!@!@!    !@!  !!!  !@!  !!!  !!!  !!!       !!!       !!!!!:     !!@!!!    ");
        centerHub.printCenteredText(":!!       !!:  !!!  !!: :!!   !!:  !!!  !!:  !!!  !!:  :!!       !!:       !!:            !:!   ");
        centerHub.printCenteredText(":!:       :!:  !:!  :!:  !:!  :!:  !:!  :!:  !:!  :!:  :!:        :!:      :!:           !:!    ");
        centerHub.printCenteredText(" ::: :::  ::   :::  ::   :::  ::::: ::   ::   ::   ::   ::: :::   :: ::::   :: ::::  :::: ::    ");
        centerHub.printCenteredText(" :: :: :   :   : :   :   : :   : :  :   ::    :   :     :: :: :  : :: : :  : :: ::   :: : :     " + textColor.RESET);
        System.out.println("");
    }
}