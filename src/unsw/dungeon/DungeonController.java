package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Entities.Door;
import Entities.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {
	@FXML
	private MediaView mv;
	MediaPlayer mp;
	Media m;

	public MediaPlayer getMP() {
		return mp;
	}
	
	
    @FXML
    private GridPane squares;
    
    @FXML
    private Button restartButton;
    
    @FXML
    private Button easyButton;
    
    @FXML
    private Button mediumButton;
    
    @FXML
    private Button masterButton;
    
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private Timeline t;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        t = new Timeline();
    }

    @FXML
	public void handleRestart(ActionEvent event) {
    	
	}
    
    public Button restartButton() {
    	return restartButton;
    }
    
    public Button easyButton() {
    	return easyButton;
    }
    
    public Button mediumButton() {
    	return mediumButton;
    }
    
    public Button masterButton() {
    	return masterButton;
    }
    @FXML
    public void handleEasy(ActionEvent event) {
    }
    
    @FXML
    public void handleMedium(ActionEvent event) {
    	
    }

    @FXML
    public void handleMaster(ActionEvent event) {
    	
    }
    
    /**
     * intilize the music and backpack
     * this part will count the pickable entities
     * It will show how many entities in the backpack
     */
    @FXML
    public void initialize() {
    	String path = new File("src/unsw/dungeon/music.mp3").getAbsolutePath();
    	m = new Media(new File(path).toURI().toString());
    	mp = new MediaPlayer(m);
    	mv = new MediaView(mp);
    	mp.setCycleCount(100);
    	mp.play();
    	
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        int y = 0;
        TextField t0 = new TextField("Goal");  
        t0.setPrefWidth(45);  
        TextField tt = new TextField("");  
        tt.setPrefWidth(250);  
        tt.textProperty().bindBidirectional(dungeon.goals().goalProperty());
        
        squares.add(t0, dungeon.getWidth()+1, y);
        squares.add(tt, dungeon.getWidth()+2, y);

        
        y++;
        TextField t1 = new TextField("0");
        t1.setPrefWidth(10);        
        t1.textProperty().bindBidirectional(player.getTreasureProperty());
        squares.add(new ImageView("/gold_pile.png"), dungeon.getWidth()+1, y);        
        squares.add(t1, dungeon.getWidth()+2, y);
        
        y++;
        TextField t2 = new TextField("0");
        t2.setPrefWidth(10);
        t2.textProperty().bindBidirectional(player.getPotionProperty());
        squares.add(new ImageView("brilliant_blue_new.png"), dungeon.getWidth()+1, y);
        squares.add(t2, dungeon.getWidth()+2, y);

        y++;
        TextField t3 = new TextField("0");
        t3.setPrefWidth(10);
        t3.textProperty().bindBidirectional(player.getSwordProperty());
        squares.add(new ImageView("greatsword_1_new.png"), dungeon.getWidth()+1, y);
        squares.add(t3, dungeon.getWidth()+2, y);
        
        y++;
        TextField t4 = new TextField("0");
        t4.setPrefWidth(10);
        t4.textProperty().bindBidirectional(player.getBombProperty());
        squares.add(new ImageView("bomb_unlit.png"), dungeon.getWidth()+1, y);
        squares.add(t4, dungeon.getWidth()+2, y);
       
        y++;
        TextField t5 = new TextField("0");
        t5.setPrefWidth(10);
        t5.textProperty().bindBidirectional(player.getKeyProperty());
        squares.add(new ImageView("key.png"), dungeon.getWidth()+1, y);
        squares.add(t5, dungeon.getWidth()+2, y);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            open(player.getX(), player.getY());
            break;
        case DOWN:
            player.moveDown();
            open(player.getX(), player.getY());
            break;
        case LEFT:
            player.moveLeft();
            open(player.getX(), player.getY());
            break;
        case RIGHT:
            player.moveRight();
            open(player.getX(), player.getY());
            break;
            // drop bomb
        case Z:
        	if(player.getBomb()) {
        		player.dropBomb();
        		dungeon.dropBomb(player.getX(), player.getY());
        		int x = player.getX(), y = player.getY();

        		Image bombImage = new Image("bomb_lit_1.png");
        		ImageView bomb1 = new ImageView(bombImage);        		
        		squares.add(bomb1, x, y);
        		
				bombImage = new Image("bomb_lit_2.png");     
        		ImageView bomb2 = new ImageView(bombImage);  
        		
				bombImage = new Image("bomb_lit_3.png");     
        		ImageView bomb3 = new ImageView(bombImage); 
        		
				bombImage = new Image("bomb_lit_4.png");     
        		ImageView bomb4 = new ImageView(bombImage);  

        		t.setCycleCount(1);
        		
            	EventHandler<ActionEvent> on1 = on(bomb1, bomb2, x, y);
            	EventHandler<ActionEvent> on2 = on(bomb2, bomb3, x, y);
            	EventHandler<ActionEvent> on3 = on(bomb3, bomb4, x, y);
            	EventHandler<ActionEvent> on4 = new EventHandler<ActionEvent>(){
        			public void handle(ActionEvent event) {
        				bomb4.setImage(null);
        				squares.getChildren().remove(bomb4);    
        				dungeon.explode(x, y);
        			}        		
        		};
        		KeyFrame k1 = new KeyFrame(Duration.seconds(1), on1);
        		KeyFrame k2 = new KeyFrame(Duration.seconds(2), on2);
        		KeyFrame k3 = new KeyFrame(Duration.seconds(3), on3);
        		KeyFrame k4 = new KeyFrame(Duration.seconds(4), on4);
        		t.getKeyFrames().addAll(k1, k2, k3, k4);
        		t.play();             		
        	}
        	break;
        default:
            break;
        }
    }

    public EventHandler<ActionEvent> on(ImageView b1, ImageView b2, int x, int y){
    	EventHandler<ActionEvent> i = new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			squares.getChildren().remove(b1);
    			squares.add(b2, x, y);
    		}
    	};
    	return i;
    }

    /**
     * 
     * change the graph when door is opened
     * when the play win the game, "You win" will show up
     * when the play win the game, "You're dead" will show up
     * @param x
     * @param y
     */
    public void open(int x, int y) {
    	boolean open = false;   	
    	for(Entity e: dungeon.getEntities()) {
    		if(e.getName().equals("door") && e.getX()==x && e.getY()==y ) {
    			Door d = (Door) e;
    			open = d.getOpen();
    			break;
    		}    		
    	}
    	
    	if(open) {
    		Image i = new Image("/dirt_0_new.png");
    		ImageView iv = new ImageView(i);  
    		squares.add(iv, x, y);

    		i = new Image("open_door.png");
    		iv = new ImageView(i); 
    		squares.add(iv, x, y);
    	}
    	
    	if(dungeon.succeed(x, y)) {
    		Stage dialog = new Stage();
    		VBox dialogVbox = new VBox(1);
            dialogVbox.getChildren().add(new Text("YOU WIN"));
            Scene dialogScene = new Scene(dialogVbox, 100, 20);
            dialog.setScene(dialogScene);
            dialog.show();
    	}
    	
    	if(dungeon.fail(x, y)) {
    		Stage dialog = new Stage();
    		VBox dialogVbox = new VBox(1);
            dialogVbox.getChildren().add(new Text("YOU'RE DEAD"));
            Scene dialogScene = new Scene(dialogVbox, 100, 20);
            dialog.setScene(dialogScene);
            dialog.show();
    	}
    }
    
}

