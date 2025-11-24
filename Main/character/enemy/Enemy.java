package Main.character.enemy;

import Main.character.Character;
import Main.character.player.Player;
import Main.item.*;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.animationHub.TypeWriter;
import Main.styles.textColor.TextColorHub;

public abstract class Enemy extends Character {

    protected CenterHub centerHub = new CenterHub();
    protected TypeWriter typeWriter = new TypeWriter();
    protected TextColorHub textColor = new TextColorHub();

    private int expReward;
    private Item[] possibleLoot = new Item[2];
    private int skillUsedTurn;

    public abstract void enemyMove(Player player);

    public Item dropLoot() {
        if (possibleLoot == null || possibleLoot.length == 0) {
            return null;
        }

        double roll = Math.random();
        if (roll < 0.4) {
            return possibleLoot[0];
        }
        else if (roll < 0.8 && roll >= 0.4) {
            return possibleLoot[1];
        }
        else {
            return null;
        }
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int amount) {
        expReward = amount;
    }

    public Item[] getPossibleLoot() {
        return possibleLoot;
    }

    public void setPossibleLoot(Item[] newPossibleLoot) {
        possibleLoot = newPossibleLoot;
    }

    public int getSkillUsedTurn() {
        return skillUsedTurn;
    }

    public void setSkillUsedTurn(int turns) {
        skillUsedTurn = turns;
    }

    public void updateSkillUsedTurn() {
        skillUsedTurn--;
    }

    public void takeDamage(int amount) {
        int reducedDamage = Math.max(0, amount - getDefense());
        setHp(getHp() - reducedDamage);
        String text = getName() + " took " + String.valueOf(reducedDamage) + " damage.";
        typeWriter.typeWriterFast(text);
        if (getHp() <= 0) {
            setHp(0);
        }
    }

    public void heal(int amount) {
    String text = getName() + " healed " + amount + " HP.";
    centerHub.printRightTextWithTypeWriter(text);
    setHp(getHp() + amount);
    if (getHp() >= getMaxHp()) {
      setHp(getMaxHp());
    }
  }

    //Debuff Methods
  public void applyDebuff(String type, int turns) {
    for (int i = 0; i < activeDebuffs.length; i++) {
        if (activeDebuffs[i] == null) {
            activeDebuffs[i] = type;
            debuffTurns[i] = turns;
            String text = getName() + " is afflicted with " + type + " for " + turns + " turns!";
            typeWriter.typeWriterFast(text);
            return;
        }
    }
    typeWriter.typeWriterFast("Too many debuffs active!");
  }

  public void updateDebuffs() {
    for (int i = 0; i < activeDebuffs.length; i++) {
        if (activeDebuffs[i] != null) {
            debuffTurns[i]--;
            applyDebuffEffect(activeDebuffs[i]);

            if (debuffTurns[i] <= 0) {
                String text = " wore off!";
                typeWriter.typeWriterFast(text);
                activeDebuffs[i] = null;
            }
        }
    }
  }

  public void applyDebuffEffect(String debuff) {
        String text;
        switch (debuff.toLowerCase()) {
            case "poison":
                text = getName() + " takes 2 poison damage!";
                typeWriter.typeWriterFast(text);
                takeDamage(2);
                break;
            case "burn":
                text = getName() + " takes 2 burn damage!";
                typeWriter.typeWriterFast(text);
                takeDamage(2);
                break;
            case "absorb":
                text = getName() + " feels weaker! Health had been absored by 2";
                typeWriter.typeWriterFast(text);
                takeDamage(2);
                break;
            case "defense down":
                text = getName() + " feels weaker! Defense temporarily reduced.";
                typeWriter.typeWriterFast(text);
                setDefense(getDefense() - 1);
                break;
            case "attack down":
                text = getName() + " feels their strength fade!";
                typeWriter.typeWriterFast(text);
                setAttackPower(getAttackPower() - 2);
                break;
            case "stun":
                text = getName() + " is stunned and cannot move!";
                typeWriter.typeWriterFast(text);
                break;
            case "confusion":
                text = getName() + " is confused by the masks!";
                typeWriter.typeWriterFast(text);
                break;             
        }
    }

    public void stealRandomItem(Player player) {
        Item[] inventory = player.getInventory();
        int nonEmptyCount = 0;
        for (Item item : inventory) {
            if (item != null) {
                nonEmptyCount++;
            }
        }

        if (nonEmptyCount == 0) {
            String text = getName() + " tried to steal an item, but " + player.getName() + "'s inventory is empty!";
            centerHub.printRightTextWithTypeWriter(textColor.YELLOW + text + textColor.RESET);
            return;
        }

        int randomIndex;
        do {
            randomIndex = (int)(Math.random() * inventory.length);
        } while (inventory[randomIndex] == null);

        Item stolenItem = inventory[randomIndex];
        inventory[randomIndex] = null;

        String text = getName() + " just stole a " + stolenItem.getName() + " from " + player.getName() + "!";
        centerHub.printRightTextWithTypeWriter(textColor.YELLOW + text + textColor.RESET);
    }
}