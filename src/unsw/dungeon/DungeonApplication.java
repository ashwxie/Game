package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Application will load the graph into the board
 * Set the button to change the level
 */
public class DungeonApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {    	
        primaryStage.setTitle("Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("initial.json");

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        Button restart = controller.restartButton();
        LevelContext context = new LevelContext(new restartWorks());
        context.executeStrategy(restart, primaryStage, controller.getMP());
        
        Button easy = controller.easyButton();
        context = new LevelContext(new easyWorks());
        context.executeStrategy(easy, primaryStage, controller.getMP());

        Button medium = controller.mediumButton();
        context = new LevelContext(new mediumWorks());
        context.executeStrategy(medium, primaryStage, controller.getMP());
        
        Button master = controller.masterButton();
        context = new LevelContext(new masterWorks());
        context.executeStrategy(master, primaryStage, controller.getMP());
        
        root.requestFocus();     
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
