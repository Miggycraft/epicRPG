package Game;

import java.util.Random;
import java.util.Scanner;

import Characters.Enemy;
import Characters.MainCharacter;
import Characters.PlayerBackpack;
public class Combat {
	// pls implement playerAttack boolean, not yet implemented
	static boolean playerFlee;
	static boolean playerAttacked;
	static boolean spotLoop;
	protected static Enemy enemy;
	static PlayerBackpack playerBackpack;
	public static Scanner input = new Scanner(System.in);
	public static void startFight(MainCharacter player, Enemy enemy){
		Combat.enemy = enemy;
		int numberOfTurns = 0;
		playerBackpack = new PlayerBackpack(player);
		playerFlee = false;
		playerAttacked = false;
		spotLoop = false;
		System.out.println(player.getName() + " has encountered " + enemy.getName());
		player.setStealthBar(player.getStealthBar() - enemy.getForesight()); // stealth is being reduced
		
		while(!player.isDead() && !enemy.isDead()) { // will loop until either dies
			numberOfTurns++;
			
			if (numberOfTurns % 3 == 0) {
				System.out.println("The shields are weakening!");
				player.weakenedBlocks();
				enemy.weakenedBlocks();
			}
			time();
			if (playerFlee)
				break;
			
			
			if (player.isBurning())
				player.burnTick();
			
			if (enemy.isBurning())
				enemy.burnTick();
			
			if (player.isDead()) {
				System.out.println(player.getName() + " was killed in action!");
				System.out.println(player.getInfo());
				System.out.println("You failed!");
				System.exit(1);
			}
			
			if (enemy.isDead()) {
				System.out.println(player.getName() + " has slain " + enemy.getName() + "!");
				System.out.println(player.getName() + " has gained " + enemy.getXP() + " xp!");
				player.setXP(player.getXP() + enemy.getXP());
				player.finishedCombat();
				break;
			}
			
			if (playerAttacked) {
				player.detected();
				player.setStealthBar(0);
			}
			
			if (player.isSeen() & !spotLoop) {
				player.detected();
				System.out.println(enemy.getName() + " saw you!");
				spotLoop = true;
			}
			
			if (!player.isSeen()) {
				spotLoop = false;
				System.out.println(enemy.getName() + " is unaware of your presence!"); // stealth is still high
				player.setStealthBar(player.getStealthBar() - enemy.getForesight());
				playerTurn(player, enemy);
			}
			else { // player is now seen
				playerTurn(player, enemy);
				
				if (enemy.isDead()) // incase player kills enemy
					break;
				
				enemyTurn(player, enemy);
			}
		}
		
	}
	
	public static void playerTurn(MainCharacter player, Enemy enemy) {
		char choice = 'X';
		showInfo(player, enemy);
		System.out.println(
				"=  O P T I O N S = \r\n"
				+ "(A) Attack\r\n"
				+ "(B) Cast Spell\r\n"
				+ "(C) Use Items\r\n"
				+ "(D) Flee\r\n");
		choice = input.nextLine().toLowerCase().charAt(0);
		switch(choice) {
		case 'a': player.attack(enemy); break;
		case 'b': 
			System.out.println("What spell will " + player.getName() + " cast?");
			String spell = input.nextLine();
			player.castSpell(spell, enemy);
			break;
		case 'c': // backpack
			System.out.println(playerBackpack.openBackpack());
			char bpchoice = ' ';
			bpchoice = input.nextLine().charAt(0);
			switch(bpchoice) {
			case 'a': // open potion contaner
				playerBackpack.openPotionContainer();
				break;
			case 'b': // open usable items
				break; // wip
			case 'c': // open weapon pack
				break; // wip
				default:
					System.out.println(player.getName() + " did not do anything with his backpack!");
			}
			
			break;
		case 'd':
			if (successFlee(player)) {
				System.out.println(player.getName() + " successfully fled!");
				playerFlee = true;
			}
			else
				System.out.println(player.getName() + " tried to flee but failed!");
			break;
		default:
			
			System.out.println(player.getName() + " failed to do something!");
		}
	}
	
	private static boolean successFlee(MainCharacter player) {
		Random random = new Random();
		int roll = random.nextInt(100);
		
		return player.getLuck() + 20 > roll;
	}

	public static void time() { // is used so that its not going to spam
		long time = 1000000000;
		for (long i = 0; i < time; i++) {
			
		}
	}

	public static void enemyTurn(MainCharacter player, Enemy enemy) {
//		System.out.println(
//				"= = B L O C K = =\r\n"
//				+ "(A) weapon a\r\n"
//				+ "(B) weapon b\r\n"
//				+ "(C) weapon c\r\n"
//				+ "(D) dodge\r\n"
//				+ "= = = = = = = = = =");
		
		// so far attack palang na implement ko
		enemy.attack(player);
	}
	
	public static void showInfo(MainCharacter player, Enemy enemy) {
		System.out.println(
				"= = = = = = = = C O M B A T = = = = = = = =\r\n"
				+ "Level: " + player.getLevel()+ "(" + player.getXP() + ")\t\tLevel: " + enemy.getLevel() + "(" + enemy.getXP() + ")\r\n"
				+ "Name: " + player.getName()+ "\t\tName: " + enemy.getName() + "\r\n"
				+ "Health: " + player.getHealth() + "/" + player.getcurrHealth()+ "\t\tHealth: " + enemy.getHealth() + "/" + enemy.getcurrHealth() + "\r\n"
				+ "Mana:" + player.getMana()+ "/" + player.getCurrMana()+ "\t\tMana: " + enemy.getMana() + "/" + enemy.getCurrMana() + "\r\n"
				+ "Damage: " + player.getDamage()+ "(" + player.damageMod + ")\t\tDamage: " + enemy.getDamage() + "(" + enemy.damageMod + ")\r\n"
				+ "Block: " + player.getBlock() + "(" + player.blockMod + ")\t\tBlock: " +enemy.getBlock() + "(" + enemy.blockMod +")\r\n"
				+ "Luck: " + player.getLuck() + "(" + player.luckMod + ")\t\tLuck: " + enemy.getLuck() + "(" + enemy.luckMod + ")\r\n"
				+ "Stealth :" + player.getStealth() + "/" + player.getStealthBar() + "\t\tForesight: " + enemy.getForesight() + "\r\n"
				+ "Status: burn(" + player.getBurn() + ")\t\tStatus: burn(" + enemy.getBurn() + ")");
	}

	public static void playerAttacks() {
		playerAttacked = true;
	}
}
