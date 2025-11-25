package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class MountainHoney extends Item{

    public MountainHoney() {
        setName(textColor.ORANGE + "Mountain Honey" + textColor.RESET);
        setDescription("(A delicous Mountain Honey that gives you stamina or mp)");
    }

    @Override
    public void useItem(Player player) {
        int staminaBoost = 15;
        
        //check if the class uses stamina or mp

        text = player.getName() + "ate the Mountain Honey!";
        typeWriter.typeWriterFast(text);
        if (!player.getUsesMp()) {
            player.addStamina(staminaBoost);
        }
        else {
            player.addMp(staminaBoost);
        }
    }
}
