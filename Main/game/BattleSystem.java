package Main.game;

import java.util.Scanner;
import Main.character.enemy.Enemy;
import Main.character.player.Player;
import Main.item.*;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.clearScreen.ClearScreen;
import Main.styles.animationHub.TypeWriter;
import Main.styles.textColor.TextColorHub;

public class BattleSystem {

    public Scanner input = new Scanner(System.in);

    private CenterHub centerHub = new CenterHub();
    private TypeWriter typeWriter = new TypeWriter();
    private TextColorHub textColor = new TextColorHub();
    private ClearScreen clearScreen = new ClearScreen();

    // Safe integer reader: reads a line and parses an int, re-prompts on invalid input

    public BattleSystem() { }

    public void BattleStart(Player player, Enemy enemy) {

        boolean playerInitiative = false;

        if (player.getSpeed() > enemy.getSpeed()) {
            printCombatStatus(player, enemy);
            typeWriter.typeWriterFast(textColor.ORANGE + "\nPlayer goes first!" + textColor.RESET);
            player.checkStunned();
            if (!player.getIsStunned()) {
                playerTurn(player, enemy);
                playerInitiative = true;
            }
        }
       
        else if (enemy.getSpeed() > player.getSpeed()) {
            String text = "\nEnemy goes first!";
            centerHub.printRightTextWithTypeWriter(textColor.RED + text + textColor.RESET);
            enemy.checkStunned();
            if (!enemy.getIsStunned()) {
                enemyTurn(player, enemy);
                enemy.updateSkillUsedTurn();
            }
        }
        else {
            double chances = Math.random();
            if (chances < 0.5) {
                printCombatStatus(player, enemy);
                typeWriter.typeWriterFast(textColor.ORANGE + "\nPlayer goes first!" + textColor.RESET);
                player.checkStunned();
                if (!player.getIsStunned()) {
                    playerTurn(player, enemy);
                }
                playerInitiative = true;
            }
            else {
                String text = "\nEnemy goes first!";
                centerHub.printRightText(textColor.RED + text + textColor.RESET);
                enemy.checkStunned();
                if (!enemy.getIsStunned()) {
                    enemyTurn(player, enemy);
                    enemy.updateSkillUsedTurn();
                }
            }
        }

        while (player.isAlive() && enemy.isAlive()) {
            if (playerInitiative) {
                enemy.checkStunned();
                if (!enemy.getIsStunned()) {
                    // Enemy goes first this round. If their action kills the player,
                    // we should not run the player's turn. Likewise, if enemy is
                    // dead before the player's turn, skip the player's turn.
                    enemyTurn(player, enemy);
                    enemy.updateDebuffs();
                    player.updateDebuffs();
                    player.updateTurnEffects();
                    if (player.isAlive() && enemy.isAlive()) {
                        printCombatStatus(player, enemy);
                        playerTurn(player, enemy);
                    }
                }
                else {
                    playerTurn(player, enemy);
                    player.updateDebuffs();
                    enemy.updateDebuffs();
                    player.updateTurnEffects();
                }
            }
            else {
                printCombatStatus(player, enemy);
                player.checkStunned();
                if (!player.getIsStunned()) {
                    // Player acts first. If the player kills the enemy, don't let
                    // the (now dead) enemy take a turn.
                    playerTurn(player, enemy);
                    if (player.isAlive() && enemy.isAlive()) {
                        enemy.checkStunned();
                        if (!enemy.getIsStunned()) {
                            enemyTurn(player, enemy);
                        }
                        enemy.updateDebuffs();
                        player.updateDebuffs();
                        player.updateTurnEffects();
                        
                    }
                }
                else {
                    clearScreen.clear();
                    printCombatStatus(player, enemy);
                    enemyTurn(player, enemy);
                    enemy.updateSkillUsedTurn();
                    enemy.updateDebuffs();
                    player.updateDebuffs();
                    player.updateTurnEffects();
                }
            }
        }

        if (!enemy.isAlive() && !player.isAlive()) {
            System.out.println("Both you and " + enemy.getName() + " have fallen!");
            Item loot = enemy.dropLoot();
            if (loot != null) {
                System.out.println("The " + enemy.getName() + " dropped " + loot.getName() + ", but you couldn't pick it up.");
            }
            player.resetProgress();
            return;
        }

        handleVictory(player, enemy);

        if (!player.isAlive()) {
            player.resetProgress();
            System.out.println(textColor.RED + "Game over! You have been slained!" + textColor.RESET);
            return;
        }
    }

    public void playerTurn(Player player, Enemy enemy) {
        
        System.out.println(textColor.ORANGE + "\nPlayer Turn / Your Turn:" + textColor.RESET);
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Your moves:");
            player.showMoves();
            System.out.println("5. Use item");
            System.out.println("=====================================================================================================================================================");
            System.out.print("Enter your choice: ");
            int choice = readInt();
            clearScreen.clear();
            if (choice >= 1 && choice <= 4) {
                // Reset the last-action flag, attempt the move, and only end input loop
                // if the move actually succeeded (had enough stamina/mp and wasn't on cooldown).
                player.setLastActionSucceeded(false);
                String attempted = player.getMoveName(choice);
                player.useMoves(choice, enemy);
                if (!player.getLastActionSucceeded()) {
                    // reprint combat status and the attempted move so user sees context again
                    printCombatStatus(player, enemy);
                    System.out.println();
                    if (attempted != null) {
                        if(player.getUsesMp()) {
                            System.out.println("Not enough MP!");
                        } else {
                            System.out.println("Not enough Stamina!");
                        }
                        System.out.println("Try again.\n");
                    }
                    // keep validInput false so the menu re-displays
                    continue;
                }
                validInput = true;
            }
            else if (choice == 5) {
                Item[] inventory = player.getInventory();
                boolean isEmpty = true;
                // Only iterate when inventory is non-null and has slots
                if (inventory != null && inventory.length > 0) {
                    for (int i = 0; i < inventory.length; i++) {
                        if (inventory[i] != null) {
                            isEmpty = false;
                            break;
                        }
                    }
                }
                if (inventory == null || isEmpty) {
                    System.out.println("\nYour inventory is empty!");
                    printCombatStatus(player, enemy);
                    System.out.println();
                } else {
                    printCombatStatus(player, enemy);
                    player.showInventory();
                    centerHub.printCenteredText("0. Cancel / Back");
                    System.out.println("\n========================================================================================================================================================");
                    System.out.print("Choose: ");
                    int itemIndex = readInt() - 1;
                    // Validate index and presence of item
                    if (itemIndex == -1) {
                        clearScreen.clear();
                        System.out.println("Item use cancelled.");
                    }
                    else if (itemIndex < -1 || itemIndex >= inventory.length || inventory[itemIndex] == null) {
                        clearScreen.clear();
                        System.out.println("Invalid item choice.");
                    }
                    else {
                        clearScreen.clear();
                        inventory[itemIndex].useItem(player);
                        player.removeItem(itemIndex);
                        validInput = true;
                    }
                }
            }
            else {
                System.out.println("Invalid input. \nPlease try again!");
                printCombatStatus(player, enemy);
                System.out.println();
            }
        }
    }

    public void enemyTurn(Player player, Enemy enemy) {
        String text;
        text = "Enemy Turn!";
        centerHub.printRightTextWithTypeWriter(textColor.RED + text + textColor.RESET);
        enemy.enemyMove(player);

        if (!(enemy.isAlive())) {
            player.addExp(enemy.getExpReward());
        }
    }

    public void handleVictory(Player player, Enemy enemy) {
        // If both enemy and player died
        if (!enemy.isAlive() && !player.isAlive()) {
            System.out.println("Both you and " + enemy.getName() + " have fallen!");
            Item loot = enemy.dropLoot();
            if (loot != null) {
                System.out.println("The " + enemy.getName() + " dropped " + loot.getName() + ", but you couldn't pick it up.");
            }
            player.resetProgress();
            return;
        }
        // If enemy is dead and player is alive -> normal victory
        if (!enemy.isAlive() && player.isAlive()) {
            clearScreen.clear();
            String text = "You defeated " + enemy.getName() + "!";
            typeWriter.typeWriterFast(textColor.GREEN + text + textColor.RESET); 
            player.addExp(enemy.getExpReward());
            text = "You gained " + enemy.getExpReward() + " Exp from the battle!";
            typeWriter.typeWriterFast(textColor.PURPLE + text + textColor.RESET);
            Item loot = enemy.dropLoot();
            if (loot != null) {
                player.addItem(loot);
                // Print the item's name (Item has getName()) instead of the raw object
                text = "The " + enemy.getName() + " dropped " + loot.getName() + "!";
                typeWriter.typeWriterFast(textColor.YELLOW + text + textColor.RESET);
            } else {
                text = "No loot dropped from " + enemy.getName() + ".";
                typeWriter.typeWriterFast(textColor.YELLOW + text + textColor.RESET);
            }
            return;
        }
    }

    private int readInt() {
        while (true) {
            String line = input.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. \nPlease enter a number: ");
            }
        }
    }

    // Print current combat status (player/enemy HP & stamina) in one place to avoid duplication
    private void printCombatStatus(Player player, Enemy enemy) {
        System.out.println("=====================================================================================================================================================");
        System.out.println(textColor.GREEN + "Player Stats:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + textColor.RESET + textColor.RED + "Enemy Stats:" + textColor.RESET);
        System.out.println(textColor.RED + "Hp: " + player.getHp() + "/" + player.getMaxHp() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Hp: " + enemy.getHp() + "/" + enemy.getMaxHp() + textColor.RESET);
        System.out.println(textColor.ORANGE + "Stamina: " + player.getStamina() + "/" + player.getMaxStamina() + textColor.RESET + " | " + textColor.BLUE + "Mp: " +  player.getMp() + "/" + player.getMaxMp() + textColor.RESET);
    }
}   
