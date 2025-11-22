package Main.character.player.classes;

import Main.character.player.Player;
import Main.character.Character;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Mage extends Player {
   
   private static CenterHub centerHub = new CenterHub();

   public static int skillUsedTurn;

   public Mage(String name) {
      setName(name);
      // Balanced base stats for Mage (glass cannon / caster)
      setMaxHp(70);
      setHp(70);
      setMp(80);
      setMaxMp(80);
      setDefense(6);
      setAttackPower(14);
      setSpeed(18);
      description =
            "Masters of the mystical arts and occasional bakery enthusiasts, Mages command the elements with pure intellect — and sometimes pure chaos.\n" +
            "These spellcasters channel the raw forces of fire, mana, and… baked goods? Yes, through years of study (and late-night merienda),\n" +
            "Mages have discovered the secret arcane energy within pastries and pastries alone.";

      // Capture base stats for proper reset behavior
      setBaseStats(70, 0, 0, 80, 80, 6, 14, 18);
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
            skillUsedTurn();
            setLastActionSucceeded(true);
            break;

         case 2:
            if (getMp() >= 10) {
               text = "\n" + getName() + " cast a LambaShield!";
               typeWriter.typeWriterFast(text);
               setMp(getMp() - 10);
               addTemporaryDefenseBoost((int)(getDefense() * 0.30), 2);
               skillUsedTurn();
               setLastActionSucceeded(true);
            }
            else {
               setLastActionSucceeded(false);
            }
            break;

         case 3:
            if(skillUsedTurn > 0){
               text = "You just used Mana Surge. Cannot use for " + skillUsedTurn + " more turn(s).";
               typeWriter.typeWriterFast(text);
               setLastActionSucceeded(false);
               break;
            }
            else{
               text = "\n" + getName() + " cast a Mana Surge!";
               typeWriter.typeWriterFast(text);
               addMp(25);
               System.out.println("Mana Restored by 25 points!");
               skillUsedTurn = 2;
               setLastActionSucceeded(true); 
               break;
            }

         case 4:
            if (getMp() >= 15) {
               setMp(getMp() - 15);
               text = "\n" + getName() + " cast a Pinagong Storm!";
               typeWriter.typeWriterFast(text);
               int damage = getAttackPower() + (int)(getAttackPower() * 0.5);
				   target.takeDamage(damage);
               skillUsedTurn();
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
      setMaxHp(getMaxHp() + 6);
      setHp(getHp() + 6);
      setMp(getMp() + 10);
       setMaxMp(getMaxMp() + 10); 
      setDefense(getDefense() + 1);
      setAttackPower(getAttackPower() + 3);
      setSpeed(getSpeed() + 0);
	}

   public static void skillUsedTurn() {
      if (skillUsedTurn <= 0) {
         skillUsedTurn = 0;
      }
      else {
         skillUsedTurn--;
         if (skillUsedTurn == 0) {
            System.out.println("Mana Surge is ready!");
         }
      }
   }
}
