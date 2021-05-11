package Entities;

import unsw.dungeon.Entity;

public class Key extends Entity{

	
	private static int keyCount = 0;
	private int id;
	
	public Key(int x, int y, String name) {
		super(x, y, name);
		super.setPickable();
		id = 0;
	}
	/**
	 * getters and setters
	 */
	public int getID() {
		return id;
	}
	
	public void setID(int i) {
		id = i;
	}
	
	public int getCount() {
		return keyCount;
	}
	
	/**
	 * count keys
	 */
	
	public void addCount() {
		keyCount++;
	}
	
}
