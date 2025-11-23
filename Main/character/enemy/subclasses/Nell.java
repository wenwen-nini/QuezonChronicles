package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Nell extends Enemy{

    public Nell(int townIndex) {
        double levelScaler = (townIndex + 1.0) / 2.0;
        setName("Sariayan Ninja Nell");
        setMaxHp((int)Math.floor(85.0 * levelScaler));
        setHp((int)Math.floor(85.0 * levelScaler));
        setAttackPower((int)Math.floor(5.0 * levelScaler));
        setDefense(3);
        setSpeed(8);

        setExpReward(300);

        // Possible loot
        setPossibleLoot(new Item[]{new BananaChips(), new DriedFishSnack()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " throws 5 star shurikens at " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(textColor.CYAN + text + textColor.RESET);

        int baseDamage = getAttackPower();
        if (baseDamage < 0) baseDamage = 0;

        player.takeDamage(baseDamage);
    }

    @Override
    public void takeDamage(int amount) {
        double dodgeChance = 0.4;
        double dodgeRoll = Math.random();
        
        if (dodgeRoll < dodgeChance) {
            centerHub.printRightTextWithTypeWriter(textColor.CYAN + getName() + " dodged the attack!" + textColor.RESET);
            centerHub.printRightTextWithTypeWriter(textColor.CYAN + "You can't catch me!" + textColor.RESET);
            String text = getName() + " took 0 damage.";
            typeWriter.typeWriterFast(text);
        } else {
            int reducedDamage = Math.max(0, amount - getDefense());
            setHp(getHp() - reducedDamage);
            String text = getName() + " took " + String.valueOf(reducedDamage) + " damage.";
            typeWriter.typeWriterFast(text);
            if (getHp() <= 0) {
                setHp(0);
            }
        }
    }
}