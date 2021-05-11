package unsw.dungeon;


import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/**
 * This part will call the strategies
 *
 */

public class LevelContext {
	
	public level l;
	
	public LevelContext(level level) {
		this.l = level;
	}
	public void executeStrategy(Button b, Stage s, MediaPlayer m) {
		this.l.buttonWorks(b, s, m);
	}
}
