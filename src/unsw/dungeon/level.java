package unsw.dungeon;

import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/**
 * interface of buttons startegy pattern
 * @author z5086369
 *
 */
public interface level {
	public void buttonWorks(Button b, Stage s, MediaPlayer mp);
}
