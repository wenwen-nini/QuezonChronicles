package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class TropicalBreezeJuice extends Item {

    public TropicalBreezeJuice() {
        setName(textColor.ORANGE + "Tropical Breeze Juice" + textColor.RESET);
        setDescription("(Begin a heroic journey where every decision alters your fate. Gives you stamina/mp and speed.)");
    }

    @Override
    public void useItem(Player player) {
        int staminaBoost = 15;
        int speed = 3;
        
        //check if the class uses stamina or mp

        text = player.getName() + "drunk the Tropical Breeze Juice!";
        typeWriter.typeWriterFast(text);
        if (!player.getUsesMp()) {
            player.addStamina(staminaBoost);
        }
        else {
            player.addMp(staminaBoost);
        }
        player.addSpeed(speed);
    }
}
