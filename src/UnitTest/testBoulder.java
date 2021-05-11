package UnitTest;



import static org.junit.Assert.assertEquals;


import org.junit.Test;

import Entities.Boulder;
import Entities.Player;
import Entities.Switch;
import Entities.Wall;
import unsw.dungeon.*;


public class testBoulder extends testSetup{
	Dungeon dungeon = new Dungeon(10,10);
	Goal goal = new Goal("treasure", "null");
	Goals gs = new Goals(dungeon);

	@Test
	public void moveBoulder() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Boulder boulder = new Boulder(2,2, "boulder");
		// assume the player is on [1,2], pushing right 
		boulder.boulderMove(1, 2, 2, 2, "right");
		assertEquals(3, boulder.getX());
		assertEquals(2, boulder.getY());
		System.out.println("test bounlder move right : PASSED");
		
		boulder.boulderMove(3, 2, 2, 2, "left");
		assertEquals(1, boulder.getX());
		assertEquals(2, boulder.getY());
		System.out.println("test bounlder move left : PASSED");
		
		boulder.boulderMove(2, 1, 2, 2, "down");
		assertEquals(2, boulder.getX());
		assertEquals(3, boulder.getY());
		System.out.println("test boulder move down : PASSED");
		
		boulder.boulderMove(2, 3, 2, 2, "up");
		assertEquals(2, boulder.getX());
		assertEquals(1, boulder.getY());
		System.out.println("test boulder move up : PASSED");
	}
	
	
	@Test
	public void boulderGetBlockedByBoulder() {
		//gs.addGoal(goal);
		dungeon.loadGoal(gs);
    	Boulder boulder1 = new Boulder(2, 2, "boulder");
    	dungeon.addEntity(boulder1);
    	Boulder boulder2 = new Boulder(3, 2, "boulder");
    	dungeon.addEntity(boulder2);
    	Player player = new Player(dungeon,1,2, "player");
    	dungeon.addEntity(player);
    	player.moveRight();
		assertEquals(1, player.getX());
		assertEquals(2, player.getY());
		assertEquals(2, boulder1.getX());
		assertEquals(2, boulder1.getY());
		assertEquals(3, boulder2.getX());
		assertEquals(2, boulder2.getY());
    	System.out.println("boulder gets blocked by boulder -- move right : PASSED");
    	
    	player.changeXandY(4, 2);
    	player.moveLeft();
		assertEquals(4, player.getX());
		assertEquals(2, player.getY());
		assertEquals(2, boulder1.getX());
		assertEquals(2, boulder1.getY());
		assertEquals(3, boulder2.getX());
		assertEquals(2, boulder2.getY());
    	System.out.println("boulder gets blocked by boulder -- move left : PASSED");
    	
    	dungeon.removeEntity(boulder2);
    	boulder2 = new Boulder(2, 3, "boulder");
    	dungeon.addEntity(boulder2);

    	player.changeXandY(2, 1);
    	player.moveDown();
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		assertEquals(2, boulder1.getX());
		assertEquals(2, boulder1.getY());
		assertEquals(2, boulder2.getX());
		assertEquals(3, boulder2.getY());
    	System.out.println("boulder gets blocked by boulder -- move down : PASSED");
    	
      	player.changeXandY(2, 4);
    	player.moveUp();
		assertEquals(2, player.getX());
		assertEquals(4, player.getY());
		assertEquals(2, boulder1.getX());
		assertEquals(2, boulder1.getY());
		assertEquals(2, boulder2.getX());
		assertEquals(3, boulder2.getY());
    	System.out.println("boulder gets blocked by boulder -- move up : PASSED");
    	
    	dungeon.removeEntity(boulder1);
    	dungeon.removeEntity(boulder2);
    	dungeon.removeEntity(player);
	}
	
	@Test
	public void puttingBoulderOnSwitch() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Switch switch1 = new Switch(3, 3, "switch");
		dungeon.addEntity(switch1);
		Player player  = new Player(dungeon, 3, 1, "player");
		dungeon.addEntity(player);
		Boulder boulder1 = new Boulder(3, 2, "boulder");
		dungeon.addEntity(boulder1);
		player.moveDown();
		System.out.println("-== " + switch1.getState());
		System.out.println("boulder on switch : PASSED");	
		
		dungeon.removeEntity(boulder1);
		dungeon.removeEntity(switch1);
		dungeon.removeEntity(player);
	}
	
	@Test
	public void boulderGetBlockedByWall() {
		//gs.addGoal(goal);
		dungeon.loadGoal(gs);
		Boulder boulder1 = new Boulder(2, 2, "boulder");
		dungeon.addEntity(boulder1);
		Wall wall = new Wall(3, 2, "wall");
		dungeon.addEntity(wall);
		Player player = new Player(dungeon, 1, 2, "player");
		
		player.moveRight();
		assertEquals(1, player.getX());
		assertEquals(2, player.getY());
		System.out.println("bouldes gets blocked by wall -- move right: PASSED");
		
		dungeon.removeEntity(player);
		dungeon.removeEntity(boulder1);
		dungeon.removeEntity(wall);
		
		System.out.println("-----------------BOULDER TEST ALL PASSED-------------------");
	}
}
