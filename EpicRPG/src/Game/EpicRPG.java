package Game;

import Characters.Enemy;
import Characters.MainCharacter;

public class EpicRPG {
	public static void main(String[] args) {
		MainCharacter player = new MainCharacter(10);
		player.setName("Atuel");
		player.setStealthBar(100);
		Enemy enemy = new Enemy().generateEnemy(1);
		while(true) {
			Combat.startFight(player, enemy);
			enemy = new Enemy().generateEnemy(1);
			player.rest();
		}
	}
}
