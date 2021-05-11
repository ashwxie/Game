package Entities;

import unsw.dungeon.Entity;

public class Sword extends Entity{

	public Sword(int x, int y, String name) {
		super(x, y, name);
		super.setPickable();
	}

}
