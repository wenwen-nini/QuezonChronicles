package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class QueenAmihan extends Enemy {

    public QueenAmihan(Player player) {
        setName("Queen Amihan");
        double levelScaler = (player.getLevel());
        // Final boss - increased health and attack, unique wave heal ability remains
        setMaxHp((int) (140 + Math.floor(levelScaler * 10 * 1.8)));
        setHp((int) (140 + Math.floor(levelScaler * 10 * 2.2)));
        setAttackPower((int) (30 + Math.floor(levelScaler * 0.8 * 2.2)));
        setDefense((int) (12 + Math.floor(levelScaler * 0.2 * 2.2)));
        setSpeed(20);

        setExpReward(3000);

        // Possible loot
        setPossibleLoot(new Item[]{new SinigangHipon(), new TropicalBreezeJuice()});
    }

   @Override
    public void enemyMove(Player player) {
        String text = getName() + " conjures a towering wave, crashing it down on " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int attackPower = getAttackPower();
        
        // Deal damage after applying effects
        player.takeDamage(attackPower);
        heal(getAttackPower() / 2);
    }
}
