package Main.item;

import Main.character.player.Player;
import Main.styles.textColor.TextColorHub;
import Main.styles.animationHub.TypeWriter;

public abstract class Item {
    private String name, description;
    protected String text;
    protected TextColorHub textColor = new TextColorHub();
    protected TypeWriter typeWriter = new TypeWriter();

    public abstract void useItem(Player player);

    //getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //setters
    public void setName(String newName) {
        name = newName;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }
}