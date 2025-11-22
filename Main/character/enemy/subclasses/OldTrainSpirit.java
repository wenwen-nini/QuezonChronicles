package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class OldTrainSpirit extends Enemy {

    public OldTrainSpirit(Player player) {
        setName("Old Train Spirit");
        double levelScaler = (player.getLevel());
        // Tougher miniboss with stun and charge
        setMaxHp((int) (160 + Math.floor(levelScaler * 6 * 1.3)));
        setHp((int) (160 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (28 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (11 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(5);

        setExpReward(400);

        // Possible loot
        setPossibleLoot(new Item[]{new Tinuto(), new PugonCoffee()});
    }

    @Override
    public void enemyMove(Player player) {
        centerHub.printRightTextWithTypeWriter("The Old Train Spirit charges with unstoppable force!");
        centerHub.printRightTextWithTypeWriter("It uses its signature move: 'Iron Charge'!");

        // Calculate base damage
        int damage = getAttackPower() + 5; // Extra impact for the charge
        player.takeDamage(damage);

        // 25% chance to stun the player for 1 turn
        double chance = Math.random();
        if (chance < 0.25) {
            player.applyDebuff("stun", 1);
        }

        // Cool flavor feedback
        String text = getName() + " rattles the ground as it passes by!";
        centerHub.printRightTextWithTypeWriter(text);
    }
}