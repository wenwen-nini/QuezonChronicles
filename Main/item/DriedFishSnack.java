package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;

public class DriedFishSnack extends Item {

    public DriedFishSnack() {
        setName(textColor.RED + "Dried Fish Snack" + textColor.RESET);
        setDescription("(Dried Fish Snack gives you additional damage for 2 turns.)");
    }

    @Override
    public void useItem(Player player) {
        int attackBoostAmount = 2;

        text = player.getName() + " ate the Dried Fish Snack!";
        typeWriter.typeWriterFast(text);
        player.addTemporaryAttackBoost(attackBoostAmount, 2);
    }
}