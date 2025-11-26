package Main.character.player.classes;

import Main.character.player.Player;
import Main.character.Character;
import Main.styles.printAlignmentHub.CenterHub;
import Main.styles.textColor.TextColorHub;

public class Warrior extends Player{

    private CenterHub centerHub = new CenterHub();

    public int skillUsedTurn;

    public Warrior(String name){
        setName(name);
        
        setMaxHp(135);
        setHp(135);
        setStamina(70);
        setMaxStamina(70);
        setDefense(10);
        setAttackPower(17);
        setSpeed(7);
        description =
                "Forged in battle and tempered by hardship, the Warrior stands as the steadfast heart of every fight. Born from the rugged hills and wide plains,\n" +
                "Warriors embody the courage of those who fight not for glory, but for karangalan (honor) and kapwa (others).";

        // Capture base stats for proper reset behavior
        setBaseStats(135, 70, 70, 0, 0, 10, 17, 7);
        setUsesMp(false);

        setMoves(new String[] {"1. Slash(Basic + no stamina required.)",
                                "2. Cleave(Swings the weapon with all your might which deals 150% of normal damage.",
                                "3. Shield Bash(Deals minor damage and has a chance to stun.)",
                                "4. Second Wind(Gain minor heal and stamina.)"});
    }

	@Override
    public void useMoves(int moveNumber, Character target) {
        switch(moveNumber){
            case 1:
                String text = "\n" + getName() + " used Slash!";
                typeWriter.typeWriterFast(text);
                target.takeDamage(getAttackPower());
                setLastActionSucceeded(true);
                skillUsedTurn();
                break;

            case 2:
                if (getStamina() >= 8){
                    text = "\n" + getName() + " used Cleave!";
                    typeWriter.typeWriterFast(text);
                    setStamina(getStamina() - 5);
                    target.takeDamage((int) Math.floor((getAttackPower() * 1.5)));
                    skillUsedTurn();
                    setLastActionSucceeded(true);
                    break;
                }
                else{
                    setLastActionSucceeded(false);
                }
                skillUsedTurn();
                break;

            case 3:
                if (getStamina() >= 5){
                    text = "\n" + getName() + " used Shield Bash!";
                    typeWriter.typeWriterFast(text);
                    target.takeDamage((int) (getAttackPower() * 0.5));
                    double stunChance = 0.25;
                    if(Math.random() <= stunChance){
                        target.applyDebuff("stun", 4);
                    }
                    setStamina(getStamina() - 3);
                    setLastActionSucceeded(true);
                    skillUsedTurn();
                    break;
                }
                else{
                    setLastActionSucceeded(false);
                }
                skillUsedTurn();
                break;

            case 4:
                if (skillUsedTurn > 0) {
                    text = "You just used Second Wind. Cannot use for " + skillUsedTurn + " more turn(s).";
                    typeWriter.typeWriterFast(text);
                    setLastActionSucceeded(false);
                    break;
                }
                else{
                    text = "\n" + getName() + " used Second Wind!";
                    typeWriter.typeWriterFast(text);
                    heal(15);
                    addStamina(10);
                    skillUsedTurn = 2;
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
		System.out.println("==================================================================== " + textColor.RED + "Warrior Stats" + textColor.RESET + " =================================================================");
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
        // Warrior grows in HP and stamina quickly, modest increases in damage and defense
        setMaxHp(getMaxHp() + 14);
        setHp(getHp() + 14);
        setStamina(getStamina() + 5);
        setMaxStamina(getMaxStamina() + 5);
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
            typeWriter.typeWriterFast("Second Wind is ready!");
         }
      }
   }
}