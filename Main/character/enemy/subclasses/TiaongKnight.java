package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class TiaongKnight extends Enemy {

    public TiaongKnight(Player player) {
        setName("Tiaong Knight");
        double levelScaler = Math.max(1, player.getLevel());
        // Late game enemy - Town 5
        int scaledHp = (int)(120 + Math.floor(levelScaler * 2.4));
        int scaledAttack = (int)(25 + Math.floor(levelScaler * 1.1));
        int scaledDefense = (int)(10 + Math.floor(levelScaler * 0.5));
        setMaxHp(scaledHp);
        setHp(scaledHp);
        setAttackPower(scaledAttack);
        setDefense(scaledDefense);
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
        double critChance = 0.35; //35% chance
        if (Math.random() < critChance) {
            attackPower = (int)Math.floor (attackPower * 1.1);
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
        }
        // Deal damage
        player.takeDamage(attackPower);
    }
}
