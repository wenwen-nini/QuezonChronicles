package Main.character.player.classes;

import Main.character.player.Player;
import Main.character.Character;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Mage extends Player {
   
   private CenterHub centerHub = new CenterHub();

   public int skillUsedTurnForSkill2 = 0;
   public int skillUsedTurnForSkill3 = 0;

   public Mage(String name) {
      setName(name);
      // Balanced base stats for Mage (glass cannon / caster)
      setMaxHp(100);
      setHp(100);
      setMp(110);
      setMaxMp(110);
      setDefense(8);
      setAttackPower(20);
      setSpeed(7);
      description =
            "Masters of the mystical arts and occasional bakery enthusiasts, Mages command the elements with pure intellect — and sometimes pure chaos.\n" +
            "These spellcasters channel the raw forces of fire, mana, and… baked goods? Yes, through years of study (and late-night merienda),\n" +
            "Mages have discovered the secret arcane energy within pastries and pastries alone.";

      // Capture base stats for proper reset behavior
      setBaseStats(100, 0, 0, 110, 110, 8, 20, 7);
      setUsesMp(true);
   
  		setMoves(new String[] {"1. Fire Ball (Basic + no mana required)", 
   			"2. LambaShield (Creates a barrier that reduces incoming damage by 30% for 2 turns. 10 MP)", 
            "3. Mana Surge (Regenerates 25 MP instantly.)",
            "4. Pinagong Storm (Calls down hard pinagong breads on all enemies, deals heavy damage. 15 MP)"});
   }

	@Override
   public void useMoves(int moveNumber, Character target) {
      switch(moveNumber) {
         case 1:
            String text = "\n" + getName() + " cast a Fire Ball!";
            typeWriter.typeWriterFast(text);
            target.takeDamage(getAttackPower());
            skillUsedTurnForSkill2();
            skillUsedTurnForSkill3();
            setLastActionSucceeded(true);
            break;

         case 2:
            if (getMp() >= 10) {
               if (skillUsedTurnForSkill2 > 0){
                  text = "You just used LambaShield. Cannot use for " + skillUsedTurnForSkill2 + " more turn(s).";
                  typeWriter.typeWriterFast(text);
                  setLastActionSucceeded(false);
                  break;
               }
               text = "\n" + getName() + " cast a LambaShield!";
               typeWriter.typeWriterFast(text);
               setMp(getMp() - 10);
               addTemporaryDefenseBoost((int)(getDefense() * 1.2), 2);
               skillUsedTurnForSkill2 = 2;
               skillUsedTurnForSkill3();
               setLastActionSucceeded(true);
            }
            else {
               setLastActionSucceeded(false);
            }
            break;

         case 3:
            if(skillUsedTurnForSkill3 > 0){
               text = "You just used Mana Surge. Cannot use for " + skillUsedTurnForSkill3 + " more turn(s).";
               typeWriter.typeWriterFast(text);
               setLastActionSucceeded(false);
               break;
            }
            else{
               text = "\n" + getName() + " cast a Mana Surge!";
               typeWriter.typeWriterFast(text);
               addMp(25);
               skillUsedTurnForSkill3 = 2;
               skillUsedTurnForSkill2();
               setLastActionSucceeded(true); 
               break;
            }

         case 4:
            if (getMp() >= 15) {
               setMp(getMp() - 15);
               text = "\n" + getName() + " cast a Pinagong Storm!";
               typeWriter.typeWriterFast(text);
               int damage = getAttackPower() + (int)(getAttackPower() * 1.1);
				   target.takeDamage(damage);
               skillUsedTurnForSkill2();
               skillUsedTurnForSkill3();
               setLastActionSucceeded(true);
            }
            else {
               setLastActionSucceeded(false);
            }
            break;
         
         default:
            typeWriter.typeWriterFast("Invalid move number!");
            setLastActionSucceeded(false);
            break;
      }
   }

	@Override
   public void showStats() {
		String text;
      System.out.println("===================================================================== " + textColor.BLUE + "Mage Stats" + textColor.RESET + " ===================================================================");
		text = "Health: " + String.valueOf(getHp()) + "/" + String.valueOf(getMaxHp());
    	centerHub.printCenteredText(textColor.GREEN + text + textColor.RESET);
		text = "Mana: " + String.valueOf(getMp()) + "/" + String.valueOf(getMaxMp());
    	centerHub.printCenteredText(textColor.BLUE + text + textColor.RESET);
		text = "Defense: " + String.valueOf(getDefense());
    	centerHub.printCenteredText(textColor.ORANGE + text + textColor.RESET);
		text = "Attack Power: " + String.valueOf(getAttackPower());
    	centerHub.printCenteredText(textColor.RED + text + textColor.RESET);
		text = "Speed: " + String.valueOf(getSpeed());
    	centerHub.printCenteredText(textColor.YELLOW + text + textColor.RESET);
		text = "Experience: " + String.valueOf(getExp()) + "/" + String.valueOf(getNextExpLevel());
    	centerHub.printCenteredText(textColor.PURPLE + text + textColor.RESET);
		text = "Level: " + String.valueOf(getLevel());
    	centerHub.printCenteredText(textColor.YELLOW + text + textColor.RESET);
    	System.out.println("=====================================================================================================================================================");
	}

	@Override
   public void levelStats() {
      // Mage grows in MP faster than HP and becomes more potent with spells
      setMaxHp(getMaxHp() + 12);
      setHp(getHp() + 12);
      setMp(getMp() + 12);
      setMaxMp(getMaxMp() + 12); 
      setDefense(getDefense() + 3);
      setAttackPower(getAttackPower() + 4);
      setSpeed(getSpeed() + 2);
	}

   public void skillUsedTurnForSkill2() {
      if (skillUsedTurnForSkill2 <= 0) {
         skillUsedTurnForSkill2 = 0;
      }
      else {
         skillUsedTurnForSkill2--;
         if (skillUsedTurnForSkill2 == 0) {
            typeWriter.typeWriterFast("LambaShield is ready!");
         }
      }
   }

   public void skillUsedTurnForSkill3() {
	  if (skillUsedTurnForSkill3 <= 0) {
		 skillUsedTurnForSkill3 = 0;
	  }
	  else {
		 skillUsedTurnForSkill3--;
		 if (skillUsedTurnForSkill3 == 0) {
			typeWriter.typeWriterFast("Mana Surge is ready!");
		 }
	  }
   }
}
