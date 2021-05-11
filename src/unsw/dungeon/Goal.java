package unsw.dungeon;

public class Goal {

	private String goal;
	private String check;
	public Goal(String i, String check) {
		this.goal = i;
		this.check = check;
	}
	/**
	 * getters & setters of goal
	 * @return
	 */

	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	

	
}
