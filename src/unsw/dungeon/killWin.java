package unsw.dungeon;

import java.util.List;

/**
 * check the entity's name.
 * if it is enemy and which is overlapped with player.
 * check wether the enemy can be killed
 * @author z5086369
 *
 */
public class killWin implements winStrategy {
	@Override
	public boolean goalcheck(String s, List<Entity> d, int x, int y) {
		for(Entity e : d) {
			if(e.getName().equals("enemy")) {
				return false;			
			}
		}		
		return true;
	}
}
