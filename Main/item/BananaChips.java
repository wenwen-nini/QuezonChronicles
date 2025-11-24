package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class BananaChips extends Item {

    public BananaChips() {
        setName(textColor.YELLOW + "Banana Chips" + textColor.RESET);
        setDescription("(Crispy banana chips. Quick energy boost grants +2 Speed for one turn.)");
    }

    @Override
    public void useItem(Player player) {

        text = player.getName() + " ate the Banana Chips!";
        typeWriter.typeWriterFast(text);
        text = player.getName() + " increased their speed by 2!";
        typeWriter.typeWriterFast(text);
        player.addSpeed(2);
    }
}