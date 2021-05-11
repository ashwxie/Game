package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Entities.Exit;
import Entities.Player;
import unsw.dungeon.*;

class testGoals {
	Dungeon dungeon = new Dungeon(10,10);
	Goal goal = new Goal("exit", "AND" );
	Goals gs = new Goals(dungeon);
	
	@Test
	void test() {
		gs.addGoal(goal);
		dungeon.loadGoal(gs);		
		Exit e = new Exit(2, 2, "exit");
		
		dungeon.addEntity(e);
		Player player = new Player(dungeon, 1, 2, "player");
		dungeon.addEntity(player);		
		player.moveRight();		
		assertTrue(dungeon.succeed(2, 2));
		System.out.println("When player reached the eixt && There's no other goals: PASSED");

	}

}
