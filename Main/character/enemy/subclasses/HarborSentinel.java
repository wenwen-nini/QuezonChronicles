package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class HarborSentinel extends Enemy {

    public HarborSentinel(Player player) {
        setName("Harbor Sentinel");
        double levelScaler = (player.getLevel());
        // Miniboss - Town 2
        setMaxHp((int) (140 + Math.floor(levelScaler * 12 * 1.3)));
        setHp((int) (140 + Math.floor(levelScaler * 12 * 1.3)));
        setAttackPower((int) (20 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (8 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(6);

        setExpReward(150);

        // Possible loot
        setPossibleLoot(new Item[]{new Chami(), new CocoJam()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " uses Anchor Smash on " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();

        // Critical hit mechanic
        double critChance = 0.1;
        if (Math.random() < critChance) {
            attackPower = (int)Math.floor(attackPower * 1.1);
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }

        // Deal damage
        player.takeDamage(attackPower);
    }
}
