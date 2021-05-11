package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String name;
    private boolean pickable;
    
    /**
     * Create an entity positioned in square (x,y) and set if it's pickable
     * @param x
     * @param y
     * @String name
     * @boolean pickable
     */
    public Entity(int x, int y, String name) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.name = name;
        this.pickable = false;
    }

	public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
     
    public String getName() {
		return name;
	}   
    
    public boolean pickable() {
    	return pickable;
    }

    public void setName(String name) {
		this.name = name;
	}
    
    public void setPickable() {
    	pickable = true;
    }
	
    public void changeXandY(int x, int y) {
    	x().set(x);
    	y().set(y);
    }

    
    /**
     * check whether the location of player will overlap with entity
     * if yes return ture
     * else return false
     * @param playerx
     * @param playery
     * @param direction
     * @param entity
     * @return
     */
    public boolean check(int playerx, int playery, String direction, Entity entity) {
    	return false;
    }

    
    /**
     * check whether the location of player is overlaped with pointed location(x,y)
     * @param playerx
     * @param playery
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public boolean checkTrue(int playerx, int playery, int x, int y, String direction) {
    	
    	return false;
    }
    
    
}
