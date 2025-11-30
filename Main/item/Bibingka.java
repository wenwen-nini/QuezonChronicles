package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Bibingka extends Item {

    public Bibingka() {
        setName(textColor.GREEN + "Bibingka" + textColor.RESET);
        setDescription("(A warm rice cake from Candelaria that restores HP and removes all debuffs.)");
    }

    @Override
    public void useItem(Player player) {
        int healBoost = 25;

        text = player.getName() + " eats a Bibingka!";
        typeWriter.typeWriterFast(text);
        text = player.getName() + " restores a +25 HP and clear all debuffs!";
        typeWriter.typeWriterFast(text);
        player.heal(healBoost);
        player.removeDebuff();
    }
}    

