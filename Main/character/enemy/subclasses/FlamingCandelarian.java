package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class FlamingCandelarian extends Enemy {

    public FlamingCandelarian(Player player) {
        setName("Flaming Candelarian");
        double levelScaler = (player.getLevel());
        // Mid-late game enemy - Town 4
        setMaxHp((int) (90 + Math.floor(levelScaler * 6 *1.9)));
        setHp((int) (90 + Math.floor(levelScaler * 6 *1.9)));
        setAttackPower((int) (17 + Math.floor(levelScaler * 0.8 * 1.9)));
        setDefense((int) (7 + Math.floor(levelScaler * 0.2 * 1.9)));
        setSpeed(9);

        setExpReward(100);

        setPossibleLoot(new Item[]{new Bibingka(), new Lambanog()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " releases a burst of flame from its torch!";
        centerHub.printRightTextWithTypeWriter(text);

        int baseDamage = getAttackPower();
        if (baseDamage < 0) baseDamage = 0;

        double critChance = 0.14;
        if (Math.random() < critChance) {
            baseDamage = (int)Math.floor(baseDamage * 1.3);
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }

        player.takeDamage(baseDamage);
    }
}
