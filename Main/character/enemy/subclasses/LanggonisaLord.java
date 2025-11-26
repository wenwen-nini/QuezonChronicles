package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class LanggonisaLord extends Enemy {

    public LanggonisaLord(Player player) {
        setName("Langgonisa Lord");
        double levelScaler = (player.getLevel());
        // Early game enemy - Town 1
        setMaxHp((int) (45 + Math.floor(levelScaler * 6 * 1.0)));
        setHp((int) (45 + Math.floor(levelScaler * 6 * 1.0)));
        setAttackPower((int) (8 + Math.floor(levelScaler * 0.8 * 1.0)));
        setDefense((int)(2 + Math.floor(levelScaler * 0.2 * 1.0)));
        setSpeed(5);

        setExpReward(35);

        // Possible loot — themed after Lucban’s specialties
        setPossibleLoot(new Item[]{new LongganisangLucban(), new Habhab()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " splashes burning oil! Greasy Splash!";
        centerHub.printRightTextWithTypeWriter(text);

        int baseDamage = getAttackPower();
        if (baseDamage < 0) baseDamage = 0;
        player.takeDamage(baseDamage);

        double attackRoll = 0.5;

        if (Math.random() <= attackRoll) {
            if (getSkillUsedTurn() <= 0) {
                player.applyDebuff("burn", 2);
                setSkillUsedTurn(2);
            }
        }
    }
}
