package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class Jm extends Enemy {

    public int windUp = 2;

    public Jm(int townIndex) {
        double levelScaler = (townIndex + 1.0) / 2.0;
        setName("Super Sariayan Jm");
        setMaxHp((int)Math.floor(280.0 * levelScaler));
        setHp((int)Math.floor(280.0 * levelScaler));
        setAttackPower((int)Math.floor(15.0 * levelScaler));
        setDefense((int)Math.floor(15.0 * levelScaler));
        setSpeed(15);

        setExpReward(300);

        setPossibleLoot(new Item[]{new SinigangHipon(), new TropicalBreezeJuice()});
    }

    public void enemyMove(Player player) {


        while(windUp > 0) {
            String windUpText = getName() + " is gathering his ki! (" + windUp + " turns remaining)";
            centerHub.printRightTextWithTypeWriter(windUpText);
            windUp--;
            return;
        }
        if (windUp == 0) {
            if (Math.random() < 0.3) {
                int doubleDamage = getAttackPower() * 2;
                if (doubleDamage < 0) doubleDamage = 0;
                String text = getName() + "'s Kamehameha hits critically!";
                centerHub.printRightTextWithTypeWriter(text);
                player.takeDamage(doubleDamage);
                windUp = 2;
            }
            else {
                String text = getName() + " unleashes a powerful Kamehameha wave!";
                centerHub.printRightTextWithTypeWriter(text);
                int baseDamage = getAttackPower();
                if (baseDamage < 0) baseDamage = 0;
                player.takeDamage(baseDamage);
                windUp = 2;
            }
        }
    }
}