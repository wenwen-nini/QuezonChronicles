package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class KipingDelight extends Item {

    public KipingDelight() {
        setName(textColor.GREEN + "Kiping Delight" + textColor.RESET);
        setDescription("(A flat, crunchy, and sweet bread that gives health and defense boost for 2 turns.) ");
    }

    @Override
    public void useItem(Player player) {
        int healthBoost = 25;
        int defenseBoost = 4;
        int duration = 2;

        text = player.getName() + " ate the Kiping Delight!";
        typeWriter.typeWriterFast(text);
        player.heal(healthBoost);
        player.addTemporaryDefenseBoost(defenseBoost, duration);
    }
}
