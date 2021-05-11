package Entities;

import unsw.dungeon.Entity;

public class Wall extends Entity {
	
    public Wall(int x, int y, String name) {
        super(x, y, name);
    }
    @Override
    public boolean check(int x, int y, String direction, Entity entity) {
		if(direction.equals("up")) {
			if(entity.getX() == x && entity.getY() + 1 == y)	return false;    			
		}else if(direction.equals("down")) {
			if(entity.getX() == x && entity.getY() - 1 == y) 	return false;    			
		}else if(direction.equals("left")) {
			if(entity.getY() == y && entity.getX() + 1 == x) 	return false;    			
		}else if(direction.equals("right")) {
			if(entity.getY() == y && entity.getX() - 1 == x ) 	return false;    
		}
    	return true;   	
    }
}
