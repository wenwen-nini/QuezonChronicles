package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class RealSpecter extends Enemy {

    public RealSpecter(Player player) {
        setName("Real Specter");
        double levelScaler = (player.getLevel());
        // Late game enemy - Town 5 (East)
        setMaxHp((int) (120 + Math.floor(levelScaler * 6 * 2.2)));
        setHp((int) (120 + Math.floor(levelScaler * 6 * 2.2)));
        setAttackPower((int) (26 + Math.floor(levelScaler * 0.8 * 2.2)));
        setDefense((int) (10 + Math.floor(levelScaler * 0.2 * 2.2)));
        setSpeed(10);

        setExpReward(120);

        // Possible loot
        setPossibleLoot(new Item[]{new SinigangHipon(), new Lambanog()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " unleashes a blood-curdling shriek that chills " + player.getName() + " to the bone!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();

        //Critical Damage
        double critChance = 0.35; //35% chance
        if (Math.random() < critChance) {
        attackPower = (int)Math.floor (attackPower * 1.5);
        centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
}
        // Deal damage
        player.takeDamage(attackPower);
    }
}
