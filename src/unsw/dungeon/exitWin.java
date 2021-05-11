package unsw.dungeon;

import java.util.List;
/**
 * check the location of the player and exit location.
 * If 2 locations are overlapped, which means player is on exit
 * achieved goal
 * @author z5086369
 *
 */
public class exitWin implements winStrategy{
	@Override
	public boolean goalcheck(String s, List<Entity> d, int x, int y) {
		for(Entity e : d) {
			if(e.getName().equals("exit") && e.getX() == x && e.getY() == y) {
				return true;
			}
		}		
		return false;
	}
}
