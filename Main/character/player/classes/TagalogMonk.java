package Main.character.player.classes;

import Main.character.player.Player;
import Main.character.Character;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class TagalogMonk extends Player {

   private CenterHub centerHub = new CenterHub();

   public int skillUsedTurn;
   
   public TagalogMonk(String name) {
      setName(name);
      // Balanced base stats for Tagalog Monk (balanced melee hybrid)
      setMaxHp(120);
      setHp(120);
      setStamina(75);
      setMaxStamina(75);
      setDefense(10);
      setAttackPower(13);
      setSpeed(10);
      description =
               "Steeped in the ancient traditions of the Tagalog highlands and lowlands, the Tagalog Monk embodies harmony between body, spirit, and the land.\n" +
               "Guided by pananampalataya (faith) and disiplina (discipline), these warriors channel the strength of their ancestors\n" +
               "from the mountains of the North to the plains of the heart of Luzon.";

      // Capture base stats for proper reset behavior
      setBaseStats(120, 75, 75, 0, 0, 10, 13, 10);
      setUsesMp(false);
   
  		setMoves(new String[] {"1. Suntok ni Apo (basic + no stamina required)", 
   			"2. Bugso ng Loob (The monk releases a surge of inner energy, increasing attack power by 60% and dealing strong damage to a single enemy. (Cost: 10 Stamina))", 
            "3. Karma Strike (A powerful counterattack imbued with spiritual justice. Deals heavy damage and has a small chance to stun the target. (Cost: 15 Stamina))",
            "4. Dasal ng Katahimikan (The monk prays calmly amid battle, restoring a portion of HP and stamina while reducing incoming damage by 25% for 1 turn)"});
   }

	@Override
   public void useMoves(int moveNumber, Character target) {
      switch(moveNumber) {
         case 1:
            String text = "\n" + getName() + " used Suntok ni Apo!";
            typeWriter.typeWriterFast(text);
            target.takeDamage(getAttackPower());
            setLastActionSucceeded(true);
            skillUsedTurn();
            break;

         case 2:
               if (getStamina() >= 10) {
                  text = "\n" + getName() + " used Bugso ng Loob!";
                  typeWriter.typeWriterFast(text);
                  setStamina(getStamina() - 10);
                  int boostDamage = getAttackPower() + (int) Math.floor((getAttackPower() * 0.6));
                  target.takeDamage(boostDamage);
                  setLastActionSucceeded(true);
               }
               else {
                  setLastActionSucceeded(false);
               }
            skillUsedTurn();
            break;

         case 3:
            if (getStamina() >= 15) {
               text = "\n" + getName() + " used a Karma Strike!";
               typeWriter.typeWriterFast(text);
               setStamina(getStamina() - 15);
			      int boostDamage = getAttackPower() + (int)(getAttackPower() * 0.2);
               target.takeDamage(boostDamage);
               double stunChance = Math.random();
                  if (stunChance < 0.25) {
                     System.out.println(target.getName() + " is stunned!");
                     target.applyDebuff("stun", 3);
                  }
               setLastActionSucceeded(true);
            }
            else {
               setLastActionSucceeded(false);
            }
            skillUsedTurn();
            break;
            
         case 4:
            if (skillUsedTurn > 0) {
               typeWriter.typeWriterFast("You just used Dasal ng Katahimikan. Cannot use for 2 turns!");
               setLastActionSucceeded(false);
               break;
            }
            else {
               text = "\n" + getName() + " used Dasal ng Katahimikan!";
               typeWriter.typeWriterFast(text);
               text = getName() + "'s has healed and gained stamina!";
               typeWriter.typeWriterFast(text);
               heal((int)(getHp() * 0.3));
               addStamina(25);
               addTemporaryDefenseBoost((int)(getDefense() * 0.25), 2);
               skillUsedTurn = 2;
               skillUsedTurn();
               setLastActionSucceeded(true);
               break;
            }

         default:
               typeWriter.typeWriterFast("Invalid move number!");
               break;
      }
   }

	@Override
   public void showStats() {
      String text;
		System.out.println("================================================================== " + textColor.ORANGE + "Tagalog Monk Stats" + textColor.RESET + " ===============================================================");
		text = "Health: " + String.valueOf(getHp()) + "/" + String.valueOf(getMaxHp());
    	centerHub.printCenteredText(textColor.GREEN + text + textColor.RESET);
		text = "Stamina: " + String.valueOf(getStamina()) + "/" + String.valueOf(getMaxStamina());
    	centerHub.printCenteredText(textColor.YELLOW + text + textColor.RESET);
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
      // Monk grows in HP and stamina and gains attack steadily
      setMaxHp(getMaxHp() + 12);
      setHp(getHp() + 12);
      setStamina(getStamina() + 7);
      setMaxStamina(getMaxStamina() + 7);
      setDefense(getDefense() + 2);
      setAttackPower(getAttackPower() + 2);
      setSpeed(getSpeed() + 2);
	}

   public void skillUsedTurn() {
      if (skillUsedTurn <= 0) {
         skillUsedTurn = 0;
      }
      else {
         skillUsedTurn--;
         if (skillUsedTurn == 0) {
            typeWriter.typeWriterFast("Dasal ng Katahimikan is ready!");
         }
      }
   }
}

