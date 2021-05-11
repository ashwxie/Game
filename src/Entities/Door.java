package Entities;

import unsw.dungeon.Entity;

public class Door extends Entity{
	
	private static int doorCount = 0;
	
	private int id;
	private boolean open;
	
	public Door(int x, int y, String name) {
		super(x, y, name);
		id = 0;
		open = false;
	}

	/**
	 * getters & setters
	 * @return
	 */	
	public int getCount() {
		return doorCount;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean getOpen() {
		return open;
	}
	
	public void setID(int i) {
		id = i;
	}
	
	public void setOpen() {
		open = true;		
	}
	
	public void addCount() {
		doorCount++;
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
	
	@Override
    public boolean checkTrue(int playerx, int playery, int x, int y, String direction) {    	
    	if(direction.equals("up")) {
    		if(playerx == x && playery-1 == y) return true;
    	}else if(direction.equals("down")) {
    		if(playerx == x && playery+1 == y) return true;
    	}else if(direction.equals("left")) {
    		if(playerx-1 == x && playery == y) return true;
    	}else if(direction.equals("right")) {
    		if(playerx+1 == x && playery == y) return true;
    	}
    	return false;
    }
    
	
	
}
