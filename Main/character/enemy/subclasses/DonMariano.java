package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class DonMariano extends Enemy {

    public DonMariano(Player player) {
        setName("Don Mariano");
        double levelScaler = player.getLevel();
        // Increased boss durability and loot value
        setMaxHp((int) (240 + Math.floor(player.getLevel() * 10 * 2.2)));
        setHp((int) (240 + Math.floor(player.getLevel() * 10 * 2.2)));
        setAttackPower((int) (28 + Math.floor(player.getLevel() * 0.8 * 2.2)));
        setDefense((int) (12 + Math.floor(player.getLevel() * 0.2 * 2.2)));
        setSpeed(6);
        setSkillUsedTurn(2);

        setExpReward(2000);

        // Possible loot
        setPossibleLoot(new Item[]{new Tinuto(), new PugonCoffee()});
    }
    @Override
    public void enemyMove(Player player) {
        
        String text = getName() + " uses 'Greed's Flame'!";
        centerHub.printRightTextWithTypeWriter(text);

        // Base damage
        int damage = getAttackPower();
        player.takeDamage(damage);        

        //Roll chances for effect
        double attackRoll = 0.5;
        double healChance = 0.15;

        if (Math.random() <= attackRoll) {
            if (getSkillUsedTurn() <= 0) {
                player.applyDebuff("burn", 2);
                setSkillUsedTurn(2);
            }
            else {
                updateSkillUsedTurn();
            }
        }

        if (Math.random() <= healChance) {
            int healAmount = (int) (player.getAttackPower() * 0.5);
            heal(healAmount);
            centerHub.printRightTextWithTypeWriter("Don Mariano absorbs " + String.valueOf(healAmount) + " damage from " + player.getName() + "!");
        }

        if (getSkillUsedTurn() > 0) {
            updateSkillUsedTurn();
        }
    }
}