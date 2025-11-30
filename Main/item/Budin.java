package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Budin extends Item {

    public Budin() {
        setName(textColor.RED + "Budin" + textColor.RESET);
        setDescription("(A sweet delicacy that boosts the player's attack power for 2 turns)");
    }

    @Override
    public void useItem(Player player) {
        int attackPowerBoost = 2;
        int duration = 2;

        text = player.getName() + " ate the budin";
        typeWriter.typeWriterFast(text);
        player.addTemporaryAttackBoost(attackPowerBoost, duration);
    }
}
