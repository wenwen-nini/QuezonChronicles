package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class TanimGuardian extends Enemy {

    private CenterHub centerHub = new CenterHub();

    public TanimGuardian(Player player) {
        setName("Tanim Guardian");
        double levelScaler = player.getLevel();
        // Early game enemy - Town 1
        setMaxHp((int) (45 + Math.floor(0 * 6 * 1.0)));
        setHp((int) (45 + Math.floor(0 * 6 * 1.0)));
        setAttackPower((int) (8 + Math.floor(0 * 0.2 * 1.0)));
        setDefense((int)(2 + Math.floor(0 * 0.2 * 1.0)));
        setSpeed(5);

        setExpReward(40);

        // Possible loot
        setPossibleLoot(new Item[]{new Habhab(), new LongganisangLucban()});
    }

    @Override
    public void enemyMove(Player player) {
    String text = getName() + " attacks with a vine!";
    centerHub.printRightTextWithTypeWriter(text);

    int baseDamage = getAttackPower();
    if (baseDamage < 0) baseDamage = 0;
    
    // Critical hit mechanic
    double critChance = 0.2;
    double critRoll = Math.random();
    if (critRoll < critChance) {
        baseDamage *= 2; // Critical hit doubles damage
        centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
    }

    player.takeDamage(baseDamage);
    }
}
