package unsw.dungeon;

import java.util.List;

/**
 * This part is for calling the strategies of goals
 * @author z5086369
 *
 */
public class Context {
	private winStrategy strategy;
	
	public Context(winStrategy strategy) {
		this.strategy = strategy;
	}
	public boolean executeStrategy(String s, List<Entity> e, int x, int y) {
		return strategy.goalcheck(s, e, x, y);
	}
}
