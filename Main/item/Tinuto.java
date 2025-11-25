package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Tinuto extends Item {

    // Constructor
    public Tinuto() {
        setName(textColor.GREEN + "Tinuto" + textColor.RESET);
        setDescription("(A rich delicacy from Tiaong that completely restores HP.)");
    }

    @Override
    public void useItem(Player player) {
        int maxHp = player.getMaxHp();

        text = player.getName() + " eats a Tinuto and restores full HP!";
        typeWriter.typeWriterFast(text);
        player.setHp(maxHp);
    }
}
