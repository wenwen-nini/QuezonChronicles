package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class SeaWidow extends Enemy {

    public SeaWidow(Player player) {
        setName("Sea Widow");
        double levelScaler = (player.getLevel());
        // Mid game enemy - Town 3 (East)
<<<<<<< HEAD
        setMaxHp((int) (70 + Math.floor(levelScaler * 6 * 1.6)));
        setHp((int) (70 + Math.floor(levelScaler * 6 * 1.6)));
        setAttackPower((int) (15 + Math.floor(levelScaler * 0.8 * 1.6)));
        setDefense((int) (6 + Math.floor(levelScaler * 0.2 * 1.6)));
=======
        setMaxHp((int) (85 + Math.floor(levelScaler * 6 * 1.3)));
        setHp((int) (85 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (12 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (6 + Math.floor(levelScaler * 0.2 * 1.3)));
>>>>>>> 2388a47946fb0ebdc282c68dddf68351cafdfd99
        setSpeed(8);

        setExpReward(70);

        // Possible loot
        setPossibleLoot(new Item[]{new AdobongPusit(), new DriedFishSnack()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " casts the Gleaming Deadly Hook at " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();

        double weakenChance = 0.1; // 10% chance
        if (Math.random() < weakenChance) {
            player.applyDebuff("attack down", 2); // or any value you want to decrease
        }

        player.takeDamage(attackPower);
    }
}
