package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class PortRat extends Enemy{

    public PortRat(Player player){
        setName("Port Rat");
        // Early-mid game enemy - Town 2
        double levelScaler = (player.getLevel());

        setMaxHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (14 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (5 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(8);

        setExpReward(40);

        // Possible loot
        setPossibleLoot(new Item[]{new Chami(), new CocoJam()});
    }

    @Override
    public void enemyMove(Player player) {
    String text = getName() + " swiftly bites the " + player.getName() + "!";
    centerHub.printRightTextWithTypeWriter(text);

    int baseDamage = getAttackPower();
    if (baseDamage < 0) baseDamage = 0;

    // Critical hit mechanic
    double critChance = 0.2;
    double critRoll = Math.random();
    if (critRoll < critChance) {
        baseDamage *= 2; // Critical hit doubles damage
        centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
    }

        player.takeDamage(baseDamage);
    }
}