package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class LucenaPirate extends Enemy{

    public LucenaPirate(Player player) {
        setName("Lucena Pirate");
        // Early-mid game enemy - Town 2
        double levelScaler = (player.getLevel());
        setMaxHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (10 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (5 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(8);

        setExpReward(50);

        // Possible loot
        setPossibleLoot(new Item[]{new Chami(), new CocoJam()});
    }

    @Override
    public void enemyMove(Player player) {
    String text = getName() + " lunges towards " + player.getName() + " fiercely!";
    centerHub.printRightTextWithTypeWriter(text);

    int baseDamage = getAttackPower();
    if (baseDamage < 0) baseDamage = 0;

    // Critical hit mechanic
    double critChance = 0.06;
    if (Math.random() < critChance) {
        baseDamage = (int)Math.floor (baseDamage * 1.1);
        centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
    }

        player.takeDamage(baseDamage);
    }
}