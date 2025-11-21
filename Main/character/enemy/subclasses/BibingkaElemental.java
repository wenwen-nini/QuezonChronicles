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
        setMaxHp((int) (100 + Math.floor(levelScaler * 6 *1.9)));
        setHp((int) (100 + Math.floor(levelScaler * 6 *1.9)));
        setAttackPower((int) (21 + Math.floor(levelScaler * 0.8 * 1.9)));
        setDefense((int) (8 + Math.floor(levelScaler * 0.2 * 1.9)));
        setSpeed(9);

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
        double critChance = 0.2; // 20% chance
        if (Math.random() < critChance) {
            attackPower *= 2;
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }

        // Deal damage
        player.takeDamage(attackPower);
    }
}