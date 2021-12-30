package Characters;

import java.util.Random;
import java.util.Scanner;

import Game.Combat;

public class MainCharacter extends Character{
	protected int stealth;
	protected int stealthBar;
	protected int xp;
	protected int gold;
	protected boolean isDetected; // this only applies during a fight
	
	public MainCharacter(){ // preferably ito dapat
		super();
		isDetected = false;
		xp = 0;
		stealth = 0;
		stealthBar = stealth;
	}
	
	public MainCharacter(int stealth) {
		super();
		isDetected = false;
		xp = 0;
		this.stealth = stealth;
		stealthBar = stealth;
	}
	
	public MainCharacter(String name, int level, int health, 
			int mana, int damage, int block, int luck, int stealth){
		super(name,level,health,mana,damage,block,luck);
		isDetected = false;
		this.stealth = stealth; // this shouldn't be called pero if meron, then sure
		stealthBar = stealth;
		xp = 0;
	}
	
	// getter
	public int getStealth() {
		return stealth;
	}
	
	public int getStealthBar() {
		return stealthBar;
	}
	
	public int getXP() {
		return xp;
	}
	
	
	// setter
	public void setStealth(int stealth) {
		this.stealth = stealth;
	}
	
	public void setStealthBar(int stealthBar) {
		if (stealthBar <= 0)
			stealthBar = 0;
		
		this.stealthBar = stealthBar;
	}
	
	public void setXP(int xp) {
		this.xp = xp;
	}
	
	// functions
	public void detected() {
		isDetected = true;
	}
	
	public boolean isSeen() {
		return stealthBar <= 0;
	}
	
	public boolean shouldLevelUp() {
		return xp >= 20;
	}
	
	public void finishedCombat(){ // this should be called everytime a combat is finished
		statusReset();
		isDetected = false;
		stealthBar = stealth;
		if (shouldLevelUp())
			levelUp();
	}
	
	public void levelUp() {
		setLevel(getLevel() + 1);
		xp = 0;
		experienceChoices();
	}
	
	public void experienceChoices() { // this will be called when you leveledUp
		int choiceA = -1;
		int choiceB = -1;
		int choiceC = -1;
		int decision = -1;
		System.out.println("With enough experience, " + name);
		String[] experiences = new String[]
				{
						" decided that it was best to improve his body.", // health
						" asked an experienced swordsman to improve his skill", // damage
						" meditate and learn the power of the cosmos", // mana
						" wanted to leave his fate to lady luck", // luck
						" studied the shadows", // stealth
						" endured being attacked by weak enemies", // block
						};
		
		Random dice = new Random();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		while (true) {
			if (choiceA == choiceB || choiceA == choiceC)
				choiceA = dice.nextInt(experiences.length);
			if (choiceB == choiceA || choiceB == choiceC)
				choiceB = dice.nextInt(experiences.length);
			choiceC = dice.nextInt(experiences.length);
			if (choiceC == choiceB || choiceC == choiceA)
				choiceC = dice.nextInt(experiences.length);
			if (choiceA != choiceB && choiceB != choiceC && choiceC != choiceA)
				break;
		}
		
		System.out.println("(A)" + experiences[choiceA]);
		System.out.println("(B)" + experiences[choiceB]);
		System.out.println("(C)" + experiences[choiceC]);
		
		while (true) {
			char letter = input.next().toLowerCase().charAt(0);
			if (letter != 'a' && letter != 'b' && letter != 'c')
				System.out.println("The choice should be A, B, or C!");
			else {
				switch(letter) {
					case 'a': decision = choiceA; break;
					case 'b': decision = choiceB; break;
					default: decision = choiceC;
				}
				break;
			}
		}
		
		switch(decision) {
		case 0: // health
			setHealth(getHealth() + 5); break;
		case 1: // damage
			setDamage(getDamage() + 1); break;
		case 2: // mana
			setMana(getMana() + 5); break;
		case 3: // luck
			setLuck(getLuck() + 10); break;
		case 4: // stealth
			setStealth(getStealth() + 15); break;
		case 5: // block
			setBlock(getBlock() + 1); break;
		}
	}
	
	@Override
	public String getInfo() {
		String info = 
				"= = = I N F O = = =\r\n"
				+ "Level: " + level + "(" + xp + ")\r\n"
				+ "Name: " + name + "\r\n"
				+ "Health: " + health + "/" + currHealth + "\r\n"
				+ "Mana:" + mana + "/" + currMana + "\r\n"
				+ "Damage: " + damage + "(" + damageMod + ")\r\n"
				+ "Block: " + block + "(" + blockMod + ")\r\n"
				+ "Luck: " + luck + "(" + luckMod + ")\r\n"
				+ "Stealth :" + stealth + "/" + stealthBar + "\r\n"
				+ "Status: burn(" + getBurn() + ")\r\n";
		
		return info;
	}
	
	public void rest() {
		System.out.println(getName() + " has rested and feels well!");
		currHealth = health;
		currMana = mana;
		stealthBar = stealth;
		System.out.println(getInfo());
	}
	
	public int getShadowStrike() { // applies when an enemy doesnt see you,
		return stealth / 10;
	}
	
	// this is used when the main character attacks
	@Override
	public int attack(Character c) { // c = enemy or player's target
		Combat.playerAttacks();
		int damaged;
		if (isDetected) {
			if (criticalStrike()) {
				System.out.println("CRITICAL STRIKE!");
				damaged = ((getDamage() * 2) - c.getBlock());
			}
				
			else
				damaged = (getDamage() - c.getBlock());
			
			if (damaged <= 0)
				damaged = 0;
			
			System.out.println(getName() + " attacked " + c.getName() + " for " + damaged + " damage!");
			c.setCurrHealth(c.getcurrHealth() - damaged);
			
		}
		else { // shadow strike
			System.out.println("SHADOW STRIKE!");
			if (criticalStrike()) {
				System.out.println("CRITICAL STRIKE!");
				damaged = ((getDamage() * 2) + getShadowStrike() - c.getBlock());
			}
				
			else
				damaged = (getDamage() + getShadowStrike() - c.getBlock());
			
			if (damaged <= 0)
				damaged = 0;
			
			detected();
			
			System.out.println(getName() + " attacked " + c.getName() + " for " + damaged + " damage!");
			c.setCurrHealth(c.getcurrHealth() - damaged);
			
		}
		

		
		
		return damaged; // if 0 or lower ang value = death
	}
}
