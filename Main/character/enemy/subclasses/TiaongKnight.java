package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class TiaongKnight extends Enemy {

    public TiaongKnight(Player player) {
        setName("Tiaong Knight");
        double levelScaler = player.getLevel();
        // Late game enemy - Town 5
        setMaxHp((int)(120 + Math.floor(0 * 6 * 1.2)));
        setHp(120);
        setAttackPower(25);
        setDefense(10);
        setSpeed(10);

        setExpReward(120);

        // Possible loot
        setPossibleLoot(new Item[]{new Tinuto(), new Turon()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " heavily swings their sword at " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();

        //Critical Damage
        double critChance = 0.5; //50% chance
        if (Math.random() < critChance) {
            attackPower *= 2;
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }
        // Deal damage
        player.takeDamage(attackPower);
    }
}
