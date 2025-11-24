package Main.character.player.classes;

import Main.character.player.Player;
import Main.character.Character;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.animationHub.TypeWriter;

public class Bruid extends Player {

	private static CenterHub centerHub = new CenterHub();

	public static int skillUsedTurnForSkill2 = 0;
	public static int skillUsedTurnForSkill3 = 0;

	public Bruid(String name) {
		setName(name);
		// Balanced base stats for Bruid (support / hybrid)
		setMaxHp(95);
		setHp(95);
		setMp(70);
		setMaxMp(70);
		setDefense(8);
		setAttackPower(13);
		setSpeed(6);
		description = 
				"Half forest guardian, half Bisakol wonder, the Bruid is a nature-touched warrior who draws strength from the sacred punò ng saging (banana tree).\n" +
				"Unlike typical druids who revere oaks or vines, the Bruid channels the resilience, flexibility, and masabaw na energy of the banana.\n" +
				"They are both protectors and pranksters of the wild, often seen communing with their leafy kin while humming folk tunes from the provinces.";

		// Capture base stats for proper reset behavior
		setBaseStats(95, 0, 0, 70, 70, 8, 13, 6);
		setUsesMp(true);

		
   
  		setMoves(new String[] {"1. Banana Strike (Basic + no mana required)", 
   							"2. Front Shield (Banana tree fronds wrap around the character, reducing incoming damage by 25% for 2 turns. 10 MP)", 
   							"3. Mana Grove (Restores moderate MP, using the banana tree's life force.)", 
   							"4. Puso ng Saging (The druid summons a giant banana heart that explodes, dealing heavy damage and a 20% chance to stun. 15 MP)"});
	}
	
	@Override
	public void useMoves(int moveNumber, Character target) {
    switch (moveNumber) {
      	case 1:
			String text = "\n" + getName() + " used Banana Strike!";
			typeWriter.typeWriterFast(text);
			target.takeDamage(getAttackPower());
			setLastActionSucceeded(true);
            skillUsedTurnForSkill2();
			skillUsedTurnForSkill3();
			break;

		case 2:
			if (getMp() >= 10) {
				if (skillUsedTurnForSkill2 > 0){
					text = "You just used Front Shield. Cannot use for " + skillUsedTurnForSkill2 + " more turn(s).";
					typeWriter.typeWriterFast(text);
					setLastActionSucceeded(false);
					break;
				}
				text = "\n" + getName() + " used Front Shield!";
				typeWriter.typeWriterFast(text);
				setMp(getMp() - 10);
				addTemporaryDefenseBoost((int)(getDefense() * 0.25), 2);
				setLastActionSucceeded(true);
				skillUsedTurnForSkill2 = 2;
				skillUsedTurnForSkill3();
			}
			else {
				setLastActionSucceeded(false);
			}
			break;

		case 3:
			if(skillUsedTurnForSkill3 > 0){
               text = "You just used Mana Grove. Cannot use for " + skillUsedTurnForSkill3 + " more turn(s).";
               typeWriter.typeWriterFast(text);
               setLastActionSucceeded(false);
               break;
            }
            else{
               text = "\n" + getName() + " cast a Mana Grove!";
               typeWriter.typeWriterFast(text);
               addMp(25);
               System.out.println("Mana Restored by 25 points!");
               skillUsedTurnForSkill3 = 2;
			   skillUsedTurnForSkill2();
               setLastActionSucceeded(true); 
               break;
            }

		case 4:
			if (getMp() >= 15) {
				setMp(getMp() - 15);
				text = "\n" + getName() + " used Puso ng Saging!";
				typeWriter.typeWriterFast(text);
				int damage = getAttackPower() + (int)(getAttackPower() * 0.5);
				target.takeDamage(damage);
				if (Math.random() < 0.2) {
					target.applyDebuff("stun", 2);
					typeWriter.typeWriterFast(text);
				}
				setLastActionSucceeded(true);
				skillUsedTurnForSkill2();
				skillUsedTurnForSkill3();
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
		System.out.println("===================================================================== " + textColor.GREEN + "Bruid Stats" + textColor.RESET + " ==================================================================");
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
		// Bruid grows moderately in HP & MP and increases defense and attack steadily
		setMaxHp(getMaxHp() + 9);
		setHp(getHp() + 9);
		setMp(getMp() + 8);
		setMaxMp(getMaxMp() + 8);
		setDefense(getDefense() + 2);
		setAttackPower(getAttackPower() + 2);
		setSpeed(getSpeed() + 0);
	}

	public static void skillUsedTurnForSkill2() {
      if (skillUsedTurnForSkill2 <= 0) {
         skillUsedTurnForSkill2 = 0;
      }
      else {
         skillUsedTurnForSkill2--;
         if (skillUsedTurnForSkill2 == 0) {
            System.out.println("Front Shield is ready!");
         }
      }
   }

   public static void skillUsedTurnForSkill3() {
	  if (skillUsedTurnForSkill3 <= 0) {
		 skillUsedTurnForSkill3 = 0;
	  }
	  else {
		 skillUsedTurnForSkill3--;
		 if (skillUsedTurnForSkill3 == 0) {
			System.out.println("Mana Grove is ready!");
		 }
	  }
   }
}
