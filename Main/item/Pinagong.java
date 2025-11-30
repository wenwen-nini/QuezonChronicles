package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Pinagong extends Item {

    public Pinagong() {
        setName(textColor.GREEN + "Pinagong" + textColor.RESET);
        setDescription("(A soft bread from Sariaya that restores a small amount of HP.)");
    }

    @Override
    public void useItem(Player player) {
        int healBoost = 30;
        
        text = player.getName() + " ate the Pinagong!";
        typeWriter.typeWriterFast(text);
        player.heal(healBoost);
    }
}