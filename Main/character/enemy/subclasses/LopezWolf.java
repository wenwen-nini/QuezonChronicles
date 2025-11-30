package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class LopezWolf extends Enemy{

    public LopezWolf(Player player) {
        setName("Lopez Wolf");
        double levelScaler = (player.getLevel());
        // Early-mid game enemy - Town 2 (East)
        setMaxHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (14 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (5 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(8);

        setExpReward(50);

        // Possible loot
        setPossibleLoot(new Item[]{new LopezCocoaDrink(), new SumanIbos()});
    }

    @Override
    public void enemyMove(Player player) {
    String text = getName() + " bites " + player.getName() + " with its strong jaws!";
    centerHub.printRightTextWithTypeWriter(text);

    int baseDamage = getAttackPower();
    if (baseDamage < 0) baseDamage = 0;

    // Critical hit mechanic
    double critChance = 0.14;
    double critRoll = Math.random();
    if (critRoll < critChance) {
        baseDamage = (int)Math.floor (baseDamage * 1.2);
        centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
    }

        player.takeDamage(baseDamage);
    }
}