package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import Entities.Bomb;
import Entities.Boulder;
import Entities.Door;
import Entities.Enemy;
import Entities.Key;
import Entities.Player;
import Entities.Switch;
import Entities.Wall;

import java.util.Iterator;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */

public class Dungeon {
	
    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Goals goals;
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = null;        
    }
    
    public Goals goals() {
    	return goals;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Entity> getEntities(){
    	return entities;
    }

    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void loadGoal(Goals g) {
    	this.goals = g;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    /**
     * 
     * @param x -- player's location in x direction
     * @param y -- player's location in y direction
     * @param direction -- player's is goint to move 
     * @return boolean
     * 
     * This function is check whether has a wall or a closed door block the way
     * If yes, player cannot go throught there
     * If no, Player can go through the way or an opend door
     */
    
    
    public boolean moveable(int x, int y, String direction) {
    	for(Entity entity : entities) {
    		if(entity.getName().equals("wall")) {
    			if(!wallcheck(x, y, entity, direction))	return false;
    		}else if(entity.getName().equals("door")) {
        		if(!doorCheck(x, y, entity, direction)) return false;
    		}
    	}
    	return true;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param entity -- wall
     * @param direction -- player is goint to move
     * @return
     * Check whether has a wall block a way
     */
    
    
    public boolean wallcheck(int x, int y, Entity entity, String direction) {
    	if(entity.getName().equals("wall")) {
    		Wall e = (Wall) entity;
    		return e.check(x,y,direction, entity);
    	}
    	return true;
    }

    /**
     * 
     * @param x
     * @param y
     * @param entity -- door
     * @param direction -- play is going to move
     * @return
     * 
     * Check wether has a door block the way
     * 
     */
    
    public boolean doorCheck(int x, int y, Entity entity, String direction) {
    	if(entity.getName().equals("door")) {
    		boolean hasKey = false;
    		Door d = (Door) entity;

    		for(Key k: player.getKeys()) {
    			if(k.getID() == d.getID()) {
    				if(d.checkTrue(x, y, d.getX(), d.getY(), direction)) {
        				d.setOpen();
        				hasKey = true;
        				break;	
    	    		}
    			}    			
    		 
    		}
    		if(!hasKey) {
    			return d.check(x, y, direction, entity);
    		}
    	}	
    	return true;    	
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param direction -- player is going to move
     * @return boolean
     * 
     * check whether has a boulder block a way
     * 
     */
    
    public boolean boulderBlock(int x, int y, String direction) {
    	for(Entity e: entities) {
    		if(e.getName().equals("boulder")) {
    			if(direction.equals("up")) {
    				if(e.getX() == x && e.getY()+1 == y ) return true;
    			}else if(direction.equals("down")) {
    				if(e.getX() == x && e.getY() - 1 == y ) return true;
    			}else if(direction.equals("left")) {
    				if(e.getX() + 1 == x && e.getY() == y) return true;
    			}else if(direction.equals("right")) {
    				if(e.getX() - 1 == x && e.getY() == y) return true;
    			}
    		}
    	}
    	return false;
    }
    
    
    /**
     * @check whether the boulder is pushable
     * @check the bounder around the player
     * 
     */
    public boolean ispushable(int x, int y, String direction) {
		boolean ret = false;
    	for(Entity entity : entities) {
    		/// check it is boulder around the player
    		if(entity.getName().equals("boulder")) {
    			if(entity.getX() + 1 < this.getWidth() && direction.equals("right")) {
    				if(x+1  == entity.getX() && y == entity.getY()) {
    					ret = adjacent(entity.getX()+1, entity.getY(), direction);
    					return ret;
    				}
    			}else if(entity.getX() - 1 > 0 && direction.equals("left")) {
    				if(x-1 == entity.getX() && y == entity.getY()) {
    					ret = adjacent(entity.getX() - 1, entity.getY(), direction);
       					return ret;
    				}
    			}else if(entity.getY() - 1 > 0 && direction.equals("up")) {
    				if(x == entity.getX() && y-1 == entity.getY()) {
    					ret = adjacent(entity.getX(),entity.getY() -1, direction);
       					return ret;
    				}			
    			}else if(entity.getY() + 1 < this.getHeight() && direction.equals("down")) {
    				if(x == entity.getX() && y+1 == entity.getY()) {
    					ret = adjacent(entity.getX(),entity.getY() + 1, direction);
       					return ret;
    				}
    			}
    		}
    	}
    	return ret;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param direction the direction of boulder going to move
     * @return boolean
     * 
     * check whether has boulder blocked boulder
     * or wall blocked the boulder
     * or closed door blocked the boulder
     */
    public boolean adjacent(int x, int y, String direction) {
    	boolean ret = true;
    	for(Entity e: entities) {
    		boolean open = false;
    		if(e.getName().equals("door")) {
    			Door d = (Door) e;
    			open = d.getOpen();
    		}
    		
    		if(e.getName().equals("wall") || e.getName().equals("boulder") || e.getName().equals("exit")
    				|| (e.getName().equals("door") && !open)) {
				if(e.getX() == x && e.getY() == y) {
					ret = false;
					break;
				}
				ret = true;
    		}
    	}
    	
    	return ret;
    }

    
    /**
     * 
     * @param x
     * @param y
     * @param direction boulder move direction
     * 
     * This function is only make boulder move;
     * change boulder's location
     */
    
    public void move(int x, int y, String direction) {
    	for(Entity entity : entities) {
    		if(entity.getName().equals("boulder")) {
    			Boulder b = (Boulder) entity;
    			b.boulderMove(x, y, entity.getX(), entity.getY(), direction);
    		}
    		
    	}
    }
    

    /**
     * 
     * @param x
     * @param y
     * @param direction
     * 
     * This function will tell enemy the location of the player
     * Meanwhile, it will do some operation to choose the best way to follow up/run away
     * 
     */
    public void tellEnemy(int x, int y, String direction, int potionCount) {
    	for(Entity e: entities) {
    		if(e.getName().equals("enemy")) {    			
    			Enemy enemyNo = (Enemy) e;
    			String move_direction = "mid";
    			if(potionCount > 0) {
    				move_direction = enemyNo.haspotionmoving(x, y, direction);
    			}else {
    				move_direction = enemyNo.enemyMove(x, y);
    			}
    			enemyNo.enemyMoveing(e.getX(), e.getY(), move_direction);
    		}
    	}
    }

    
    /**
     * 
     * @param x
     * @param y
     * @return boolean
     * 
     * check wether the player achieved the goals
     * 
     */
    public boolean succeed(int x, int y) {
    	if(goals.succeed(x, y)) return true;
    	
    	return false;
   }
   
   /**
    *  
    * @param x
    * @param y
    * 
    * check whether the switch is overlapped by boulder
    * if yes, change switch state
    */
    
   public void twoEntityOverlap(int x, int y) {
	   for(Entity e: entities) {
		   if(e.getX() == x && e.getY() == y && !e.getName().equals("boulder") && !e.getName().equals("switch")) {
			   removeStuff(e.getX(), e.getY());
			   return; 
		   }
		   if(e.getX() == x && e.getY() == y && e.getName().equals("switch")) {
			   //changeSwitchState(e.getX(),e.getY());
			   Switch s =(Switch) e;
			   s.setState();
			   return;
		   }
		   if(player.getX() == e.getX() && player.getY() == e.getY() && e.getName().equals("switch")) {
			   Switch s =(Switch) e;
			   s.reSetState();;
			   return;
		   }
	   }
   }
   
   /**
    * 
    * @param x
    * @param y
    * 
    * remove entity from the board
    */
   public void removeStuff(int x, int y) {
	   Iterator<Entity> i = entities.iterator();
	   while(i.hasNext()) {
		   Entity e = i.next();
		   if(e.getX() == x && e.getY() == y ) {
			   if(e.pickable() || e.getName().equals("enemy")) {
				   e.changeXandY(0, height+1);
				   i.remove();	
			   }
		   }
	   }
   }
   

   /**
    * Player lose the game
    * @return string
    */
   public boolean fail(int x, int y) {
	   for(Entity e: entities) {
		   if(e.getName().equals("enemy")) {
			   if(e.getX()==x && e.getY()==y)	return true;
		   }
	   }
	   
	   boolean flag = false;
	   for(Entity e: entities) {
		   if(e.getName().equals("player")) {
			   flag = true;			   
			   break;
		   }
	   }
	   if(!flag) return true;
	   return false;
   }
   
   /**
    * 
    * @param x
    * @param y
    * drop bomb on map
    */
   public void dropBomb(int x, int y) {
	   Bomb b = new Bomb(x, y, "bomb");
	   b.lit();
	   entities.add(b);
   }
   
   /**
    * 
    * @param x
    * @param y
    * 
    * let bomb explode
    * its goint kill the enemy boulder and player who around the bomb
    */
   public void explode(int x, int y) {
	   Iterator<Entity> i = entities.iterator();
	   while(i.hasNext()) {
		   Entity e = i.next();
		   if((e.getX() == x && e.getY( )== y) || (e.getX() == x+1 && e.getY( )== y)
		   			|| (e.getX() == x-1 && e.getY( )== y) || (e.getX() == x && e.getY( )== y+1)
		   			|| (e.getX() == x && e.getY( )== y-1)) {
			   if(e.getName().equals("enemy") || e.getName().equals("boulder") 
					   || e.getName().equals("player") || e.getName().equals("bomb")) {
				   e.changeXandY(0, height+1);
				   i.remove();	
			   }
		   }
	   }
   }
   
   public boolean exist(int x, int y) {
	   for(Entity e: entities) {
		   if(e.getX()==x && e.getY()==y) {
			   if(e.pickable())	return false;
			   else if(e.getName().equals("enemy"))	return false;
			   else if(e.getName().equals("player"))	return false;
			   else return true;
		   }
	   }
	   return false;
   }
   
}
