package Entities;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private int sword;
    private int potion;
    private boolean bomb;
    private ArrayList<Key> keys;
    private StringProperty Treasure;
    private StringProperty Potion;
    private StringProperty Sword;
    private StringProperty Keys;
    private StringProperty Bomb;

    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, String name) {
        super(x, y, name);
        this.dungeon = dungeon;
        this.sword = 0;
        this.potion = 0;
        this.bomb = false;        
        this.keys = new ArrayList<>();
        String s = "0";
        this.Treasure = new SimpleStringProperty(s);
        this.Potion = new SimpleStringProperty(s);
        this.Sword = new SimpleStringProperty(s);
        this.Keys = new SimpleStringProperty(s);
        this.Bomb = new SimpleStringProperty(s);
    }
    
    /**
     * getters for pickable entity
     */

    public int getSword() {
    	return sword;
    }
    
    public int getTreasure() {
    	String s = Treasure.get();
    	return Integer.parseInt(s);
    }
    
    public StringProperty getTreasureProperty() {
    	return Treasure;
    }
    
    public StringProperty getSwordProperty() {
    	return Sword;
    }
    
    public StringProperty getBombProperty() {
    	return Bomb;
    }
    
    public int getPotion() {
    	return potion;
    }
    
    public StringProperty getPotionProperty() {
    	return Potion;
    }

    public boolean getBomb() {
    	return bomb;
    }
    
    public ArrayList<Key> getKeys(){
    	return keys;
    }
    
    public StringProperty getKeyProperty() {
    	return Keys;
    }
    
    /**
     * player moves
     */

    public void moveUp() {
    	if (getY() > 0) {
    		moveOperator(getX(), getY(), "up");
    	}
    }
    public void moveDown() {
    	if (getY() < dungeon.getHeight() - 1) {
    		moveOperator(getX(), getY(), "down");
        }
    }
    
    public void moveLeft() {
    	if (getX() > 0) {
    		moveOperator(getX(),getY(), "left");
        }
    }

    public void moveRight() {
    	if (getX() < dungeon.getWidth() - 1) {
    		moveOperator(getX(), getY(), "right");
        }
    }
    
    
    /**
     * Player picks
     */
    
    public void pickSword() {
    	sword = 5;
    	Sword.set(Integer.toString(sword));
    }
    
    public void pickPotion() {
    	potion = 10;    
    	Potion.set(Integer.toString(potion));
    }
    
    public void pickTreasure() {		
		int i = Integer.parseInt(Treasure.get());
		i++;
		Treasure.set(Integer.toString(i));
    }
    
    public void pickBomb() {
    	bomb = true;  	
    	String s = "1";
    	Bomb.set(s);
    }
    
    public void pickKey(Key k) {
    	keys.add(k);
    	int i = Integer.parseInt(Keys.get().toString());
    	i++;
    	Keys.set(Integer.toString(i));
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param direction
     * this function will check wether the player achieved the goals
     * if not keep working on the goals
     */
    
    public void moveOperator(int x, int y, String direction) {
    	if(dungeon.succeed(x, y)) 
    		System.out.println("YOU WIN");
    	else if(dungeon.fail(x, y))
    		System.out.println("YOU'RE DEAD");
    	else {
    		if(dungeon.moveable(x, y, direction)) {
    			if(dungeon.boulderBlock(x, y, direction)) {
    				hasBounderBlock(x, y, direction);
    			}else {
    				noBounderBlock(x, y, direction);
    			}  			
    		}
    	}
    }
    
    
    /**
     * 
     * @param x
     * @param y
     * @param direction
     * 
     * this function will check whether has boulder block the way and check no boulder or wall block the blouder(going to push)
     */
    public void hasBounderBlock(int x, int y, String direction) {
		if(direction.equals("up")) {
			if(dungeon.ispushable(getX(), getY(), "up")) {
        		dungeon.move(getX(), getY(), "up");
        		y().set(getY() - 1);  
    		}
			dungeon.tellEnemy(getX(), getY(), "up", potion);
			dungeon.twoEntityOverlap(getX(), getY()-1);
		}else if(direction.equals("down")) {
			if(dungeon.ispushable(getX(), getY(), "down")) {
        		dungeon.move(getX(), getY(), "down");
                y().set(getY() + 1);
			}
			dungeon.tellEnemy(getX(), getY(), "down", potion);
    		dungeon.twoEntityOverlap(getX(), getY()+1);
		}else if(direction.equals("left")) {
			if(dungeon.ispushable(getX(), getY(), "left")) {
        		dungeon.move(getX(), getY(), "left");
                x().set(getX() - 1);
			}
			dungeon.tellEnemy(getX(), getY(), "left", potion);
    		dungeon.twoEntityOverlap(getX()-1, getY());
		}else {
			if(dungeon.ispushable(getX(), getY(), "right")) {
        		dungeon.move(getX(), getY(), "right");
        	    x().set(getX() + 1); 
			}
			dungeon.tellEnemy(getX(), getY(), "right", potion);
    		dungeon.twoEntityOverlap(getX()+1, getY());
		}
    }
    
    
    /**
     * 
     * @param x
     * @param y
     * @param direction
     * Check there has no boulder or wall block the current wall
     */
   
    public void noBounderBlock(int x, int y, String direction) {
    	if(direction.equals("up")) {
    		y().set(getY() - 1);
    		dungeon.tellEnemy(getX(), getY(), "up", potion);
      	    pick(getX(), getY());
    	}else if(direction.equals("down")) {
		    y().set(getY() + 1);
			dungeon.tellEnemy(getX(), getY(), "down", potion);
 	        pick(getX(), getY());
    	}else if(direction.equals("left")) {
            x().set(getX() - 1);
			dungeon.tellEnemy(getX(), getY(), "left", potion);		
    	    pick(getX(), getY());
    	}else {
		    x().set(getX() + 1); 
			dungeon.tellEnemy(getX(), getY(), "right", potion);
    	    pick(getX(), getY());    
    	}
    }

    /**
     * 
     * @param x
     * @param y
     * player is going to pick the pickable entities
     * When player use potion kill the enemy, the potion state will gone
     * When player use sword kill the enemy, the sword use change will reduce 1
     */
    
    public void pick(int x, int y) {
    	if(potion > 0) {
    		potion--;    
    		Potion.set(Integer.toString(potion));
    	}

    	for(Entity e: dungeon.getEntities()) {
    		if(e.getX() == x && e.getY() == y){
    			if(e.pickable()) {
    				if(e.getName().equals("treasure"))		pickTreasure();    				
    				else if(e.getName().equals("sword"))	pickSword();    
    				else if(e.getName().equals("potion"))	pickPotion();
    				else if(e.getName().equals("bomb") && !((Bomb)e).ifLit())	pickBomb();      
    				else if(e.getName().equals("key"))		pickKey((Key) e);
    				    				
    				dungeon.removeStuff(getX(), getY());
    				break;
    			}
    			else if(e.getName().equals("enemy")) {
    				boolean flag = false;
    				if(sword > 0 || potion > 0) {
    					sword--;
    					Sword.set(Integer.toString(sword));
    					dungeon.removeStuff(getX(), getY());
				   }
    				else	dungeon.fail(getX(), getY());
    				break;
    			}    			
    		}
    	}       
    }

    /**
     * drop the bomb, and change the state of bomb
     */    
    public void dropBomb() {
    	bomb = false;    
    	String s = "0";
    	Bomb.set(s);
    }
}
