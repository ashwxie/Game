package Entities;

import unsw.dungeon.Entity;

public class Switch extends Entity{

	private boolean state;
	
	public Switch(int x, int y, String name) {
		super(x, y, name);
		state = false;
	}
	
	/**
	 * getter and setter of switch state
	 * @return
	 */
	public boolean getState() {
		return state;
	}
	
	public void setState() {
		state = true;
	}
	
	public void reSetState() {
		state = false;
	}
	
}
