package unsw.dungeon;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
public class easyWorks implements level{
	
	/**
	 * This part is for changing restart button
	 * When client click the restart, the game will be restarted
	 * every entity will be reset
	 * music will be reset
	 */
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

		        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

		        DungeonController controller = dungeonLoader.loadController();

		        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		        loader.setController(controller);

		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        System.out.println("Doing");
		        Button restart = controller.restartButton();	
		        
		        buttonWorks(restart, primaryStage, controller.getMP());
		        
		        Button easy = controller.easyButton();
		        buttonWorks(easy, primaryStage, controller.getMP());
		        Button medium = controller.mediumButton();
		        LevelContext context = new LevelContext(new mediumWorks());
		        context.executeStrategy(medium, primaryStage, controller.getMP());
		        
		        Button master = controller.masterButton();
		        context = new LevelContext(new masterWorks());
		        context.executeStrategy(master, primaryStage, controller.getMP());
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
