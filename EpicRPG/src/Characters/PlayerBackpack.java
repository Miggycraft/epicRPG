package Characters;

import java.util.Scanner;

import Game.Combat;
import backpack.Equipment;
import backpack.PotionPack;
import backpack.Usables;

public class PlayerBackpack extends Combat{
	Scanner input = new Scanner(System.in);
	static MainCharacter mc;
	PotionPack potionContainer;
	Usables usableItems;
	Equipment equipments;
	
	public PlayerBackpack(MainCharacter mc){
		PlayerBackpack.mc = mc;
		potionContainer = new PotionPack(mc);
		usableItems = new Usables();
		equipments = new Equipment();
	}
	
	public String openBackpack() {
		String backpack = "= = = = = I T E M S = = = = =\r\n"
				+ "(A) Open Potion Container\r\n"
				+ "(B) Open Usable Items\r\n"
				+ "(C) Open Equipment Pack\r\n"
				+ "= = = = = = = = = = = = = = =";
		
		return backpack;
	}
	
	public void openPotionContainer() {
		// implement something
		String stringUpper = "= = = = P O T I O N S = = = =\r\n";
		String stringLower = "= = = = = = = = = = = = = = =\r\n";
		char letter = 'A';
		System.out.println(stringUpper);
		if (potionContainer.getPotionQuantity() == 0) {
			System.out.println("You currently have no Potions");
		}
		else {
			for (int i = 0; i < potionContainer.getQuantity(); i++) {
				System.out.printf("(%s) %s [%d]\n", letter, potionContainer.getPotion(i).getName(), potionContainer.getPotion(i).getQuantity());
				letter++;
			}
			
			char choice = ' ';
			choice = input.nextLine().toUpperCase().charAt(0);
			int potionID = choice - 'A';
			
			if ((potionID < 0 || potionID > potionContainer.getQuantity() - 1) ||
					(potionContainer.getPotion(potionID).getQuantity() == 0)) {
				System.out.println(mc.getName() + " failed to use a potion!");
			}
			else {
				System.out.println(potionContainer.getPotion(potionID).getName() + " will be (D)rank / (T)hrown");
				choice = input.nextLine().toUpperCase().charAt(0);
				
				if (choice == 'T') { // throw the potion
					usePotion(potionID, false);
				}
				else if (choice == 'D') { // drink the potion
					usePotion(potionID, true);
				}
				else { // otherz
					System.out.println(mc.getName() + " failed to do an action with " + potionContainer.getPotion(potionID).getName() + "!");
				}
			}
		}
		System.out.println(stringLower);
	}
	
	public void usePotion(int x, boolean toPlayer) {
		if (toPlayer)
			potionContainer.getPotion(x).drinkPotion();
		else
			potionContainer.getPotion(x).throwPotion(enemy);
	}
	
	public void openUsableItems() {
	}
	
	public void openEquipmentPack() {
		String equipmentPack = 
				"= = = W E A P O N S = = =\r\n"
				+ "(A) Artifact - Basic Ring\r\n"
				+ "(B) Artifact - Empty\r\n"
				+ "(C) Weapon - Fist\r\n"
				+ "(D) Shield - Empty\r\n"
				+ "(E) Armor - Basic Clothing\r\n"
				+ "= = = = = = = = = = = = = =";
	}
}
