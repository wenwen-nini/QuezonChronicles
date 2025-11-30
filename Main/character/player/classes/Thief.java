package Main.character.player.classes;

import Main.character.player.Player;
import Main.character.Character;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Thief extends Player{

    public int skillUsedTurnForSkill3 = 0;
    public int skillUsedTurnForSkill4 = 0;
	private CenterHub centerHub = new CenterHub();

    public Thief(String name){
        setName(name);
        // Balanced base stats for Thief (high speed & crit, glassy)
        setMaxHp(100);
        setHp(100);
        setStamina(70);
        setMaxStamina(70);
        setDefense(6);
        setAttackPower(18);
        setSpeed(10);
        description =
                "Swift, sly, and sharp as a blade hidden beneath a cloak, the Thief dances between the lines of law and survival.\n" +
                "Born from the bustling streets, back alleys, and midnight markets, Thieves are experts in deception, timing, and taking what the world refuses to give.\n\n" +
                "Where others see danger, the Thief sees opportunity. Every fight is a gamble, every strike a trick of the hand.\n" +
                "Armed with quick reflexes and a sharper wit, they slip through shadows, strike where it hurts, and vanish before justice can catch up.\n" +
                "In a world ruled by power, the Thief survives through diskarte.";

        // Capture base stats for proper reset behavior
        setBaseStats(100, 70, 70, 0, 0, 6, 18, 10);
        setUsesMp(false);

        setMoves(new String[] {"1. Stab (Basic + no stamina required)",
                                "2. Critical Edge (One strong hit that always lands critical damage (Cost: 10 Stamina))",
                                "3. Vanish (Throws Smoke Bomb that vanishes the Thief, giving 80% to dodge all attacks for 2 turns. (Cost: 15 Stamina))",
                                "4. Looter's Instinct (regains small HP/stamina on successful steals)"});
        }   

	@Override
    public void useMoves(int moveNumber, Character target) {
        switch(moveNumber){
            case 1:
                String text = "\n" + getName() + " used Stab!";
                typeWriter.typeWriterFast(text);
                target.takeDamage(getAttackPower());
                skillUsedTurnForSkill3();
                skillUsedTurnForSkill4();
                setLastActionSucceeded(true);
                break;

            case 2:
                if (getStamina() >= 10){
                    text = "\n" + getName() + " used Critical Edge!";
                    typeWriter.typeWriterFast(text);
                    setStamina(getStamina() - 10);
                    target.takeDamage((int)(getAttackPower() * 1.6));
                    skillUsedTurnForSkill3();
                    skillUsedTurnForSkill4();
                    setLastActionSucceeded(true);
                }
                else {
                    setLastActionSucceeded(false);
                }
                break;

            case 3:
                if (getStamina() >= 15){
                    if (skillUsedTurnForSkill3 > 0){
                        text = "You just used Vanish. Cannot use for " + skillUsedTurnForSkill3 + " more turn(s).";
                        typeWriter.typeWriterFast(text);
                        setLastActionSucceeded(false);
                        break;
                    }
                    setStamina(getStamina() - 15);
                    text = "\n" + getName() + " used Vanish!";
                    typeWriter.typeWriterFast(text);
                    text = getName() + " threw a Smoke Bomb and vanished from sight, giving 80% to dodge all attacks for 2 turns!";
                    typeWriter.typeWriterFast(text);
                    addDodgeTurns(2);
                    skillUsedTurnForSkill3 = 2;
                    skillUsedTurnForSkill4();
                    setLastActionSucceeded(true);
                }
                else {
                    setLastActionSucceeded(false);
                }
                break;

            case 4:
                if(skillUsedTurnForSkill4 > 0){
                    text = "You just used Looter's Instinct. Cannot use for " + skillUsedTurnForSkill4 + " more turn(s).";
                    typeWriter.typeWriterFast(text);
                    setLastActionSucceeded(false);
                    break;
                }
                else{
                    text = "\n" + getName() + " used Looter's Instinct!";
                    typeWriter.typeWriterFast(text);
                    heal(10 + (getLevel() * 2));
                    addStamina(15 + (getLevel() * 2));
                    skillUsedTurnForSkill4 = 2;
                    skillUsedTurnForSkill3();
                    setLastActionSucceeded(true);
                    break;
                }

            default:
                typeWriter.typeWriterFast("Invalid move number!");
                setLastActionSucceeded(false);
                break;
        }
    }

	@Override
    public void showStats() {
        String text;
		System.out.println("===================================================================== " + textColor.PURPLE + "Thief Stats" + textColor.RESET + " ==================================================================");
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
        // Thief gains small max HP and stamina but grows in speed and critical damage
        setMaxHp(getMaxHp() + 10);
        setHp(getHp() + 10);
        setStamina(getStamina() + 8);
        setMaxStamina(getMaxStamina() + 8);
        setDefense(getDefense() + 2);
        setAttackPower(getAttackPower() + 2);
        setSpeed(getSpeed() + 1);
	}

    public void skillUsedTurnForSkill3() {
      if (skillUsedTurnForSkill3 <= 0) {
         skillUsedTurnForSkill3 = 0;
      }
      else {
         skillUsedTurnForSkill3--;
         if (skillUsedTurnForSkill3 == 0) {
            typeWriter.typeWriterFast("Vanish is ready!");
         }
      }
   }

   public void skillUsedTurnForSkill4() {
	  if (skillUsedTurnForSkill4 <= 0) {
		 skillUsedTurnForSkill4 = 0;
	  }
	  else {
		 skillUsedTurnForSkill4--;
		 if (skillUsedTurnForSkill4 == 0) {
			typeWriter.typeWriterFast("Looter's Instinct is ready!");
		 }
	  }
   }
}

