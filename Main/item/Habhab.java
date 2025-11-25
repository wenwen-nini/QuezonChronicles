package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Habhab extends Item {

    public Habhab() {
        setName(textColor.GREEN + "Habhab" + textColor.RESET);
        setDescription("(A famous Lucban noodle dish that restores HP and Stamina/MP.)");
    }

    @Override
    public void useItem(Player player) {
    int healBoost = 25;
    int resourceBoost = 10;

    text = player.getName() + " eats a Habhab!";
    typeWriter.typeWriterFast(text);
    player.heal(healBoost);
    if (!player.getUsesMp()) {
            player.addStamina(resourceBoost);
        }
        else {
        player.addMp(resourceBoost);
        }
    }
}

