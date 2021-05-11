package unsw.dungeon;

import java.util.List;
/**
 * This is the interface of goal strategy pattern
 * @author z5086369
 *
 */
public interface winStrategy {
	public boolean goalcheck(String s,  List<Entity> e, int x, int y);
}
