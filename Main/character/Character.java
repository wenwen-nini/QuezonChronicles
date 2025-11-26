package Main.character;

import Main.styles.animationHub.TypeWriter;

public abstract class Character {
  private String name;
  private int maxHp, hp, stamina, mp, attackPower, defense, speed, maxStamina, maxMp;
  private int dodgeTurns = 0;

  //Debuff Attributes
  public String[] activeDebuffs = new String[3];
  public int[] debuffTurns = new int[3];

  private TypeWriter typeWriter = new TypeWriter();

  //Stun Attribute
  private boolean isStunned;

  public abstract void takeDamage(int amount);
  public abstract void applyDebuff(String type, int turns);
  public abstract void updateDebuffs();
  public abstract void applyDebuffEffect(String debuff);
  public abstract void heal(int amount);

  public void addStamina(int amount) {
    typeWriter.typeWriterFast(name + " regained " + amount + " Stamina.");
    stamina += amount;
    if (stamina >= maxStamina) {
      stamina = maxStamina;
    }
  }

  public void addMp(int amount) {
    typeWriter.typeWriterFast(name + " regained " + amount + " MP.");
    mp += amount;
    if (mp >= maxMp) {
      mp = maxMp;
    }
  }

  public void addSpeed(int amount) {
    speed += amount;
  }

  public boolean isAlive() {
    return hp > 0;
  }

  //getters for Stats
  public String getName() {
    return name;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public int getHp() {
    return hp;
  }

  public int getStamina() {
    return stamina;
  }

  public int getMaxStamina() {
    return maxStamina;
  }

  public int getMp() {
    return mp;
  }

  public int getMaxMp() {
    return maxMp;
  }

  public int getAttackPower() {
    return attackPower;
  }

  public int getDefense() {
    return defense;
  }

  public int getSpeed() {
    return speed;
  }

  public boolean getIsStunned() {
    return isStunned;
  }

  // When a character's turn arrives, consume one turn of stun if present.
  // Returns true if the character was stunned and their turn is consumed (i.e., they cannot act).
  public boolean consumeStunTurn() {
    for (int i = 0; i < activeDebuffs.length; i++) {
      String debuff = activeDebuffs[i];
      if (debuff != null && debuff.equalsIgnoreCase("stun")) {
        // apply effect message
        applyDebuffEffect("stun");
        debuffTurns[i]--;
        if (debuffTurns[i] <= 0) {
          activeDebuffs[i] = null;
          typeWriter.typeWriterFast("Stun wore off!");
        }
        return true;
      }
    }
    return false;
  }

	//setters for Stats
  public void setName(String newName) {
    name = newName;
  }
  
  public void setMaxHp(int newMaxHp) {
    maxHp = newMaxHp;
  }

  public void setHp(int newHp) {
    hp = newHp;
  }

  public void setStamina(int newStamina) {
    stamina = newStamina;
  }

  public void setMaxStamina(int newMaxStamina) {
    maxStamina = newMaxStamina;
  }

  public void setMp(int newMp) {
    mp = newMp;
  }

  public void setMaxMp(int newMaxMp) {
    maxMp = newMaxMp;
  }

  public void setAttackPower(int newAttackPower) {
    attackPower = newAttackPower;
  }

  public void setDefense(int newDefense) {
    defense = newDefense;
  }

  public void setSpeed(int newSpeed) {
    speed = newSpeed;
  }
  
  public void removeDebuff() {
    for (int i = 0; i < activeDebuffs.length; i++) {
      activeDebuffs[i] = null;
    }
    for (int i = 0; i < debuffTurns.length; i++) {
      debuffTurns[i] = 0;
    }
  }

  public void checkStunned() {
    isStunned = false;
    for (String debuff : activeDebuffs) {
      if (debuff != null && debuff.equalsIgnoreCase("stun")) {
        isStunned = true;
        break;
      }
    }
  }

  public int getDodgeTurns() {
    return dodgeTurns;
  }

  public void setDodgeTurns(int turns) {
    dodgeTurns = turns;
  }

  public void addDodgeTurns(int turns) {
    dodgeTurns += turns;
  }

  public void reduceDodgeTurns() {
    if (dodgeTurns > 0) {
      dodgeTurns--;
    }
  }
}
