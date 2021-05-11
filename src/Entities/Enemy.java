package Entities;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class Enemy extends Entity{

	private Dungeon dungeon;
	
	public Enemy(Dungeon dungeon, int x, int y, String name) {
		super(x, y, name);
		this.dungeon = dungeon;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * @return
	 * 
	 * if the player is invincible, enemy needs to run away
	 * 
	 */	
	public String haspotionmoving(int x, int y, String direction) {	
		if(direction.equals("left")) {
			if(this.dungeon.moveable(this.getX(), this.getY(), "left")  && !dungeon.boulderBlock(this.getX(), this.getY(), "left")) {				
				return "left";
			}else {
				if(this.dungeon.moveable(this.getX(), this.getY(), "up") && !dungeon.boulderBlock(this.getX(), this.getY(), "up") ) 					
					return "up";
				else if(this.dungeon.moveable(this.getX(), this.getY(), "down") && !dungeon.boulderBlock(this.getX(), this.getY(), "down")) 
					return "down";
 			}
		}else if(direction.equals("right")) {
			if(this.dungeon.moveable(this.getX(), this.getY(), "right")  && !dungeon.boulderBlock(this.getX(), this.getY(), "right")) {
				return "right";
			}else {
				if(this.dungeon.moveable(this.getX(), this.getY(), "up")  && !dungeon.boulderBlock(this.getX(), this.getY(), "up")) 
					return "up";
				else if(this.dungeon.moveable(this.getX(), this.getY(), "down") && !dungeon.boulderBlock(this.getX(), this.getY(), "down")) 
					return "down";
 			}
		}else if(direction.equals("up")) {
			if(this.dungeon.moveable(this.getX(), this.getY(), "up")  && !dungeon.boulderBlock(this.getX(), this.getY(), "up")) {
				return "up";
			}else {
				if(this.dungeon.moveable(this.getX(), this.getY(), "left")  && !dungeon.boulderBlock(this.getX(), this.getY(), "left")) 
					return "left";
				else if(this.dungeon.moveable(this.getX(), this.getY(), "right") &&  !dungeon.boulderBlock(this.getX(), this.getY(), "right")) 
					return "right";
 			}
		}else {
			if(this.dungeon.moveable(this.getX(), this.getY(), "down") && !dungeon.boulderBlock(this.getX(), this.getY(), "down")) {
				return "down";
			}else {
				if(this.dungeon.moveable(this.getX(), this.getY(), "left") &&  !dungeon.boulderBlock(this.getX(), this.getY(), "left")) 
					return "left";
				else if(this.dungeon.moveable(this.getX(), this.getY(), "right") && !dungeon.boulderBlock(this.getX(), this.getY(), "right")) 
					return "right";
 			}
		}
		
		return "mid";
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * 
	 * change enemy's location according to the direction
	 */
	public void enemyMoveing(int x, int y, String direction) {
		if(direction.equals("up"))			this.changeXandY(x, y-1);		
		else if(direction.equals("down"))	this.changeXandY(x, y+1);		
		else if(direction.equals("left"))	this.changeXandY(x-1, y);    					
		else if(direction.equals("right"))	this.changeXandY(x+1, y);		
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 * 
	 * Enemy will track the location of player and follow up
	 */	
	public String enemyMove(int x, int y) {
		int horizontal = this.getX() - x;
		int vertical = this.getY() - y;
		String horizontal_direction = "mid";
		String vertical_direction = "mid";
		
		//the direction of the player

		if(horizontal>0) {
			horizontal_direction = "left";
		}else if(horizontal < 0) {
			horizontal_direction = "right";
		}
		if(vertical>0) {
			vertical_direction = "up";
		}else if(vertical < 0) {
			vertical_direction = "down";
		}
		//choosing a direction.
		int tempx = Math.abs(horizontal);
		int tempy = Math.abs(vertical);

		if(tempx == 0 && tempy != 0 ) {				
			if(vertical > 0 && this.dungeon.moveable(this.getX(), this.getY(), "up") && !dungeon.boulderBlock(this.getX(), this.getY(), "up")) {
				return "up";
			}else if(vertical < 0 && this.dungeon.moveable(this.getX(), this.getY(), "down") && !dungeon.boulderBlock(this.getX(), this.getY(), "down")) {
				return "down";
			}
		}
		if(tempy == 0 && tempx != 0 ) {	
			if(horizontal > 0 && this.dungeon.moveable(this.getX(), this.getY(), "left")&& !dungeon.boulderBlock(this.getX(), this.getY(), "left")) return "left";
			else if(horizontal < 0 && this.dungeon.moveable(this.getX(), this.getY(), "right") && !dungeon.boulderBlock(this.getX(), this.getY(), "right")) return "right";
		}

		if(tempx >= tempy) {
			//vertical shorter, so higher priority
			if(this.dungeon.moveable(this.getX(), this.getY(),horizontal_direction) && !dungeon.boulderBlock(this.getX(), this.getY(), horizontal_direction)) {
				return horizontal_direction;
			}else if(this.dungeon.moveable(this.getX(), this.getY(), vertical_direction) && !dungeon.boulderBlock(this.getX(), this.getY(), vertical_direction)) {
				return vertical_direction;
			}else {
				if(vertical_direction.equals("up") && this.dungeon.moveable(this.getX(), this.getY(), "down") &&!dungeon.boulderBlock(this.getX(), this.getY(), "down")) {
					return "down";
				}else if(vertical_direction.equals("down") && this.dungeon.moveable(this.getX(), this.getY(),"up") && !dungeon.boulderBlock(this.getX(), this.getY(), "up")) {
					return "up";
				}else {
					if(horizontal_direction.equals("left") && this.dungeon.moveable(this.getX(), this.getY(), "right") &&  !dungeon.boulderBlock(this.getX(), this.getY(), "right")) {
						return "right";
					}else {
						return "left";
					}
				}
			}
		}else {
			if(this.dungeon.moveable(this.getX(), this.getY(), horizontal_direction) &&  !dungeon.boulderBlock(this.getX(), this.getY(),horizontal_direction)) {
				return horizontal_direction;
			}else if(this.dungeon.moveable(this.getX(), this.getY(), vertical_direction) && !dungeon.boulderBlock(this.getX(), this.getY(), vertical_direction)) {
				return vertical_direction;
			}else {
				if(horizontal_direction.equals("left") && this.dungeon.moveable(this.getX(), this.getY(),"right")  && !dungeon.boulderBlock(this.getX(), this.getY(), "right")) {
					return "right";
				}else if(horizontal_direction.equals("right") && this.dungeon.moveable(this.getX(), this.getY(), "left") && !dungeon.boulderBlock(this.getX(), this.getY(), "left")) {
					return "left";
				}else {
					if(vertical_direction.equals("up") && this.dungeon.moveable(this.getX(), this.getY(), "down") && !dungeon.boulderBlock(this.getX(), this.getY(), "down")) {
						return "down";
					}else {
						return "up";
					}
				}
			}
		}
	
}


}
