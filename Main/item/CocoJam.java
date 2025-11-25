package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class CocoJam extends Item {

    public CocoJam() {
        setName(textColor.ORANGE + "Coco Jam" + textColor.RESET);
        setDescription("(A sweet Lucena delicacy that restores Stamina/Mp.)");
    }

    @Override
    public void useItem(Player player) {

        int resourceBoost = 15;
        
        //check if the class uses stamina or mp
        
        text = player.getName() + " eats a Coco Jam!";
        typeWriter.typeWriterFast(text);

        if (!player.getUsesMp()) {
            player.addStamina(resourceBoost);
        }
        else {
        player.addMp(resourceBoost);
        }
    }
}
