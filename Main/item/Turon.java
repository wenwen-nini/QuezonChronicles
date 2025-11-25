package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Turon extends Item {

    public Turon() {
        setName(textColor.RED + "Turon" + textColor.RESET);
        setDescription("(Local delicacy. Gives you additional damage. (OP +15 Attack for 2 turns))");
    }

    @Override
    public void useItem(Player player) {
        int attackBoostAmount = 15;
        int duration = 2;
        
        text = player.getName() + " ate the turon!";
        typeWriter.typeWriterFast(text);

        player.addTemporaryAttackBoost(attackBoostAmount, duration);
    }
}