package Entities;

import unsw.dungeon.Entity;

public class Boulder extends Entity {

	public Boulder(int x, int y, String name) {
		super(x, y, name);
	}
	
    @Override
    public boolean check(int x, int y, String direction, Entity entity) {
		if(direction.equals("up")) {
			if(getX() == x && getY()+1 == y) return true;
		}else if(direction.equals("down")) {
			if(getX() == x && getY()-1 == y) return true;
		}else if(direction.equals("left")) {
			if(getX()+1 == x && getY() == y) return true;
		}else if(direction.equals("right")) {
			if(getX()-1 == x && getY() == y) return true;
		}    	
    	return false;
    }

    /**
     * 
     * @param x
     * @param y
     * @param boulderx
     * @param bouldery
     * @param direction
     * 
     * change the boulder's location base on the direction
     */    
    public void boulderMove(int x, int y, int boulderx, int bouldery, String direction) {    	
		if(x+1 == boulderx && y == bouldery && direction.equals("right")) 
	    	x().set(boulderx + 1);
		else if(x-1 == boulderx && y == bouldery && direction.equals("left")) 
	    	x().set(boulderx - 1);
		else if(x == boulderx && y-1 == bouldery && direction.equals("up")) 
			y().set(bouldery - 1);
		else if(x == boulderx && y+1 == bouldery && direction.equals("down")) 
			y().set(bouldery + 1);		
	}
    
}
