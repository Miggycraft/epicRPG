package backpack;

import Characters.Enemy;
import Characters.MainCharacter;
import Characters.Character;

public class Potions extends Items{
	MainCharacter mc;
	
	private static int potionCreated = 0;
	/*
	 * String type, String subType, int quantity, String name, String description
	 * Items requirements
	 */
	Potions(String potionName, String subType, MainCharacter mc){
		potionCreated++;
		this.mc = mc;
		int quantity = 5; // when initialized, all potions start at 0, Potion class is only used in PotionPack. FOR NOW!
		setQuantity(quantity);
		setName(potionName);
		
	}
	
	public int getPotionCreated() {
		return potionCreated;
	}
	
	
	public void drinkPotion() {
		// this will be used when the potion is drank by a player.
		removeQuantity();
		getEffects(mc);
	}
	
	public void throwPotion(Enemy e) {
		// this will be used when the potion is thrown by a player to a target.
		removeQuantity();
		getEffects(e);
	}
	
	
	// Overload... if blank that means this applies to the main character...
	/*
	 * I FOUND A SOLUTION HOPEFULLY EFFECTIVE SIYA REEEEEEEEEEEEE
	public void getEffects() {
		// this will be used when the potion is being used. can be used by either a player or an enemy.
		int healing;
		
		switch(getName()) {
		case "Healing Potion":
			healing = (int)(getHealth() * 0.33);
			
			if (healing >= (getcurrHealth() - healing)) {
					setCurrHealth(getHealth());
			}
			else {
				setCurrHealth(getcurrHealth() + healing);
			}
			break;
		case "Golden Potion":
			healing = (int)(getHealth() * 0.2);
			setCurrHealth(getcurrHealth() + healing);
			break;
		case "Burning Potion":
			setBurn(getBurn() + 6);
			break;
		case "Passion Potion":
			setBurn(getBurn() + 12);
			damageMod += 6;
			break;
		default:
			System.out.println("Nothing happens...");
		}
	}
	*/
	
	
	public void getEffects(Character c) {
		// this will be used when the potion is being used. can be used by either a player or an enemy.
		int healing;
		
		switch(getName()) {
		case "Healing Potion":
			healing = (int)(c.getHealth() * 0.33);
			System.out.println(c.getName() + " was healed for " + healing + " HP!");
			
			if (healing <= (c.getcurrHealth() - healing)) {
					c.setCurrHealth(c.getHealth());
			}
			else {
				c.setCurrHealth(c.getcurrHealth() + healing);
			}
			break;
		case "Golden Potion":
			healing = (int)(c.getHealth() * 0.2);
			System.out.println(c.getName() + " was powerhealed for " + healing + " HP!");
			c.setCurrHealth(c.getcurrHealth() + healing);
			break;
		case "Burning Potion":
			System.out.println(c.getName() + " sets himself on fire!");
			c.setBurn(c.getBurn() + 3);
			break;
		case "Passion Potion":
			System.out.println(c.getName() + " is burning with determination!");
			c.setBurn(c.getBurn() + 6);
			c.damageMod += 3;
			break;
		default:
			System.out.println("Nothing happens...");
		}
	}
	 
}
