package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Alwynn extends Enemy{

    public Alwynn(int townIndex) {
        double levelScaler = (townIndex + 1.0) / 2.0;
        setName("All in Alwynn");
        setMaxHp((int)Math.floor(85.0 * levelScaler));
        setHp((int)Math.floor(85.0 * levelScaler));
        setAttackPower((int)Math.floor(10.0 * levelScaler));
        setDefense(6);
        setSpeed(8);

        setExpReward(350);

        // Possible loot
        setPossibleLoot(new Item[]{new Chami(), new CocoJam()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " uses Quezon Zigzag Fury!";
        centerHub.printRightTextWithTypeWriter(textColor.GREEN + text + textColor.RESET);
        
        String confuseText = getName() + " dashes in a chaotic zigzag pattern, confusing " + player.getName() + " before striking multiple times!";
        centerHub.printRightTextWithTypeWriter(textColor.GREEN + confuseText + textColor.RESET);
        centerHub.printRightTextWithTypeWriter(textColor.GREEN + "Marceline toh boy!" + textColor.RESET);

        int numberOfHits = 3;
        int totalDamage = 0;

        // High critical hit chance
        double critChance = 0.4;

        for (int i = 0; i < numberOfHits; i++) {
            int baseDamage = getAttackPower();
            if (baseDamage < 0) baseDamage = 0;

            // Critical hit mechanic
            double critRoll = Math.random();
            if (critRoll < critChance) {
                baseDamage *= 2;
                centerHub.printRightTextWithTypeWriter(textColor.RED + "Critical hit!" + textColor.RESET);
            }

            totalDamage += baseDamage;
            centerHub.printRightTextWithTypeWriter("Hit " + (i + 1) + " deals " + baseDamage + " damage!");
        }

        player.takeDamage(totalDamage);

        double healChance = 0.25;
        double healRoll = Math.random();
        if (healRoll < healChance) {
            int healAmount = (int)(getMaxHp() * 0.12); // Heal 12% of max HP
            heal(healAmount);
            centerHub.printRightTextWithTypeWriter(textColor.GREEN + getName() + " recovers " + healAmount + " HP from the zigzag momentum!" + textColor.RESET);
        }
    }
}