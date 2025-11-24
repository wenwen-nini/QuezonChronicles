package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class LopezCocoaDrink extends Item {

    public LopezCocoaDrink() {
        setName(textColor.ORANGE + "Lopez Cocoa Drink" + textColor.RESET);
        setDescription("(+2 defense for 2 turns))");
    }

    @Override
    public void useItem(Player player) {
        int defenseboost = 5;
        int duration = 2;

        text = player.getName() + " drinks the Lopez Cocoa Drink!";
        typeWriter.typeWriterFast(text);
        player.addTemporaryDefenseBoost(5, 2);
    }
}