package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Goals {
	private Dungeon dungeon;
	private ArrayList<Goal> goals;
	private StringProperty goalProperty;
	
	public Goals(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.goals = new ArrayList<>();
		this.goalProperty = new SimpleStringProperty();
	}
	
	public ArrayList<Goal> getGoals(){
		return goals;
	}
	
	/**
	 * add the goal into the goal list
	 * @param g
	 */
	public void addGoal(Goal g) {
		goals.add(g);		
		String s1 = g.getGoal() + " " + g.getCheck();	
		String s2 = goalProperty.get();

		if(s2==null)	goalProperty.set(s1);		 
		else			goalProperty.set(s2 + " " + s1);
	}
	
	public StringProperty goalProperty() {
		return goalProperty;
	}
	
	public void removeGoal(Goal g, String s) {
		goals.remove(g);
	}
	/**
	 * check whether it statisfied the goal,
	 * if not return false and keep playing
	 * if yes return true and gameover
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean succeed(int x, int y) {
		int andCounter = AND_counter();		
		int orCounter = OR_counter();
		int counter = 0;
		
		for(Goal g : goals) {
			if(g.getCheck().equals("OR") && orCounter > 0) {
				if(AND_OR(g,x,y)) {
					orCounter = -1;
				}
			}else if(g.getCheck().equals("AND") || g.getCheck().equals(" ")){
				if(AND_OR(g,x,y)) counter++;
			}
		}
		if(orCounter <= 0 && counter == andCounter) {
			return true;
		}
		return false;
	}
	/**
	 * check how many or in list in each level
	 * @return
	 */
	public int OR_counter() {
		int counter = 0;
		for(Goal g : goals) {
			if(g.getCheck().equals("OR")) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * check how many and in the goal list in each level.
	 * @return
	 */
	public int AND_counter() {
		int counter = 0;
		for(Goal g : goals) {
			if(g.getCheck().equals("AND") || g.getCheck().equals(" ")) {
				counter++;
			}
		}
		
		return counter;
	}
	
	/**
	 * Apply the strategy pattern
	 * check wether it is achieved the goals
	 * @param g
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean AND_OR(Goal g, int x, int y) {
		boolean flag = false;
		if(g.getGoal().equals("exit")) {
		    Context context = new Context(new exitWin());
		    flag = context.executeStrategy("exit", dungeon.getEntities(), x, y);
		}else if(g.getGoal().equals("treasure")) {
		    Context context = new Context(new treasureWin());
		    flag = context.executeStrategy("treasure", dungeon.getEntities(), x, y);
		}else if(g.getGoal().equals("enemies")) {
			Context context = new Context(new killWin());
		    flag = context.executeStrategy("enemy", dungeon.getEntities(), x, y);
		}else if(g.getGoal().equals("boulder")) {
			Context context = new Context(new switchWin());
		    flag = context.executeStrategy("switch", dungeon.getEntities(), x, y);
		}
		return flag;
	}
	
	/**
	 * print the goals if you need
	 */
	public void print() {
		int i = 0;
		for(Goal g: goals)	i++;		
	}
	
}
