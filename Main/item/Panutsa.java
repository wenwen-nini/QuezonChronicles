package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Panutsa extends Item{

    public Panutsa() {
        setName(textColor.ORANGE + "Panutsa" + textColor.RESET);
        setDescription("(A sweet treat that boosts your defense by +5 per turn for 2 turns.)");
    }

    @Override
    public void useItem(Player player) {
        int defenseBoost = 5;
        int duration = 2;

        text = player.getName() + " eats the Panutsa!";
        typeWriter.typeWriterFast(text);
        player.addTemporaryDefenseBoost(defenseBoost, duration);
    }
}
