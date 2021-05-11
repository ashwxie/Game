package Entities;

import unsw.dungeon.Entity;

public class Exit extends Entity{
	
	private boolean gameover = false;
	public Exit(int x, int y, String name) {
		super(x, y, name);
		this.gameover = false;
	}
	
	/**
	 * check wther the game is finished
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean endCheck(int x, int y) {
		if(this.getX() == x && this.getY() == y) {
			this.gameover = true;
		}
		return this.gameover;
	}

	public boolean isGameover() {
		return gameover;
	}

		
}
