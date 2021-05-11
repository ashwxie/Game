package unsw.dungeon;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/**
 * This is part of button strategy pattern
 * Player will call strategy to make button works
 * this is restart button
 * when player click the restart button, the map will be changed
 * music will be reset
 * @author z5086369
 *
 */
public class restartWorks implements level{

	@Override
	public void buttonWorks(Button button, Stage primaryStage, MediaPlayer mp) {
        button.setOnAction(__->
        {
          System.out.println( "Restarting!" );
          mp.stop();
          primaryStage.close();
          Platform.runLater( () -> {
			try {
				new DungeonApplication().start( new Stage() );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("System error");
			}
		} );
        } );
	}
}
