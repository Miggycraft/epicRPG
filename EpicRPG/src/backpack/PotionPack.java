package backpack;

import Characters.MainCharacter;

public class PotionPack{ 
	// potions that are currently here, idk how to improves this tho honestly adding an array in PotionClass is much better... putang ina pero idk how?
	Potions healingPotion;
	Potions goldenPotion;
	Potions burningPotion;
	Potions passionPotion;
	Potions namelessPotion;
	int potionQuantity = 5; // since there are currently 5 potions you can see.
	
	static MainCharacter mc;
	public PotionPack(MainCharacter mc){
		PotionPack.mc = mc;
		healingPotion = new Potions("Healing Potion", "Health", mc);
		goldenPotion = new Potions("Golden Potion", "Enhance", mc);
		burningPotion = new Potions("Burning Potion", "Curse", mc);
		passionPotion = new Potions("Passion Potion", "Enhance", mc);
		namelessPotion = new Potions("Nameless Potion", "Unknown", mc);
	}
	
	// getters
	public int getPotionQuantity() { // refers the max potions you currently have
		int quantity = 0;
		quantity += healingPotion.getQuantity();
		quantity += goldenPotion.getQuantity();
		quantity += burningPotion.getQuantity();
		quantity += passionPotion.getQuantity();
		quantity += namelessPotion.getQuantity();
		
		return quantity;
	}
	
	public int getQuantity() {
		return potionQuantity;
	}
	
	public Potions getPotion(int potionID) {
		switch(potionID) {
		case 0:
			return healingPotion;
		case 1:
			return goldenPotion;
		case 2:
			return burningPotion;
		case 3:
			return passionPotion;
		case 4:
			return namelessPotion;
		default:
			return null;
		}
	}
}
