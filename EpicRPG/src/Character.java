
public class Character {
	protected String name;
	protected int health; // initial health at 20
	protected int currHealth; // current health
	protected int mana; // initial mana at 10
	protected int currMana; // current mana
	protected int speed;
	protected int damage;
	protected int block;
	protected int luck; // maximum of 100 only.
	protected int stealth; // dapat maincharacter lang to
	protected int stealhBar;
	
	Character(){
		this("Default",20, 10, 0, 2, 0, 50, 50);
	}
	
	Character(String name, int health, int mana, int speed, int damage, int block, int luck, int stealth){
		this.name = name;
		this.health = health;
		this.mana = mana;
		this.speed = speed;
		this.damage = damage;
		this.block = block;
		this.luck = luck;
		currHealth = health;
		currMana = mana;
	}
	
	// Getters
	
	String getName() {
		return name;
	}
	
	int getHealth() {
		return health;
	}
	
	int getcurrHealth() {
		return currHealth;
	}
	
	int getMana() {
		return mana;
	}
	
	int getCurrMana() {
		return currMana;
	}
	
	int getSpeed() {
		return speed;
	}
	
	int getDamage() {
		return damage;
	}
	
	int getBlock() {
		return block;
	}
	
	int getLuck() {
		return luck;
	}
	

	// Setters
	
	void setName(String name) {
		this.name = name;
	}
	
	void setHealth(int health) {
		this.health = health;
	}
	
	void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}
	
	void setMana(int mana) {
		this.mana = mana;
	}
	
	void setCurrMana(int currMana) {
		this.currMana = currMana;
	}
	
	void setSpeed(int speed) {
		this.speed = speed;
	}
	
	void setDamage(int damage) {
		this.damage = damage;
	}
	
	void setBlock(int block) {
		this.block = block;
	}
	
	void setLuck(int luck) {
		if (luck >= 100)
			this.luck = 100;
		else
			this.luck = luck;
	}
	
	// Functions
	String getInfo() {
		String info = 
				"= = = I N F O = = =\r\n"
				+ "Name: " + name + "\r\n"
				+ "Health: " + health + "\\" + currHealth + "\r\n"
				+ "Mana:" + mana + "\\" + currMana + "\r\n"
				+ "Damage: " + damage + "\r\n"
				+ "Block: " + block + "\r\n"
				+ "Speed: " + speed + "\r\n"
				+ "Luck: " + luck + "\r\n"
				+ "= = = = = = = = = =";
		
		return info;
	}
	
	int attack(Character c) {
		int damaged = (c.getBlock() - getDamage());
		
		
		return 
	}
}
