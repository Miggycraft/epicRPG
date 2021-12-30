package Characters;

import java.util.Random;

import Game.Combat;

/*
 * Health = Determines your current health
 * Mana = Determines your current mana
 * Damage = Determines the Damage you will Deal
 * Block = Determines the Damage you will Block
 * Luck = Determines the critical chance
 * Stealth = Determines if the enemy will detect you or not, and critical chance damage
 * Foresight = Determines if you will see the hidden enemy, each turn if the enemy is invisible, it's stealth will reduced by the foresight, if 0, the enemy will be shown
 */

public class Character {
	protected String name;
	protected int level; // initial level at 1
	protected int health; // initial health at 20
	protected int currHealth; // current health
	protected int mana; // initial mana at 10
	protected int currMana; // current mana
	protected int damage;
	protected int block;
	protected int luck;
	protected int burning; // burns hp per tick
	public int damageMod; // modifier for damage
	public int blockMod; // modifier for block
	public int luckMod; // modifier for luck
	
	public Character(){
		this("Default",1, 20, 20, 2, 0, 50);
	}
	
	public Character(String name, int level, int health, 
			int mana, int damage, int block, int luck){
		this.name = name;
		this.level = level;
		this.health = health;
		this.mana = mana;
		this.damage = damage;
		this.block = block;
		this.luck = luck;
		currHealth = health;
		currMana = mana;
	}
	
	// Getters
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getcurrHealth() {
		return currHealth;
	}
	
	public int getMana() {
		return mana;
	}
	
	public int getCurrMana() {
		return currMana;
	}
	
	
	public int getDamage() {
		return damage + damageMod;
	}
	
	public int getBlock() {
		return block + blockMod;
	}
	
	public int getLuck() {
		return luck + luckMod;
	}
	
	public int getBurn() {
		return burning;
	}
	

	// Setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public void setCurrMana(int currMana) {
		this.currMana = currMana;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setBlock(int block) {
		this.block = block;
	}
	
	public void setLuck(int luck) {
		this.luck = luck;
	}
	
	public void setBurn(int burning) {
		this.burning = burning;
	}
	
	
	// Functions
	public String getInfo() {
		String info = 
				"= = = I N F O = = =\r\n"
				+ "Level: " + level + "\r\n"
				+ "Name: " + name + "\r\n"
				+ "Health: " + health + "\\" + currHealth + "\r\n"
				+ "Mana:" + mana + "\\" + currMana + "\r\n"
				+ "Damage: " + damage + "(" + damageMod + ")\r\n"
				+ "Block: " + block + "(" + blockMod + ")\r\n"
				+ "Luck: " + luck + "(" + luckMod + "\r\n"
				+ "Status: burn(" + getBurn() + ")\r\n";
		
		return info;
	}
	
	public void statusReset() { // resets all the status modifiers, does not include any non-modifiers
		damageMod = 0; // modifier for damage
		blockMod = 0; // modifier for block
		luckMod = 0; // modifier for luck
	}
	
	public boolean criticalStrike() {
		Random random = new Random();
		int roll = random.nextInt(100) + 1;
		int chance = getLuck() / 4;
		
		return chance > roll;  
	}
	
	public int attack(Character c) { // c = enemy or player's target
		int damaged;
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
		
		
		
		return damaged; // if 0 or lower ang value = death
	}

	public boolean isDead() {
		return currHealth <= 0;
	}
	
	public void burnTick() {
		System.out.println(name + " is burning and lost " + burning + " hp!");
		setCurrHealth(getcurrHealth() - burning);
		burning--;
	}
	
	public boolean isBurning() {
		return burning >= 1;
	}
	
	public void castSpell(String spell, Character target) {
		switch(spell) {
			case "shard": // shoots a crystal shard to a target, ignores armor, 7 mana.
				if (getCurrMana() < 7)
					failCast("magic");
				else {
					successCast(7, "magic");
					target.setCurrHealth(target.getcurrHealth() - 4);
				}
				break;
				
			case "heal": // heals target for 1/3th of their max life, 8 mana.
				if (getCurrMana() < 8)
					failCast("healing");
				else {
					successCast(8, "healing");
					int heal = (int) (getHealth() * 0.33);
					System.out.println(getName() + " recovers for " + heal + "HP!");
					
					if (heal >= getcurrHealth())
						setCurrHealth(getHealth());
					else
						setCurrHealth(getcurrHealth() + heal);
				}
				break;
				
		
			case "ember": // applies a 3 burn to a target, damage reduces overtime, 6 mana.
				if (getCurrMana() < 6)
					failCast("fire");
				else {
					successCast(6, "fire");
					target.setBurn(target.getBurn() + 3);
				}
				break;
			case "enhance": // multiplies your damage by 1.5, 20 mana.
				if (getCurrMana() < 20)
					failCast("enchantment");
				else {
					successCast(20, "enchantment");
					damageMod = (int) (getDamage() * 1.5);
				}
				break;
			case "shallow": // reduces target's armor by 1, 10 mana.
				if (getCurrMana() < 10)
					failCast("curse");
				else {
					successCast(10, "curse");
					target.blockMod--;
				}
				break;
			case "burning sword": // applies a 2 burn to a target, and damages target equal to your damage, 20 mana.
				if (getCurrMana() < 15) {
					System.out.println(name + " tries to cast a fire spell but failed badly!");
					setBurn(getBurn() + 2);
				}
				else {
					successCast(15, "fire");
					target.setBurn(target.getBurn() + 2);
					attack(target);
				}
			break;
			case "burning memory": // multiplies the burn tick by 2, 15 mana.
				if (getCurrMana() < 15)
					failCast("fire");
				else {
					System.out.println(name + " casts a fire spell!");
					setCurrMana(getCurrMana() - 15);
					target.setBurn(target.getBurn() * 2);
				}
				break;
				
			default:
				failCast("a");
			}
	}
	
	public void failCast(String spellType) {
		System.out.println(name + " tries to cast " + spellType + " spell but failed!");
	}

	public void successCast(int manaCost, String spellType) { // used in castSpell function
		System.out.println(name + " casts a " + spellType + " spell!");
		setCurrMana(getCurrMana() - manaCost);
	}

	public void weakenedBlocks() {
		blockMod--;
	}
}
