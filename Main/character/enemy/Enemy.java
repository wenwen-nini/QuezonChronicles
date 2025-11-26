package Main.character.enemy;

import Main.character.Character;
import Main.character.player.Player;
import Main.character.player.classes.Bruid;
import Main.character.player.classes.Mage;
import Main.character.player.classes.TagalogMonk;
import Main.character.player.classes.Thief;
import Main.character.player.classes.Warrior;
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

    // Scaling support
    private boolean baseStatsCaptured = false;
    private boolean baseStatsAdjusted = false;
    private int baseMaxHp;
    private int baseAttackPower;
    private int baseDefense;
    private int baseSpeed;

    public abstract void enemyMove(Player player);

    private void captureBaseStatsIfNeeded() {
        if (!baseStatsCaptured) {
            baseStatsCaptured = true;
            baseMaxHp = getMaxHp();
            baseAttackPower = getAttackPower();
            baseDefense = getDefense();
            baseSpeed = getSpeed();
        }
    }

    public void scaleToPlayer(Player player, int townIndex) {
        if (player == null) {
            return;
        }
        captureBaseStatsIfNeeded();

        int playerLevel = Math.max(1, player.getLevel());
        if (isSpecialEncounter()) {
            return;
        }
        applySubclassBaseReduction(townIndex);

        double levelFactor = 1.0 + 0.028 * (playerLevel - 1);
        double[] townMultipliers = getTownMultipliers(townIndex);

        double targetHp = player.getMaxHp() * townMultipliers[0] * levelFactor;
        double targetAttack = player.getAttackPower() * townMultipliers[1] * levelFactor;
        double targetDefense = Math.max(0, player.getDefense() * townMultipliers[2] * levelFactor);
        int newSpeed = (int)Math.max(1, Math.round(baseSpeed + Math.min(3, townIndex)));

        int newMaxHp = (int)Math.max(1, Math.round(Math.max(baseMaxHp, targetHp)));
        int newAttack = (int)Math.max(1, Math.round(Math.max(baseAttackPower, targetAttack)));
        int newDefense = (int)Math.max(0, Math.round(Math.max(baseDefense, targetDefense)));

        double[] townBaseWeakness = getTownBaseWeakness(townIndex);
        newMaxHp = (int)Math.max(1, Math.round(newMaxHp * townBaseWeakness[0]));
        newAttack = (int)Math.max(1, Math.round(newAttack * townBaseWeakness[1]));
        newDefense = (int)Math.max(0, Math.round(newDefense * townBaseWeakness[2]));

        double[] classBiases = getClassBiases(player);
        newMaxHp = (int)Math.max(1, Math.round(newMaxHp * classBiases[0]));
        newAttack = (int)Math.max(1, Math.round(newAttack * classBiases[1]));
        newDefense = (int)Math.max(0, Math.round(newDefense * classBiases[2]));
        newSpeed = (int)Math.max(1, Math.round(newSpeed * classBiases[3]));

        double damageMultiplier = getDamageMultiplierForTown(townIndex);
        newAttack = (int)Math.max(1, Math.round(newAttack * damageMultiplier));

        int defenseBuffer = Math.max(1, Math.min(9, 2 + (playerLevel / 4) + Math.max(0, townIndex - 1)));
        int minAttack = player.getDefense() + defenseBuffer;
        if (newAttack < minAttack) {
            newAttack = minAttack;
        }

        setMaxHp(newMaxHp);
        setHp(newMaxHp);
        setAttackPower(newAttack);
        setDefense(newDefense);
        setSpeed(newSpeed);
    }

    protected boolean isSpecialEncounter() {
        return false;
    }

    private double[] getClassBiases(Player player) {
        double hpBias = 1.0;
        double atkBias = 1.0;
        double defBias = 1.0;
        double spdBias = 1.0;

        if (player instanceof Mage) {
            hpBias = 0.94;
            atkBias = 0.9;
            defBias = 0.92;
            spdBias = 1.05;
        } else if (player instanceof Thief) {
            hpBias = 0.96;
            atkBias = 0.92;
            defBias = 0.93;
            spdBias = 1.08;
        } else if (player instanceof Bruid) {
            hpBias = 0.98;
            atkBias = 0.95;
            defBias = 0.98;
            spdBias = 1.0;
        } else if (player instanceof TagalogMonk) {
            hpBias = 1.0;
            atkBias = 1.0;
            defBias = 1.02;
            spdBias = 1.02;
        } else if (player instanceof Warrior) {
            hpBias = 1.03;
            atkBias = 1.1;
            defBias = 1.05;
            spdBias = 0.98;
        }

        return new double[]{hpBias, atkBias, defBias, spdBias};
    }

    private void applySubclassBaseReduction(int townIndex) {
        if (baseStatsAdjusted) {
            return;
        }
        int capped = Math.max(0, Math.min(townIndex, 4));
        double[] hpFactors = {0.9, 0.94, 0.98, 1.02, 1.05};
        double[] atkFactors = {0.86, 0.9, 0.95, 1.0, 1.04};
        double[] defFactors = {0.86, 0.9, 0.95, 1.0, 1.03};
        baseMaxHp = (int)Math.max(1, Math.round(baseMaxHp * hpFactors[capped]));
        baseAttackPower = (int)Math.max(1, Math.round(baseAttackPower * atkFactors[capped]));
        baseDefense = (int)Math.max(0, Math.round(baseDefense * defFactors[capped]));
        baseStatsAdjusted = true;
    }

    private double[] getTownMultipliers(int townIndex) {
        int capped = Math.max(0, Math.min(townIndex, 4));
        double[] hpMultipliers = {0.65, 0.85, 1.0, 1.15, 1.3};
        double[] atkMultipliers = {0.7, 0.88, 1.03, 1.18, 1.33};
        double[] defMultipliers = {0.85, 0.95, 1.05, 1.15, 1.25};
        return new double[]{hpMultipliers[capped], atkMultipliers[capped], defMultipliers[capped]};
    }

    private double[] getTownBaseWeakness(int townIndex) {
        int capped = Math.max(0, Math.min(townIndex, 2));
        double[] hpFactors = {0.84, 0.92, 1.0};
        double[] atkFactors = {0.8, 0.88, 1.0};
        double[] defFactors = {0.85, 0.92, 1.0};
        return new double[]{hpFactors[capped], atkFactors[capped], defFactors[capped]};
    }

    private double getDamageMultiplierForTown(int townIndex) {
        int capped = Math.max(0, Math.min(townIndex, 4));
        double[] multipliers = {0.82, 0.88, 0.94, 0.98, 1.02};
        return multipliers[capped];
    }

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
                String text = "Stun wore off!";
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