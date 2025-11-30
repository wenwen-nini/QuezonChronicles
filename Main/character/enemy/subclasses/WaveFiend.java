package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class WaveFiend extends Enemy {

    public WaveFiend(Player player) {
        setName("Wave Fiend");
        double levelScaler = (player.getLevel());
        // Late game enemy - Town 5 (East)
        setMaxHp((int) (100 + Math.floor(levelScaler * 6 * 2.2)));
        setHp((int) (100 + Math.floor(levelScaler * 6 * 2.2)));
        setAttackPower((int) (26 + Math.floor(levelScaler * 0.8 * 2.2)));
        setDefense((int) (10 + Math.floor(levelScaler * 0.2 * 2.2)));
        setSpeed(10);

        setExpReward(110);

        // Possible loot
        setPossibleLoot(new Item[]{new SinigangHipon(), new TropicalBreezeJuice()});
    }

   @Override
    public void enemyMove(Player player) {
    String text = getName() + " conjures a towering wave, crashing it down on " + player.getName() + "!";
    centerHub.printRightTextWithTypeWriter(text);

    int attackPower = getAttackPower();

    // Slamming Waves skill: Chance to lower player's attack power (uses debuff system)
    double weakenChance = 0.33; // 20% chance
    if (Math.random() < weakenChance) {
        player.applyDebuff("attack down", 2); // Applies "attack down" debuff for 2 turns
    }

    // Deal damage after applying effects
    player.takeDamage(attackPower);
}


}
