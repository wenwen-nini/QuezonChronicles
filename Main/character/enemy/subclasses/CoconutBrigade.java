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
        setMaxHp((int) (85 + Math.floor(levelScaler * 6 *1.6)));
        setHp((int) (85 + Math.floor(levelScaler * 6 * 1.6)));
        setAttackPower((int) (16 + Math.floor(levelScaler * 0.8 * 1.6)));
        setDefense((int) (6 + Math.floor(levelScaler * 0.2 * 1.6)));
        setSpeed(8);

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
            baseDamage = (int)Math.Floor(baseDamage * 1.2);
        }

        player.takeDamage(baseDamage);
    }
}
