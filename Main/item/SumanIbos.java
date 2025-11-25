package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class SumanIbos extends Item {

    public SumanIbos() {
        setName(textColor.GREEN + "Suman sa Ibos" + textColor.RESET);
        setDescription("(A sticky rice treat that heals for 30 health.)");
    }

    @Override
    public void useItem(Player player) {
        int healBoost = 30;

        text = player.getName() + " ate the Suman sa Ibos!";
        typeWriter.typeWriterFast(text);
        player.heal(healBoost);
    }
}
