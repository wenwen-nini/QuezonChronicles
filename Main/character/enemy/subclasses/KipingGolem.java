package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class KipingGolem extends Enemy {

    public KipingGolem(Player player) {
        setName("Kiping Golem");
        double levelScaler = (player.getLevel());
        // Early game enemy - Town 1 (East)
        setMaxHp((int) (45 + Math.floor(levelScaler * 6 * 1.0)));
        setHp((int ) (45 + Math.floor(levelScaler * 6 * 1.0)));
        setAttackPower((int) (8 + Math.floor(levelScaler * 0.8 * 1.0)));
        setDefense((int)(2 + Math.floor(levelScaler * 0.2 * 1.0)));
        setSpeed(5);

        setExpReward(50);

        // Possible loot
        setPossibleLoot(new Item[]{new BananaChips(), new KipingDelight()});
    }
    
    @Override
    public void enemyMove(Player player) {
        centerHub.printRightTextWithTypeWriter("The Kiping Golem slams the ground, sending Kiping shards flying!");
        
        int damage = getAttackPower();
        player.takeDamage(damage);
    }
}