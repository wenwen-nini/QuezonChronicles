package Main.character.enemy.subclasses;

import Main.item.*;
import Main.character.player.Player;
import Main.character.enemy.Enemy;
import Main.styles.printAlignmentHub.CenterHub;

public class FestivalMask extends Enemy {

    public FestivalMask(Player player) {
        setName("Festival Mask");
        double levelScaler = (player.getLevel());
        // Early game enemy - Town 1 (East)
        setMaxHp(45);
        setHp(45);
        setAttackPower(8);
        setDefense(2);
        setSpeed(5);
        setSkillUsedTurn(2);

        setExpReward(50);

        // Possible loot
        setPossibleLoot(new Item[]{new KipingDelight(), new BananaChips()});
    }
    @Override
    public void enemyMove(Player player) {
        String text = "The Festival Mask floats toward " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(text);
        centerHub.printRightTextWithTypeWriter("It releases a dazzling light that confuses its target!");

        // Base damage
        int damage = getAttackPower();
        if (getSkillUsedTurn() > 0) {
            double confuseChance = 0.4;
            if (Math.random() <= confuseChance){
                player.applyDebuff("confusion", 2);
                player.takeDamage(damage + player.getDefense());
                updateSkillUsedTurn();
            } else {
                player.takeDamage(damage);
            }
        } else {
            setSkillUsedTurn(2); // count down until the skill can be used
        }

        // Optional chance flavor (adds variety)
        double chance = Math.random();
        if (chance <= 0.15) { // 15% chance for extra confusion feedback
            text = player.getName() + " hits themselves in confusion!";
            centerHub.printRightTextWithTypeWriter(text);
            player.takeDamage(3 + player.getDefense());
        }
    }
}
