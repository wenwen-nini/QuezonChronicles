package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class SumanMimic extends Enemy {

    public SumanMimic(Player player) {
        setName("Suman Mimic");
        double levelScaler = (player.getLevel());
        // Early-mid game enemy - Town 2 (East)
        setMaxHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (14 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (5 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(8);

        setExpReward(40);

        // Possible loot
        setPossibleLoot(new Item[]{new SumanIbos(), new LopezCocoaDrink()});
    }

    @Override
    public void enemyMove(Player player) {
        centerHub.printRightTextWithTypeWriter("The Suman Mimic wriggles its rice wrap and attacks!");
        int damage = getAttackPower();

        // Attack the player
        player.takeDamage(damage);

        int healAmount = 2;
        String text = getName() + " absorbs energy from the hit and heals " + healAmount + " HP!";
        centerHub.printRightTextWithTypeWriter(text);
        heal(healAmount);
    }
}