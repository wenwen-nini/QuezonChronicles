package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class Jm extends Enemy {

    public int windUp = 2;

    public Jm() {
        setName("Super Sariayan Jm");
        setMaxHp(280);
        setHp(280);
        setAttackPower(12);
        setDefense(12);
        setSpeed(15);

        setExpReward(300);

        setPossibleLoot(new Item[]{new SinigangHipon(), new TropicalBreezeJuice()});
    }

    @Override
    public void enemyMove(Player player) {


        while(windUp > 0) {
            String windUpText = getName() + " is gathering his ki! (" + windUp + " turns remaining)";
            centerHub.printRightTextWithTypeWriter(windUpText);
            windUp--;
            return;
        }
        if (windUp == 0) {
            String text = getName() + " unleashes a powerful Kamehameha wave!";
            centerHub.printRightTextWithTypeWriter(text);
            int baseDamage = getAttackPower();
            if (baseDamage < 0) baseDamage = 0;

            player.takeDamage(baseDamage);

            windUp = 2;
        }
    }
}