package Main.character.player;

import Main.item.*;
import Main.character.Character;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.animationHub.TypeWriter;
import Main.styles.textColor.TextColorHub;

public abstract class Player extends Character {
  private int experience, level = 1, nextExpLevel = 100;
  public String description;

  // Path tracking
  private String chosenPath = null; // "west" or "east"
  private int currentTownIndex = 0; // Track progress through the path
  private int enemiesDefeatedInTown = 0; // Track enemies defeated in current town

  // Base stats (captured at character creation) to allow proper reset
  private int baseMaxHp = 0;
  private int baseStamina = 0;
  private int baseMaxStamina = 0;
  private int baseMp = 0;
  private int baseMaxMp = 0;
  private int baseDefense = 0;
  private int baseAttackPower = 0;
  private int baseSpeed = 0;
  private boolean usesMp = true;
  
  //Effect attributes
  private int attackBoostAmount = 0, attackBoostTurn = 0;
  private int defenseBoostAmount = 0, defenseBoostTurn = 0;
  private int defenseDebuffAmount = 0, defenseDebuffTurn = 0;

  private static Item[] inventory = new Item[10];
  private final String[] attackMoves = new String[4];
  private boolean lastActionSucceeded = false;

  protected CenterHub centerHub = new CenterHub();
  protected TextColorHub textColor = new TextColorHub();
  protected TypeWriter typeWriter = new TypeWriter();

	public abstract void showStats();
  public abstract void levelStats();
  public abstract void useMoves(int moveNumber, Character target);

	//getter
  public boolean getUsesMp() {
    return usesMp;
  }

  public boolean getUsesStamina() {
    return !usesMp;
  }

  public String getChosenPath() {
    return chosenPath;
  }

  public void setChosenPath(String path) {
    this.chosenPath = path;
  }

  public int getCurrentTownIndex() {
    return currentTownIndex;
  }

  public void setCurrentTownIndex(int index) {
    this.currentTownIndex = index;
  }

  public int getEnemiesDefeatedInTown() {
    return enemiesDefeatedInTown;
  }

  public void setEnemiesDefeatedInTown(int count) {
    this.enemiesDefeatedInTown = count;
  }

  public int getLevel() {
   return level;
  }

  public int getExp() {
    return experience;
  }

  public int getNextExpLevel() {
    return nextExpLevel;
  }

  public Item[] getInventory() {
    return inventory;
  }

  public String[] getAttackMoves() {
    return attackMoves;
  }

  public String[] getActiveDebuffs() {
    return activeDebuffs;
  }

	//setter
  public void setUsesMp(boolean usesMp) {
    this.usesMp = usesMp;
  }

  public void setLastActionSucceeded(boolean val) {
    this.lastActionSucceeded = val;
  }

  public boolean getLastActionSucceeded() {
    return this.lastActionSucceeded;
  }

  public void addExp(int amount) {
    if (amount <= 0) return;
    this.experience += amount;
    while (this.experience >= nextExpLevel) {
      this.experience -= nextExpLevel;
      levelUp();
    }
  }
 
  public void levelUp() {
    level++;
    String text = "You have leveled up to: " + level;
    typeWriter.typeWriterFast(textColor.YELLOW + text + textColor.RESET);
    nextExpLevel += 50;
    levelStats();
  }

  public void takeDamage(int amount) {
    int reducedDamage = Math.max(0, amount - getDefense());
    setHp(getHp() - reducedDamage);
    String text = getName() + " took " + String.valueOf(reducedDamage) + " damage.";
    centerHub.printRightTextWithTypeWriter(text);
    if (getHp() <= 0) {
      setHp(0);
    }
  }
  
  public void heal(int amount) {
    String text = getName() + " healed " + amount + " HP.";
    typeWriter.typeWriterFast(text);
    setHp(getHp() + amount);
    if (getHp() >= getMaxHp()) {
      setHp(getMaxHp());
    }
}

  public void addItem(Item item) {
      for (int i = 0; i < inventory.length; i++) {
        if (inventory[i] == null) {
          inventory[i] = item;
          break;
        }
      }
  }

  public void removeItem(int index) {
    if (index >= 0 && index < inventory.length) {
      // Shift all subsequent items left so there are no gaps.
      for (int i = index; i < inventory.length - 1; i++) {
        inventory[i] = inventory[i + 1];
      }
      // Clear last slot
      inventory[inventory.length - 1] = null;
    }
  }

  public void showInventory() {
    String text;
    System.out.println("\n========================================================================Inventory=====================================================================\n");
    inventorySorter();
    for (int i = 0; i < inventory.length; i++) {
      Item item = inventory[i];
      if (item != null) {
        text = "  " + (i + 1) + ". " + item.getName() + " - " + item.getDescription();
        centerHub.printCenteredText(text);
      }
    }
  }

  public static void inventorySorter() {
    int count = 0;
    for (int i = 0; i < inventory.length; i++) {
      if (inventory[i] != null) {
        inventory[count++] = inventory[i];
      }
    }
    for (int i = count; i < inventory.length; i++) {
      inventory[i] = null;
    }
  }
  
  public void setMoves(String[] moves) {
    for (int i = 0; i < moves.length & i < attackMoves.length; i++) {
      attackMoves[i] = moves[i];
    }
  }
 
 public void showMoves() {
  for (String moves : attackMoves) {
    if (moves != null) {
      System.out.println(moves);
    }
  }
 }

  // Return the display string for a move number (1-4), or null if not set
  public String getMoveName(int moveNumber) {
    if (moveNumber < 1 || moveNumber > attackMoves.length) return null;
    return attackMoves[moveNumber - 1];
  }

  //Temporary Effect Boost
  public void addTemporaryAttackBoost(int amount, int duration) {
    setAttackPower(getAttackPower() + amount);
    attackBoostAmount = amount;
    attackBoostTurn = duration;
    System.out.println(getName() + "'s Attack Power increased by " + amount + " for " + duration);
  }

  public void addTemporaryDefenseBoost(int amount, int duration) {
    setDefense(getDefense() + amount);
    defenseBoostAmount = amount;
    defenseBoostTurn = duration;
    System.out.println(getName() + "'s Defense increased by " + amount + " for " + duration + " turns.");
  }

  public void addDebuff(int amount, int duration) {
    setDefense(getDefense() - amount);
    defenseDebuffAmount = amount;
    defenseDebuffTurn = duration;
    String text = getName() + "'s Defense decreased by " + amount + " for " + duration + " turns.";
    centerHub.printRightTextWithTypeWriter(text);
  }

  public void updateTurnEffects() {

    //Attack Power
    if (attackBoostTurn > 0) {
      attackBoostTurn--;
      if (attackBoostTurn == 0) {
        setAttackPower(getAttackPower() - attackBoostAmount);
        String text = "Attack boost expired for " + getName() + "!";
        typeWriter.typeWriterFast(text);
        attackBoostAmount = 0;
      }
    }

    //Defense
    if (defenseBoostTurn > 0) {
      defenseBoostTurn--;
      if (defenseBoostTurn == 0) {
        setDefense(getDefense() - defenseBoostAmount);
        String text = "Defense boost expired for " + getName() + "!"; 
        typeWriter.typeWriterFast(text);
        defenseBoostAmount = 0;
      }
    }

    //Defense Debuff
    if (defenseDebuffTurn > 0) {
      defenseDebuffTurn--;
      if (defenseDebuffTurn == 0) {
        setDefense(getDefense() + defenseDebuffAmount);
        String text = "Defense debuff expired for " + getName() + "!"; 
        typeWriter.typeWriterFast(text);
        defenseDebuffAmount = 0;
      }
    }
  }

  //Debuff Methods
  public void applyDebuff(String type, int turns) {
    for (int i = 0; i < activeDebuffs.length; i++) {
        if (activeDebuffs[i] == null) {
            activeDebuffs[i] = type;
            debuffTurns[i] = turns;
            String text = getName() + " is afflicted with " + type + " for " + turns + " turns!";
            centerHub.printRightTextWithTypeWriter(text);
            return;
        }
        else if (activeDebuffs[i].equals(type)) {
            debuffTurns[i] = Math.max(debuffTurns[i], turns);
            String text = getName() + "'s " + type + " duration refreshed to " + debuffTurns[i] + " turns!";
            centerHub.printRightTextWithTypeWriter(text);
            return;
        }
    }
    centerHub.printRightTextWithTypeWriter("Too many debuffs active!");
  }

  public void updateDebuffs() {
    for (int i = 0; i < activeDebuffs.length; i++) {
        if (activeDebuffs[i] != null) {
            debuffTurns[i]--;
            applyDebuffEffect(activeDebuffs[i]);

            if (debuffTurns[i] <= 0) {
                String text = activeDebuffs[i] + " wore off!";
                centerHub.printRightTextWithTypeWriter(text);
                activeDebuffs[i] = null;
            }
        }
    }
  }

  public void applyDebuffEffect(String debuff) {
        switch (debuff.toLowerCase()) {
            case "poison":
                String text = getName() + " takes 2 poison damage!";
                centerHub.printRightTextWithTypeWriter(text);
                takeDamage(2);
                break;
            case "burn":
                text = getName() + " takes 2 burn damage!";
                centerHub.printRightTextWithTypeWriter(text);
                takeDamage(getDefense() + 2);
                break;
            case "absorb":
                text = getName() + " feels weaker! Health had been absored by 2";
                centerHub.printRightTextWithTypeWriter(text);
                takeDamage(2);
                break;
            case "defense down":
                text = getName() + " feels weaker! Defense temporarily reduced.";
                centerHub.printRightTextWithTypeWriter(text);
                setDefense(getDefense() - 2);
                break;
            case "attack down":
                text = getName() + " feels their strength fade!";
                centerHub.printRightTextWithTypeWriter(text);
                setAttackPower(getAttackPower() - 2);
                break;
            case "stun":
                text = getName() + " is stunned and cannot move!";
                centerHub.printRightTextWithTypeWriter(text);
                break;
            case "confusion":
                text = getName() + " is confused by the masks!";
                centerHub.printRightTextWithTypeWriter(text);
                break;             
        }
    }

  public void setBaseStats(int baseMaxHp, int baseStamina, int baseMaxStamina, int baseMp, int baseMaxMp, int baseDefense, int baseAttackPower, int baseSpeed) {
    this.baseMaxHp = baseMaxHp;
    this.baseStamina = baseStamina;
    this.baseMaxStamina = baseMaxStamina;
    this.baseMp = baseMp;
    this.baseMaxMp = baseMaxMp;
    this.baseDefense = baseDefense;
    this.baseAttackPower = baseAttackPower;
    this.baseSpeed = baseSpeed;
  }

  // Reset player progress (called when player dies)
  public void resetProgress() {
    // Reset experience and level
    this.experience = 0;
    this.level = 1;
    this.nextExpLevel = 100;

    // Reset path tracking
    this.chosenPath = null;
    this.currentTownIndex = 0;
    this.enemiesDefeatedInTown = 0;

    // Clear inventory
    for (int i = 0; i < inventory.length; i++) {
      inventory[i] = null;
    }

    // Reset temporary effect trackers
    attackBoostAmount = 0;
    attackBoostTurn = 0;
    defenseBoostAmount = 0;
    defenseBoostTurn = 0;
    defenseDebuffAmount = 0;
    defenseDebuffTurn = 0;

    // Remove debuffs from Character (inherited)
    removeDebuff();

    // Restore stats back to base (level 1) values captured at creation
    setMaxHp(baseMaxHp);
    setHp(baseMaxHp);

    setStamina(baseStamina);
    setMaxStamina(baseMaxStamina);

    setMp(baseMp);
    setMaxMp(baseMaxMp);

    setDefense(baseDefense);
    setAttackPower(baseAttackPower);
    setSpeed(baseSpeed);

    System.out.println(getName() + "'s progress has been reset to level 1 base stats due to death.");
  }
}