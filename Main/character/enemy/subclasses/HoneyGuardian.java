package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class HoneyGuardian extends Enemy {

    public HoneyGuardian(Player player) {
        setName("Honey Guardian");
        double levelScaler = (player.getLevel());
        // Mid-late game enemy - Town 4 (East)
        setMaxHp((int) (80 + Math.floor(levelScaler * 6 *1.9)));
        setHp((int) (80 + Math.floor(levelScaler * 6 *1.9)));
        setAttackPower((int) (15 + Math.floor(levelScaler * 0.8 * 1.9)));
        setDefense((int) (4 + Math.floor(levelScaler * 0.2 * 1.9)));
        setSpeed(9);

        setExpReward(90);

        // Possible loot
        setPossibleLoot(new Item[]{new LambanogLecheFlan(), new MountainHoney()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " summons a horde of angry bees at " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();

        // Critical hit mechanic
        double critChance = 0.05; // 14% chance
        if (Math.random() < critChance) {
            attackPower = (int)Math.floor(attackPower * 1.1);
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }

        // Deal damage
        player.takeDamage(attackPower);

        //poison
        double poisonChance = 0.25;
        if (Math.random() < poisonChance) {
            centerHub.printRightTextWithTypeWriter(text);
            player.applyDebuff("Poison", 2);
        }
    }
}
