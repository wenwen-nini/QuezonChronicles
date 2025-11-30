package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class FisherRogue extends Enemy {

    public FisherRogue(Player player) {
        setName("Fisher Rogue");
        double levelScaler = (player.getLevel());
        // Mid game enemy - Town 3 (East)
        setMaxHp((int) (70 + Math.floor(levelScaler * 6 * 1.6)));
        setHp((int) (70 + Math.floor(levelScaler * 6 * 1.6)));
        setAttackPower((int) (15 + Math.floor(levelScaler * 0.8 * 1.6)));
        setDefense((int) (6 + Math.floor(levelScaler * 0.2 * 1.6)));
        setSpeed(8);

        setExpReward(60);

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
            attackPower = (int)Math.floor(attackPower * 1.3);
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }

        // Deal damage
        player.takeDamage(attackPower);
    }
}
