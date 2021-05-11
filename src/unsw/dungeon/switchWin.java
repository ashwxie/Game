package unsw.dungeon;

import java.util.List;

import Entities.Switch;

/**
 * This is a part of goal strategy pattern
 * it will check all of the switch's state
 * if all close, goal achieved
 * @author z5086369
 *
 */
public class switchWin implements winStrategy{
	@Override
	public boolean goalcheck(String s, List<Entity> d, int x, int y) {
		for(Entity e : d) {
			if(e.getName().equals("switch")) {
				Switch sw = (Switch) e;
				if(!sw.getState()) {
					return false;
				}
			}
		}		
		return true;
	}

}
