package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Chami extends Item {

    // Constructor
    public Chami() {
        setName(textColor.GREEN + "Chami" + textColor.RESET);
        setDescription("(A sweet and savory noodle dish from Lucena that restores a good amount of HP.)");
    }

    @Override
    public void useItem(Player player) {
    int healBoost = 40;

    text = player.getName() + " eats a Chami and restores a +40 HP!";
    typeWriter.typeWriterFast(text);
    player.heal(healBoost);
    }
}
