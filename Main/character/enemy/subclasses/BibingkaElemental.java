package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class BibingkaElemental extends Enemy {

    public BibingkaElemental(Player player) {
        setName("Bibingka Elemental");
        double levelScaler = (player.getLevel());
        // Mid-late game enemy - Town 4
        setMaxHp((int) (80 + Math.floor(levelScaler * 6 *1.9)));
        setHp((int) (80 + Math.floor(levelScaler * 6 *1.9)));
        setAttackPower((int) (15 + Math.floor(levelScaler * 0.8 * 1.9)));
        setDefense((int) (6 + Math.floor(levelScaler * 0.2 * 1.9)));
        setSpeed(7);

        setExpReward(100);

        // Possible loot
        setPossibleLoot(new Item[]{new Bibingka(), new Panutsa()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " casts Flaming Bibingka at " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();

        // Critical hit mechanic
        double critChance = 0.14; // 14% chance
        if (Math.random() < critChance) {
            attackPower = (int)Math.floor(attackPower * 1.3);
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }

        // Deal damage
        player.takeDamage(attackPower);
    }
}