package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Eduard extends Enemy{

    public Eduard(int townIndex) {
        double levelScaler = (townIndex + 1.0) / 2.0;
        setName("Aladdin of Mindoro, Eduard");
        setMaxHp((int)Math.floor(85.0 * levelScaler));
        setHp((int)Math.floor(85.0 * levelScaler));
        setAttackPower((int)Math.floor(10.0 * levelScaler));
        setDefense(6);
        setSpeed(8);

        setExpReward(300);

        // Possible loot
        setPossibleLoot(new Item[]{new MountainHoney(), new Turon()});
    }

    @Override
    public void enemyMove(Player player) {
        String text = getName() + " uses Aladdin's Carpet Specter!";
        centerHub.printRightTextWithTypeWriter(textColor.ORANGE + text + textColor.RESET);
        
        String attackText = getName() + " dives on its flying carpet and crashes into " + player.getName() + " with force!";
        centerHub.printRightTextWithTypeWriter(textColor.ORANGE + attackText + textColor.RESET);

        int baseDamage = getAttackPower();// High attack power
        if (baseDamage < 0) baseDamage = 0;

        player.takeDamage(baseDamage);

        // 50% chance to stun player for 2 turns (only if not already stunned and skill is not on cooldown)
        double stunChance = 0.3;
        double stunRoll = Math.random();
        if (stunRoll < stunChance && getSkillUsedTurn() <= 0) {
            // Check if player already has stun debuff
            boolean alreadyStunned = false;
            String[] debuffs = player.getActiveDebuffs();
            for (String debuff : debuffs) {
                if (debuff != null && debuff.equalsIgnoreCase("stun")) {
                    alreadyStunned = true;
                    break;
                }
            }
            
            if (!alreadyStunned) {
                player.applyDebuff("stun", 2);
                centerHub.printRightTextWithTypeWriter(textColor.YELLOW + player.getName() + " is stunned by the impact!" + textColor.RESET);
                setSkillUsedTurn(2);
            }
        }

        // 20% chance to steal a random item from player's inventory
        if (Math.random() < 0.2) {
            stealRandomItem(player);
        }
    }
}