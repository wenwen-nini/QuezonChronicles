package Main.worldBuilder;

import java.util.Scanner;
import Main.character.enemy.Enemy;
import Main.character.Character;
import Main.character.player.Player;
import Main.game.BattleSystem;
import Main.item.*;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.clearScreen.ClearScreen;
import Main.styles.animationHub.TypeWriter;
import Main.styles.animationHub.LoadingDots;
import Main.styles.textColor.TextColorHub;
import Main.character.enemy.subclasses.*;

public class Town {
    private String name;
    private String description;
    private Enemy[] enemies;
    private Enemy boss;
    private Town nextTown;
    private CenterHub centerHub = new CenterHub();
    private TypeWriter typeWriter = new TypeWriter();
    private LoadingDots loadingDots = new LoadingDots();
    private TextColorHub textColor = new TextColorHub();
    private ClearScreen clearScreen = new ClearScreen();

    public Town(String name, String description, Enemy[] enemies, Enemy boss) {
        this.name = name;
        this.description = description;
        this.enemies = enemies;
        this.boss = boss;
    }

    public void setNextTown(Town nextTown) {
        this.nextTown = nextTown;
    }

    public Town getNextTown() {
        return this.nextTown;
    }

    public void enterTown(Player player, int townIndex) {

        String text = "CHAPTER " + String.valueOf(townIndex + 1);
        centerHub.printCenteredTextWithTypeWriter(text);    
        System.out.println("\n====================================================================== " + textColor.GREEN + name.toUpperCase() + textColor.RESET + " =======================================================================\n");
        centerHub.printCenteredTextWithTypeWriter(description);

        // Load progress if returning to this town
        int enemiesDefeated = (player.getCurrentTownIndex() == townIndex) ? player.getEnemiesDefeatedInTown() : 0;
        player.setCurrentTownIndex(townIndex);
        
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (player.getHp() == 0) {
                System.out.println("Resetting your progress and returning to the main menu...");
                // Reset player progress on death
                player.resetProgress();
                enemiesDefeated = 0;
                break;
            }
            int totalCount = (enemies != null ? enemies.length : 0) + (boss != null ? 1 : 0);
            int remaining = Math.max(0, totalCount - enemiesDefeated);
            System.out.println("\n======================================================================= " + textColor.GREEN + "OPTIONS" + textColor.RESET + " =====================================================================");
            System.out.println(textColor.RED + "Enemies left: " + remaining + textColor.RESET);
            centerHub.printCenteredText(textColor.RED + "( 1 ) Explore / Battle" + textColor.RESET);
            centerHub.printCenteredText(textColor.GREEN + "( 2 ) Move to next town" + textColor.RESET);
            centerHub.printCenteredText(textColor.BLUE + "( 3 ) Check player stats" + textColor.RESET);
            centerHub.printCenteredText(textColor.YELLOW + "( 4 ) Inventory" + textColor.RESET);
            centerHub.printCenteredText(textColor.PURPLE + "( 5 ) Exit to main menu" + textColor.RESET);
            System.out.println("=====================================================================================================================================================");
            System.out.print("Enter your Choice: ");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                sc.nextLine();
                clearScreen.clear();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    double chances = Math.random();
                    if (chances <= 0.17 && enemiesDefeated < (enemies != null ? enemies.length : 0)) { //FOR SPECIAL ENEMY SPAWN
                        clearScreen.clear();
                        typeWriter.typeWriterFast("A special enemy have spawned during your exploration!");
                        double specialEnemyChance = Math.random();
                        if (specialEnemyChance <= 0.25) {
                            Enemy enemy = new Alwynn(townIndex);
                            text = "A wild " + enemy.getName() + " appeared!";
                            typeWriter.typeWriterFast(textColor.GREEN + text + textColor.RESET);
                            typeWriter.typeWriterFast(textColor.GREEN + "Bro followed you all the way from the Atimonan!" + textColor.RESET);
                            new BattleSystem().BattleStart(player, enemy);
                        }
                        else if (specialEnemyChance > 0.25 && specialEnemyChance <= 0.5) {
                            Enemy enemy = new Eduard(townIndex, player);
                            text = "A wild " + enemy.getName() + " appeared!";
                            typeWriter.typeWriterFast(textColor.ORANGE + text + textColor.RESET);
                            typeWriter.typeWriterFast(textColor.ORANGE + "Careful—he might try to bargain, charm, or steal your loot." + textColor.RESET);
                            new BattleSystem().BattleStart(player, enemy);
                        }
                        else if (specialEnemyChance > 0.5 && specialEnemyChance <= 0.75) {
                            Enemy enemy = new Nell(townIndex);
                            text = "A wild " + enemy.getName() + " appeared!";
                            typeWriter.typeWriterFast(textColor.CYAN + text + textColor.RESET);
                            typeWriter.typeWriterFast(textColor.CYAN + "You didn’t see him coming—he’s from Sariaya, after all." + textColor.RESET);
                            new BattleSystem().BattleStart(player, enemy);
                        }

                        else if (specialEnemyChance > 0.75 && specialEnemyChance <= 1) {
                            Enemy enemy = new Red(townIndex);
                            text = "A wild " + enemy.getName() + " appeared!";
                            typeWriter.typeWriterFast(textColor.RED + text + textColor.RESET);
                            typeWriter.typeWriterFast(textColor.RED + "A rumble echoes... sparks ignite..."+ textColor.RESET);
                            new BattleSystem().BattleStart(player, enemy);
                        }
                        else {
                            System.out.println("Error spawning special enemy.");
                        }
                    } 
                    
                    else {
                        if (enemies != null && enemiesDefeated < enemies.length) {
                            clearScreen.clear();
                            Enemy enemy = enemies[enemiesDefeated];
                            enemy.scaleToPlayer(player, townIndex);
                            text = "A wild " + enemy.getName() + " appeared!";
                            typeWriter.typeWriterFast(textColor.GREEN + text + textColor.RESET);
                            new BattleSystem().BattleStart(player, enemy);
                            enemiesDefeated++;
                            player.setEnemiesDefeatedInTown(enemiesDefeated);
                        } else if (boss != null && enemiesDefeated == (enemies != null ? enemies.length : 0)) {
                            clearScreen.clear();
                            typeWriter.typeWriterFast(textColor.RED + "\nYou've reached the boss battle!" + textColor.RESET);
                            boss.scaleToPlayer(player, townIndex);
                            typeWriter.typeWriterFast(textColor.RED + "\nThe boss of " + name + " appears: " + boss.getName() + "!" + textColor.RESET);
                            new BattleSystem().BattleStart(player, boss);
                            enemiesDefeated++;
                            player.setEnemiesDefeatedInTown(enemiesDefeated);
                            typeWriter.typeWriterFast("You've cleared " + name + "!");
                        } else {
                            clearScreen.clear();
                            typeWriter.typeWriterFast("You’ve already cleared all enemies here!");
                        }
                        break;
                    }
                    break;

                case 2:
                    if ((boss == null && enemiesDefeated >= 3) || (boss != null && enemiesDefeated >= 3)) {
                        if (nextTown != null) {
                            clearScreen.clear();
                            text = "Preparing to travel to " + nextTown.name;
                            loadingDots.loadingDotsAnimation(textColor.GREEN + text + textColor.RESET);
                            clearScreen.clear();
                            text = "You've just arrived in " + nextTown.name;
                            typeWriter.typeWriterFast(textColor.GREEN + text + textColor.RESET);
                            if (!player.getUsesMp()) {
                                player.setStamina(player.getMaxStamina());
                                typeWriter.typeWriterFast(textColor.ORANGE + "\nYour stamina has been fully restored." + textColor.RESET);
                            } else {
                                player.setMp(player.getMaxMp());
                                typeWriter.typeWriterFast(textColor.BLUE + "\nYour MP has been fully restored." + textColor.RESET);
                            }
                            player.setEnemiesDefeatedInTown(0); // Reset for new town
                            nextTown.enterTown(player, townIndex + 1);
                            return;
                        } else {
                            clearScreen.clear();
                            typeWriter.typeWriterFast("You've reached the end of your journey!\n");
                            epilogueMessager();
                            System.out.println("\n===================================================================================================================================================");
                            if (player.getChosenPath().equals("west")) {
                                centerHub.printCenteredTextWithTypeWriter("The farmland winds of Tiaong brush against your shoulders as you return from your final battle.\n" +
                                                "You crossed the western roads fields, forests, and bustling towns—\n" +
                                                "leaving behind stories that the locals will pass down for years.\n" +
                                                "Tiaong stands safer because of you,\n" +
                                                "yet the land still hums with secrets hidden beyond the horizon.\n" +
                                                "A path remains unexplored.\n" +
                                                "A threat remains unseen.\n" +
                                                "Your adventure is far from finished.");
}
                            else {
                                centerHub.printCenteredTextWithTypeWriter("The waves of Real crash softly behind you as you take one last look at the eastern horizon.\n" +
                                                "You faced the storms, the cliffs, and the wild edges of Quezon’s coast—\n" +
                                                "and through every trial, you stood firm.\n\n" +
                                                "The people of Real will remember the traveler who walked in from Atimonan\n" +
                                                "and left as a protector of the east.\n\n" +
                                                "Yet even as peace settles over the shoreline,\n" +
                                                "you feel a pull—something unfinished,\n" +
                                                "a whisper carried by the sea breeze…\n\n" +
                                                "Your journey isn’t over.\n" +
                                                "Not yet.");
                            } 

                            chances = Math.random();
                            if (chances <= 0.5) {
                                Enemy secretEnemy = new Jm();
                                secretBossAppearedMessager();
                                new BattleSystem().BattleStart(player, secretEnemy);
                                secretBossDefeatedMessager(player);
                            }
                            player.setChosenPath(null); // Allow new path selection after completion
                            player.setCurrentTownIndex(0);
                            player.setEnemiesDefeatedInTown(0);
                            return;
                        }
                    } else {
                        clearScreen.clear();
                        typeWriter.typeWriterFast(textColor.RED + "You must defeat all enemies before moving on!" + textColor.RESET);
                    }
                    break;

                case 3:
                    clearScreen.clear();
                    player.showStats();
                    break;

                case 4: 
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
                    }
                    else {
                        player.showInventory();
                        System.out.println("\n========================================================================================================================================================");
                    }
                    break;

                case 5:
                    loadingDots.customLoadingDotsAnimation("Returning to main menu", 3, 500, 5000);
                    System.out.println();
                    clearScreen.clear();
                    return;

                default:
                    clearScreen.clear();
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void epilogueMessager() {
        centerHub.printCenteredText("@@@@@@@@ @@@@@@@  @@@ @@@       @@@@@@   @@@@@@@  @@@  @@@ @@@@@@@@");
        centerHub.printCenteredText("@@!      @@!  @@@ @@! @@!      @@!  @@@ !@@       @@!  @@@ @@!     ");
        centerHub.printCenteredText("@!!!:!   @!@@!@!  !!@ @!!      @!@  !@! !@! @!@!@ @!@  !@! @!!!:!  ");
        centerHub.printCenteredText("!!:      !!:      !!: !!:      !!:  !!! :!!   !!: !!:  !!! !!:     ");
        centerHub.printCenteredText(": :: ::   :       :   : ::.: :  : :. :   :: :: :   :.:: :  : :: :: ");
    }

    public void secretBossAppearedMessager() {
        typeWriter.typeWriterFast(textColor.YELLOW + "A mysterious force stirs within the Hidden Shrine of Mt. Banahaw…" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "Thunder echoes through the shrine as SUPER SARIAYAN JM kneels, energy flickering like dying festival lanterns." + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "A Wild Super Sariayan JM Appeared!" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "\nSo you’ve made it this far…" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "I am Super Sariayan JM—Guardian of Quezon Province!" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "If you wish to protect this land, then prove your strength!" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "Show me your resolve!" + textColor.RESET);
    }

    public void secretBossDefeatedMessager(Player player) {
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        clearScreen.clear();
        typeWriter.typeWriterFast(textColor.YELLOW + "…So, this is defeat." + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "You carry a strength far beyond my expectations.\n" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "I was consumed by the power meant to protect Quezon…" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "But you—" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "You fought not for glory, but for the people.\n" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "Take what’s left of my power… and guard this land better than I ever could." + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "Super Sariayan JM fades into golden light…" + textColor.RESET);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        clearScreen.clear();
        typeWriter.typeWriterFast(textColor.YELLOW + "The shrine grows calm. The corruption around Quezon fades away." + textColor.RESET);
        String text = player.getName() + ": It’s over… the land is healing.";
        typeWriter.typeWriterFast(textColor.YELLOW + text + textColor.RESET);
        text = player.getName() + ": From the shores of Atimonan, everything is returning to normal.";
        typeWriter.typeWriterFast(textColor.YELLOW + text + textColor.RESET);
        text = player.getName() + ": I didn’t just defeat JM… I freed him.";
        typeWriter.typeWriterFast(textColor.YELLOW + text + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "You've completed the journey of Quezon Chronicles!" + textColor.RESET);
        typeWriter.typeWriterFast(textColor.YELLOW + "Thank you for playing!" + textColor.RESET);
    }
}
