/**
 * 
 */
package UnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Entities.Bomb;
import Entities.Enemy;
import Entities.Key;
import Entities.Player;
import Entities.Potion;
import Entities.Sword;
import Entities.Treasure;
import Entities.Wall;
import unsw.dungeon.*;

/**
 * @This test will test the functionality of the player
 * Player cannot cross the wall
 * Player can Kill the enemy
 * Player can push the boulder
 * Player can pick up the pickable entity <treasure, bomb, sword, potion, key>
 *
 */
public class testPlayer {
	Dungeon dungeon = new Dungeon(10,10);
	Goal goal = new Goal("exit", "AND");
	Goals gs = new Goals(dungeon);
	
	@Test
	public void playerCanMove() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 2, 2, "player");
		dungeon.addEntity(player);
		player.moveRight();
		assertEquals(3, player.getX());
		assertEquals(2, player.getY());
		player.moveLeft();
		assertEquals(2, player.getX());
		assertEquals(2, player.getY());
		player.moveUp();
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		player.moveDown();
		assertEquals(2, player.getX());
		assertEquals(2, player.getY());
		System.out.println("Player move test : PASSED");
		dungeon.removeEntity(player);
	}
	
	@Test
	public void playerCanBeBlocked() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 2, 2, "player");
		dungeon.addEntity(player);
		Wall wall = new  Wall(3, 2, "wall");
		dungeon.addEntity(wall);
		player.moveRight();
		assertEquals(2, player.getX());
		assertEquals(2, player.getY());
		assertEquals(3, wall.getX());
		assertEquals(2, wall.getY());
		System.out.println("Player can be blocked");
		dungeon.removeEntity(player);
		dungeon.removeEntity(wall);
	}
	
	
	

	@Test
	public void playerCanBeKilledByEnemy() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 2, 2, "player");
		dungeon.addEntity(player);
		Enemy enemy = new  Enemy(dungeon, 2, 4, "enemy");
		dungeon.addEntity(enemy);
		player.moveDown();
		//assertEquals("fail", dungeon.fail());
		System.out.println("Player can be killed by enemy");
		dungeon.removeEntity(player);
		dungeon.removeEntity(enemy);
	}
	
	@Test
	public void playerKillTheEnemyWithSwordAndPotionAndBomb() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 2, 2, "player");
		dungeon.addEntity(player);
		Sword sword = new Sword(2,3, "sword");
		dungeon.addEntity(sword);
		Enemy enemy = new  Enemy(dungeon, 2, 5, "enemy");
		dungeon.addEntity(enemy);
		player.moveDown();  // [2,3]
		player.moveDown();  // [2,4]
		player.moveDown();  // [2,5]
		assertEquals(2, player.getX());
		assertEquals(5, player.getY());
		assertEquals(0, sword.getX());
		assertEquals(0, sword.getY());
		assertEquals(0, enemy.getX());
		assertEquals(0, enemy.getY());
		System.out.println("Player Can pick up sword and Kill Enemy : PASSED");	
		
		dungeon.removeEntity(sword);
		Enemy enemy1 = new  Enemy(dungeon, 2, 8, "enemy");
		dungeon.addEntity(enemy1);
		Potion potion = new Potion(2,6,"potion");
		player.moveDown(); //[2, 6] has potion
		assertEquals(0,player.getPotion());
		System.out.print("Player can pick up an potion : PASSED");
		player.moveDown(); //[2, 7] potion state and enemy get killed
		player.moveDown(); //[2, 8] 
		assertEquals(2, player.getX());
		assertEquals(8, player.getY());
		assertEquals(0, enemy.getX());
		assertEquals(0, enemy.getY());
		System.out.println("Player Can pick up potion and Kill Enemy : PASSED");
		dungeon.removeEntity(potion);
		dungeon.removeEntity(enemy);
		dungeon.removeEntity(enemy1);
		
		Bomb bomb = new Bomb(5, 2, "bomb");
		dungeon.addEntity(bomb);
		enemy = new Enemy(dungeon ,0, 2, "enemy"); 
		dungeon.addEntity(enemy);
		player.changeXandY(4, 2);
		player.moveRight(); //[2, 5]
		System.out.print("Player can pick up an unlit bomb : PASSED");
		dungeon.removeEntity(bomb);
		dungeon.removeEntity(player);
		dungeon.removeEntity(enemy);
	}
	
	
	@Test
	public void playerCanPickUpKey() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 2, 2, "player");
		dungeon.addEntity(player);
		Key key = new Key(3, 2, "key");
		dungeon.addEntity(key);
		player.moveRight();
		assertEquals(key, player.getKeys().get(0));
		System.out.println("Player can Pick up the Key : PASSED");

		dungeon.removeEntity(player);
		dungeon.removeEntity(key);
	}

	@Test
	public void treasureCanBePick() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Player player = new Player(dungeon, 2, 2, "player");
		dungeon.addEntity(player);
		Treasure treasure = new Treasure(2, 3, "treasure");
		dungeon.addEntity(treasure);
		Treasure treasure1 = new Treasure(2, 4, "treasure");
		dungeon.addEntity(treasure1);
		player.moveDown();
		assertEquals(1, player.getTreasure());
		player.moveDown();
		assertEquals(2, player.getTreasure());
		System.out.println("     Player can Pick up the treasure : PASSED");
		dungeon.removeEntity(player);
		dungeon.removeEntity(treasure);
		dungeon.removeEntity(treasure1);

	}
}
