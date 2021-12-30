package Characters;

import java.util.Random;

public class Enemy extends Character{
	protected int foresight;
	protected int xp;
	protected String classType;

	public Enemy(){
		super();
		xp = 5;
		foresight = 10;
		this.classType = "Unknown";
	}
	
	public Enemy(String name, int level, int health, 
			int mana, int damage, int block, int luck, int xp, int foresight, String classType){
		super((name + " the " + classType), level, health, 
				mana, damage, block, luck);
		this.xp = xp;
		this.foresight = foresight;
		this.classType = classType;
	}
	
	// getter
	public int getXP() { // xp given to the player once killed
		return xp;
	}
	
	public int getForesight() {
		return foresight;
	}
	
	public String getClassType() {
		return classType;
	}
	
	// setter
	public void setXP(int xp) {
		this.xp = xp;
	}
	
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	public void setForesight(int foresight) {
		this.foresight = foresight;
		
	}
	
	// functions
	@Override
	public String getInfo() {
		String info = 
				"= = = I N F O = = =\r\n"
				+ "Level: " + level + "\r\n"
				+ "Name: " + name + "\r\n"
				+ "Health: " + health + "/" + currHealth + "\r\n"
				+ "Mana:" + mana + "/" + currMana + "\r\n"
				+ "Damage: " + damage + "(" + damageMod + ")\r\n"
				+ "Block: " + block + "(" + blockMod + ")\r\n"
				+ "Luck: " + luck + "(" + luckMod + ")\r\n"
				+ "Status: burn(" + getBurn() + ")\r\n"
				+ "Foresight: " + foresight + "\r\n";
		
		return info;
	}

	public Enemy generateEnemy(int idLocation) { // 1 - town, 2 - forest, 3 - swamp, 4 - castle
		idLocation--;
		String[][] nameList = {
				{"Tirell", "Thurmond", "Berthe", "Dion", "Danny", "Deven", "Kasey", "Robin"},// town
				{}, // forest
				{}, // swamp
				{}, // castle
		};
		
		String[][] classType = {
				{"Bandit", "Thief", "Mercenary", "Soldier", "Veteran"}, // town
				{}, // forest
				{}, // swamp
				{}, // castle
		};
		Random random = new Random();
		String randomName = nameList[idLocation][random.nextInt(nameList[idLocation].length)];
		int ranClassType = random.nextInt(classType[idLocation].length);
		String ranNameClassType = classType[idLocation][ranClassType];
		int ranLevel = ranClassType + 1; 
		int ranHealth;
		int ranMana;
		int ranDamage;
		int ranBlock;
		int ranLuck;
		int ranXP;
		int ranForesight;
		
		int[] stats = genStats(idLocation, ranClassType);
		ranHealth = stats[0];
		ranMana = stats[1];
		ranDamage = stats[2];
		ranBlock = stats[3];
		ranLuck = stats[4];
		ranXP = stats[5];
		ranForesight = stats[6];
		
		return new Enemy(randomName, ranLevel, ranHealth, ranMana, ranDamage, ranBlock, 
				ranLuck, ranXP, ranForesight, ranNameClassType);
	}
	
	public int[] genStats(int IdLocation, int classType) {
		int[] stats = new int[7];
		if (IdLocation == 0) { // forest
			switch(classType) {
			case 0: // bandit
				stats[0] = 10; // health
				stats[1] = 0; // mana
				stats[2] = 2; // damange
				stats[3] = 1; // block
				stats[4] = 50; // luck
				stats[5] = 3; // xp
				stats[6] = 10; // foresight
				break;
			case 1: // thief
				stats[0] = 8; // health
				stats[1] = 0; // mana
				stats[2] = 3; // damange
				stats[3] = 0; // block
				stats[4] = 75; // luck
				stats[5] = 3; // xp
				stats[6] = 15; // foresight
				break;
			case 2: // mercinary
				stats[0] = 12; // health
				stats[1] = 0; // mana
				stats[2] = 1; // damange
				stats[3] = 1; // block
				stats[4] = 25; // luck
				stats[5] = 4; // xp
				stats[6] = 10; // foresight
				break;
			case 3: // soldier
				stats[0] = 15; // health
				stats[1] = 0; // mana
				stats[2] = 1; // damange
				stats[3] = 2; // block
				stats[4] = 25; // luck
				stats[5] = 5; // xp
				stats[6] = 10; // foresight
				break;
			case 4: // veteran
				stats[0] = 19; // health
				stats[1] = 0; // mana
				stats[2] = 3; // damange
				stats[3] = 2; // block
				stats[4] = 0; // luck
				stats[5] = 8; // xp
				stats[6] = 7; // foresight
				break;
			default: // lol
			}
		}
		
		else if (IdLocation == 1) { // swamp
			
		}
		
		else if (IdLocation == 2) { // castle
			
		}
		
		return stats;
	}
	
}
