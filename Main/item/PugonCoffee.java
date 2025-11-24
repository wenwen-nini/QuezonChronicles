package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class PugonCoffee extends Item {

    public PugonCoffee() {
        setName(textColor.YELLOW + "Pugon Coffee" + textColor.RESET);
        setDescription("(A strong brew that energizes the drinker, granting +2 Speed for the next turn.)");
    }

    @Override
    public void useItem(Player player) {
        int speedBoost = 2;

        text = player.getName() + " drinks the Pugon Coffee!";
        typeWriter.typeWriterFast(text);
        text = player.getName() + " has permanently increased their speed by 2!";
        typeWriter.typeWriterFast(text);
        player.addSpeed(speedBoost);
    }
}