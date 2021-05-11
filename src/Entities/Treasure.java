package Entities;

import unsw.dungeon.Entity;

public class Treasure extends Entity{

	public Treasure(int x, int y, String name) {
		super(x, y, name);
		super.setPickable();
	}

}
