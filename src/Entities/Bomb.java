package Entities;

import unsw.dungeon.Entity;

public class Bomb extends Entity{

	private boolean lit;
	
	public Bomb(int x, int y, String name) {
		super(x, y, name);
		super.setPickable();
		lit = false;
	}
	
	/**
	 * check whether the bomb is lit
	 * @return
	 */
	public boolean ifLit() {
		return lit;
	}

	/**
	 * change bomb state
	 */	
	public void lit() {
		lit = true;
	}

}
