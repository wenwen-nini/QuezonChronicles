package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class CalauagBandit extends Enemy {

    public CalauagBandit(Player player) {
        setName("Calauag Bandit");
        double levelScaler = (player.getLevel());
        // Mid game enemy - Town 3 (East)
        setMaxHp((int) (85 + Math.floor(levelScaler * 6 * 1.3)));
        setHp((int) (85 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (12 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (6 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(8);

        setExpReward(75);

        // Possible loot
        setPossibleLoot(new Item[]{new AdobongPusit(), new DriedFishSnack()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " casts the Gleaming Deadly Hook at " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();

        // Critical hit mechanic
        double critChance = 0.14; // 14% chance
        if (Math.random() < critChance) {
            attackPower = (int)Math.floor(attackPower * 1.2);
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }

        // Deal damage
        player.takeDamage(attackPower);
    }
}
