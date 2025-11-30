package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class AdobongPusit extends Item {

    public AdobongPusit() {
        setName(textColor.GREEN + "Adobong Pusit" + textColor.RESET);
        setDescription("(A savory squid dish that restores +30hp and grants +3 Defense Boost per turn for 2 turns.)");
    }

    @Override
    public void useItem(Player player) {
        int healthBoost = 30;
        int defenseBoost = 3;
        int duration = 2;

        text = player.getName() + " ate the Adobong Pusit!";
        typeWriter.typeWriterFast(text);
        player.heal(healthBoost);
        player.addTemporaryDefenseBoost(defenseBoost, duration);
    }
}
