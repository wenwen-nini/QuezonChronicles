package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class BakeryGremlin extends Enemy{

    public BakeryGremlin(Player player) {
        double levelScaler = (player.getLevel());
        setName("Bakery Gremlin");
        // Mid game enemy - Town 3
        setMaxHp((int) (75 + Math.floor(levelScaler * 12 *1.3)));
        setHp((int) (75 + Math.floor(levelScaler * 12 * 1.3)));
        setAttackPower((int) (14 + Math.floor(levelScaler * 1.2 * 1.3)));
        setDefense((int) (4 + Math.floor(levelScaler * 0.3 * 1.3)));
        setSpeed(8);

        setExpReward(70);

        // Possible loot
        setPossibleLoot(new Item[]{new Pinagong(), new Budin()});
    }

    @Override
    public void enemyMove(Player player) {
    String text = getName() + " throws a Pinagong at " + player.getName() + "!";
    centerHub.printRightTextWithTypeWriter(text);

    int baseDamage = getAttackPower();
    if (baseDamage < 0) baseDamage = 0;

    // Critical hit mechanic
    double critChance = 0.14;
    double critRoll = Math.random();
    if (critRoll < critChance) {
        baseDamage =(int)Math.floor(baseDamage * 1.2); // Critical hit doubles damage
        centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
    }

        player.takeDamage(baseDamage);
    }
}