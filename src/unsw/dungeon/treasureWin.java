package unsw.dungeon;

import java.util.List;

/**
 * This is a part of goal strategy
 * if player collect all treasure, return true
 * goal achieved
 * @author z5086369
 *
 */

public class treasureWin implements winStrategy{
	@Override
	public boolean goalcheck(String s, List<Entity> d, int x, int y) {
		for(Entity e : d) {
			if(e.getName().equals("treasure")) {
					return false;
			}
			
		}		
		return true;
	}
}
