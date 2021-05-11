package Entities;

import unsw.dungeon.Entity;

public class Potion extends Entity{

	public Potion(int x, int y, String name) {
		super(x, y, name);
		super.setPickable();
	}
	
}
