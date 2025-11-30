package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class CoconutBrigade extends Enemy {

    public CoconutBrigade(Player player) {
        double levelScaler = (player.getLevel());
        setName("Coconut Brigade");
        // Mid game enemy - Town 3
        setMaxHp((int) (70 + Math.floor(levelScaler * 6 *1.3)));
        setHp((int) (70 + Math.floor(levelScaler * 6 * 1.3)));
        setAttackPower((int) (8 + Math.floor(levelScaler * 0.8 * 1.3)));
        setDefense((int) (5 + Math.floor(levelScaler * 0.2 * 1.3)));
        setSpeed(5);

        setExpReward(100);

        setPossibleLoot(new Item[]{new Lambanog(), new Pinagong()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " hurls a spinning coconut toward " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);

        int baseDamage = getAttackPower();
        if (baseDamage < 0) baseDamage = 0;

        double critChance = 0.14;
        if(Math.random() < critChance){
            centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
            baseDamage = (int)Math.floor(baseDamage * 1.2);
        }

        player.takeDamage(baseDamage);
    }
}
