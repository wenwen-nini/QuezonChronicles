package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class HeritageWraith extends Enemy{

    public HeritageWraith(Player player) {
        setName("Heritage Wraith");
        double levelScaler = (player.getLevel());
        // Mid game enemy - Town 3
        setMaxHp((int) (85 + Math.floor(levelScaler * 6 *1.6)));
        setHp((int) (85 + Math.floor(levelScaler * 6 * 1.6)));
        setAttackPower((int) (16 + Math.floor(levelScaler * 0.8 * 1.6)));
        setDefense((int) (6 + Math.floor(levelScaler * 0.2 * 1.6)));
        setSpeed(8);

        setExpReward(100);

        // Possible loot
        setPossibleLoot(new Item[]{new Pinagong(), new Budin()});
    }

    @Override
    public void enemyMove(Player player) {
    String text = getName() + " lashes out with its shadowy claws at " + player.getName() + "!";
    centerHub.printRightTextWithTypeWriter(text);

    int baseDamage = getAttackPower();
    if (baseDamage < 0) baseDamage = 0;

    // Critical hit mechanic
    double critChance = 0.14;
    double critRoll = Math.random();
    if (critRoll < critChance) {
        baseDamage = (int)Math.floor(baseDamage * 1.2); // Critical hit doubles damage
        centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
    }

    //Defense debuff
    int defenseReduce = 1;
    int duration = 1;

        player.addDebuff(defenseReduce, duration);
        player.takeDamage(baseDamage);
    }
}