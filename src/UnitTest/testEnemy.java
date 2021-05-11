package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Entities.Enemy;
import Entities.Player;
import Entities.Potion;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Goals;
/**
 * 
 * Test enemy's functionalities
 * follow player when player has no potion
 * run away when player has potion
 */


class testEnemy {
	Dungeon dungeon = new Dungeon(10,10);
	Goal goal = new Goal("exit", "AND");
	Goals gs = new Goals(dungeon);
	
	@Test
	public void testEnemyCanfollowPlayer() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 7, 3, "player");
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 6, 3, "enemy");
		dungeon.addEntity(enemy);
		player.moveRight();
		assertEquals(8, player.getX());
		assertEquals(3, player.getY());
		assertEquals(7, enemy.getX());
		assertEquals(3, enemy.getY());
		System.out.println("Enemy follow the player : PASSED");
		
		dungeon.removeEntity(player);
		dungeon.removeEntity(enemy);
	}
	
	@Test
	public void testEnemyRunAway() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 8, 3, "player");
		dungeon.addEntity(player);
		Potion potion = new Potion(7,3, "potion");
		dungeon.addEntity(potion);
		Enemy enemy = new Enemy(dungeon, 4, 3, "enemy");
		dungeon.addEntity(enemy);
		player.moveLeft();
		assertEquals(5, enemy.getX());
		assertEquals(3, enemy.getY());
		player.moveLeft();
		assertEquals(4, enemy.getX());
		assertEquals(3, enemy.getY());
		player.moveLeft();
		assertEquals(3, enemy.getX());
		assertEquals(3, enemy.getY());
		System.out.println("Enemy run away when player is under the potion state : PASSED");
		
	}

}
