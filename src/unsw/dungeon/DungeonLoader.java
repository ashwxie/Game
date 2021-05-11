package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Entities.Bomb;
import Entities.Boulder;
import Entities.Door;
import Entities.Enemy;
import Entities.Exit;
import Entities.Key;
import Entities.Player;
import Entities.Potion;
import Entities.Switch;
import Entities.Sword;
import Entities.Treasure;
import Entities.Wall;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }        
    	Goals goals = new Goals(dungeon);
        loadGoal(dungeon, json.getJSONObject("goal-condition"), goals, " ");
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y, "player");
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, "wall");
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y, "exit");
        	onLoad(exit);
        	entity = exit;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y, "boulder");
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "door":
        	Door door = new Door(x, y, "door");
        	int i = door.getCount();
        	door.setID(i);
        	door.addCount();
        	onLoad(door);
        	entity = door;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x, y, "bomb");
        	onLoad(bomb);
        	entity = bomb;
        	break;
        case "key":
        	Key key = new Key(x, y, "key");
        	int k = key.getCount();
        	key.setID(k);
        	key.addCount();
        	onLoad(key);
        	entity = key;
        	break;
        case "invincibility":
        	Potion potion = new Potion(x,y, "potion");
        	onLoad(potion);
        	entity = potion;
        	break;
        case "switch":
        	Switch s = new Switch(x,y, "switch");
        	onLoad(s);
        	entity = s;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y, "sword");
        	onLoad(sword);
        	entity = sword;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x,y, "treasure");
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x, y, "enemy");
        	onLoad(enemy);
        	entity = enemy;
        }
        dungeon.addEntity(entity);
    }

    /**
     * load goals from JSON file
     * recursive function will load the goal into the list in the goals.java
     * @param d
     * @param o
     */    
    public void loadGoal(Dungeon d, JSONObject o, Goals goals, String type) {
    	String s = o.getString("goal");
    	switch (s) {
        	case "exit":
        		Goal g = new Goal("exit", type);
        		goals.addGoal(g);
        		break;
        	case "boulders":
        		Goal g1 = new Goal("boulder", type);
        		goals.addGoal(g1);
        		break;
        	case "enemies":
        		Goal g2 = new Goal("enemies", type);
        		goals.addGoal(g2);
        		break;
        	case "treasure":
        		Goal g3 = new Goal("treasure", type);
        		goals.addGoal(g3);
        		break;        	
        	case "AND":
                JSONArray jsonSub = o.getJSONArray("subgoals");
                for (int i = 0; i < jsonSub.length(); i++) {
                	JSONObject x = jsonSub.getJSONObject(i);
                	if(x.getString("goal").equals("AND")) {
                		loadGoal(d, x, goals, "AND");
                		break;
                	}else if(x.getString("goal").equals("OR")){
                		loadGoal(d, x, goals, "OR");
                		break;
                	}
            		loadGoal(d, x, goals, "AND");
                }
                break;
        	case "OR":
                JSONArray jsonSubOR = o.getJSONArray("subgoals");
                for (int i = 0; i < jsonSubOR.length(); i++) {
                	JSONObject x = jsonSubOR.getJSONObject(i);
                	if(x.getString("goal").equals("AND")) {
                		loadGoal(d, x, goals, "AND");
                		break;
                	}else if(x.getString("goal").equals("OR")){
                		loadGoal(d, x, goals, "OR");
                		break;
                	}
            		loadGoal(d, x, goals, "OR");
                }
                break;
        }
    	goals.print();   	    	
    	d.loadGoal(goals);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Bomb bomb);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(Switch s);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Enemy enemy);
    
}
