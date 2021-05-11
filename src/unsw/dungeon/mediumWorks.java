package unsw.dungeon;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/**
 * This is part of button strategy pattern
 * Player will call strategy to make button works
 * this is medium button
 * when player click the medium button, the map will be changed
 * music will be reset
 * @author z5086369
 *
 */
public class mediumWorks implements level{

	@Override
	public void buttonWorks(Button button, Stage primaryStage, MediaPlayer mp) {
        button.setOnAction(__->
        {
          System.out.println( "New Game!" );
          mp.stop();
          primaryStage.close();
          Platform.runLater( () -> {
			try {
		        primaryStage.setTitle("Dungeon");

		        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");

		        DungeonController controller = dungeonLoader.loadController();

		        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		        loader.setController(controller);

		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        System.out.println("Doing");
		        Button restart = controller.restartButton();	
		        
		        buttonWorks(restart, primaryStage, controller.getMP());
		        Button easy = controller.easyButton();
		        LevelContext context = new LevelContext(new easyWorks());
		        context.executeStrategy(easy, primaryStage, controller.getMP());

		        
		        Button master = controller.masterButton();
		        context = new LevelContext(new masterWorks());
		        context.executeStrategy(master, primaryStage, controller.getMP());
		        
		        
		        Button medium = controller.mediumButton();
		        buttonWorks(medium, primaryStage, controller.getMP());
		        root.requestFocus();     
		        primaryStage.setScene(scene);
		        primaryStage.show();
			} catch (IOException e) {				
				// TODO Auto-generated catch block
				System.out.println("System error");
			}
		} );
        } );
	}
}