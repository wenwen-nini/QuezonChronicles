package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class ForestNymph extends Enemy {

    public ForestNymph(Player player) {
        setName("Forest Nymph");
        double levelScaler = (player.getLevel());
        // Mid-late game enemy - Town 4 (East)
        setMaxHp((int) (80 + Math.floor(levelScaler * 6 *1.9)));
        setHp((int) (80 + Math.floor(levelScaler * 6 *1.9)));
        setAttackPower((int) (18 + Math.floor(levelScaler * 0.8 * 1.9)));
        setDefense((int) (4 + Math.floor(levelScaler * 0.2 * 1.9)));
        setSpeed(9);

        setExpReward(85);

        // Possible loot
        setPossibleLoot(new Item[]{new LambanogLecheFlan(), new MountainHoney()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " glows with green light as her wounds fade.";
        centerHub.printRightTextWithTypeWriter(text);

        //healherself
        heal(12);
        player.takeDamage(getAttackPower());
    }
}
