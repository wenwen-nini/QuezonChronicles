package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Red extends Enemy{

    //PLAYERS MUST KILL THIS ENEMY BEFORE THE TIMER HITS 0 for balance have a lot of health
    private int tickingTimer = 5; // Countdown timer before explosion

    public Red(int townIndex) {
        double levelScaler = (townIndex + 1.0) / 2.0;
        setName("Bomb Devil Red");
        setMaxHp((int)Math.floor(85.0 * levelScaler));
        setHp((int)Math.floor(85.0 * levelScaler));
        setAttackPower((int)Math.floor(16.0 * levelScaler));
        setDefense(6);
        setSpeed(8);

        setExpReward(500);

        // Possible loot
        setPossibleLoot(new Item[]{new Lambanog(), new PugonCoffee()});
    }

    @Override
    public void enemyMove(Player player) {
        // Decrease timer
        tickingTimer--;
        
        if (tickingTimer > 0) {
            String text = getName() + " is ticking! Timer: " + tickingTimer + " turns remaining!";
            centerHub.printRightTextWithTypeWriter(textColor.YELLOW + text + textColor.RESET);
            
            String warningText = getName() + " glows ominously, getting ready to explode!";
            centerHub.printRightTextWithTypeWriter(textColor.RED + warningText + textColor.RESET);
            
        } else {
            // Timer reached 0 - EXPLOSION!
            String explosionText = getName() + " EXPLODES!";
            centerHub.printRightTextWithTypeWriter(textColor.RED + explosionText + textColor.RESET);
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Deal massive damage to player
            int explosionDamage = 999;
            String damageText = "The explosion deals devastating damage to " + player.getName() + "!";
            centerHub.printRightTextWithTypeWriter(textColor.RED + damageText + textColor.RESET);
            player.takeDamage(explosionDamage);
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Red also dies from the explosion
            setHp(0);
            String suicideText = getName() + " perishes in the explosion!";
            centerHub.printRightTextWithTypeWriter(textColor.YELLOW + suicideText + textColor.RESET);
        }
    }
}