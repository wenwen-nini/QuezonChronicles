package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class Lambanog extends Item {

    public Lambanog() {
        setName(textColor.BLUE + "Lambanog" + textColor.RESET);
        setDescription("(A potent coconut spirit prized in local taverns, often used in celebrations or risky gambles throughout the realm)");
    }

    @Override
    public void useItem(Player player) {
        int staminaBoost = 15;
        
        //check if the class uses stamina or mp
        text = player.getName() + "drinks the Lambanog!";
        typeWriter.typeWriterFast(text);
        if (!player.getUsesMp()) {
            player.addStamina(staminaBoost);
        }
        else {
            player.addMp(staminaBoost);
        }
    }
}
