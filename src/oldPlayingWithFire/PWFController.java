
package oldPlayingWithFire;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PWFController {
	@FXML private AnchorPane window;
	@FXML private GridPane map;
	@FXML private AnchorPane players;
	@FXML private ImageView menu;

	@FXML private ImageView p1img;
	@FXML private ImageView p2img;
	@FXML private ImageView p3img;
	@FXML private ImageView p4img;
	
	@FXML private Text timer;
	@FXML private Text score1;
	@FXML private Text score2;
	@FXML private Text score3;
	@FXML private Text score4;
	@FXML private Text winnerBoard;
	@FXML private ImageView gameover;
	
	
	@FXML public void b1() {
		game.buttonPressed('1');
	}
	@FXML public void b2() {
		game.buttonPressed('2');
	}
	@FXML public void b3() {
		game.buttonPressed('3');
	}
	@FXML public void b4() {
		game.buttonPressed('4');
	}
	@FXML public void fw() {
		game.buttonPressed('f');
	}
	@FXML public void bk() {
		game.buttonPressed('b');
	}
	@FXML public void exit() {
		game.buttonPressed('e');
	}
	
	private PlayingWithFire game = new PlayingWithFire();
	
	@FXML
	void initialize() { //Fills the already excisting GridPane with ImageViews, and sets this class to be the controller of the PlayingWithFire-class
		for(int i = 0;i<PlayingWithFire.BOARD_WIDTH;i++) {
			for(int j = 0;j<PlayingWithFire.BOARD_HEIGHT;j++) {
				ImageView iv = new ImageView();
		        iv.setFitWidth(PlayingWithFire.TILE_SIZE); //Sets size of the imageview
		        iv.setPreserveRatio(true); //Sets the image to preserve its original pixel-ratio
		        iv.setSmooth(true); //Preformance and looks
		        iv.setCache(true); //Preformance and looks
				map.add(iv,i,j); //Adds the imageview to the gridpane
			}
		}
		game.setController(this); //Sets this to be the controller
	}
	
    public void keyDown(KeyEvent event){ //KeyEvent when key is pressed
    	switch(event.getCode()) {
		case LEFT:
			game.addKey(0); break;
		case RIGHT:
			game.addKey(1); break;
		case UP:
			game.addKey(2); break;
		case DOWN:
			game.addKey(3); break;
		case COMMA:
			game.addKey(4); break;
		case A:
			game.addKey(5); break;
		case D:
			game.addKey(6); break;
		case W:
			game.addKey(7); break;
		case S:
			game.addKey(8); break;
		case DIGIT1:
			game.addKey(9); break;
		default: break;
		}
    }
    
	public void keyUp(KeyEvent event) { //KeyEvent when button released
		switch(event.getCode()) {
		case LEFT:
			game.removeKey(0); break;
		case RIGHT:
			game.removeKey(1); break;
		case UP:
			game.removeKey(2); break;
		case DOWN:
			game.removeKey(3); break;
		case COMMA:
			game.removeKey(4); break;
		case A:
			game.removeKey(5); break;
		case D:
			game.removeKey(6); break;
		case W:
			game.removeKey(7); break;
		case S:
			game.removeKey(8); break;
		case DIGIT1:
			game.removeKey(9); break;
		default: break;
		}
    }

    public void setText(int board, String text){ //Sets text of lifebars and timer
        switch(board) {
        case 0:
        	timer.setText(text); break;
        case 1:
        	score1.setText(text); break;
        case 2:
        	score2.setText(text); break;
        case 3:
        	score3.setText(text); break;
        case 4:
        	score4.setText(text); break;
        case 5:
        	winnerBoard.setText(text); break;
        }
    }
    
    private ImageView playerToIV(char player) { //checks the playernumber and returns the corresponding ImageView
		ImageView iv;
    	switch(player) {
		case '1':
			iv = p1img;
			break;
		case '2':
			iv = p2img;
			break;
		case '3':
			iv = p3img;
			break;
		case '4':
			iv = p4img;
			break;
		default:
			iv=p1img;
			break;
		}
    	return iv;
    }
	
    public void animate(char player, int moveTo, boolean x, int animationTime) { //Animates a movement
		Timeline timeline = new Timeline();
		if(x) {
			timeline.getKeyFrames().add(new KeyFrame(Duration.millis(animationTime),new KeyValue(playerToIV(player).layoutXProperty(),moveTo)));
		} else {
			timeline.getKeyFrames().add(new KeyFrame(Duration.millis(animationTime),new KeyValue(playerToIV(player).layoutYProperty(),moveTo)));
		}
		timeline.play();
	}
	
	public void setPlayerImageCoords(char player, int x,int y) { //moves the image of players to the specified coords
		playerToIV(player).setLayoutX(x);
		playerToIV(player).setLayoutY(y);
	}
	
	public void setPlayerImage(char player, Image img) { //Changes player image (rotation)
		playerToIV(player).setImage(img);
	}
	
	public void winner() {
		winnerBoard.setVisible(true);
		gameover.setVisible(true);
	}
	public void reset() {
		winnerBoard.setVisible(false);
		gameover.setVisible(false);
	}
	
	public void animateLifeLoss(char player) {
		
	}
	
	public void setPlayerImageVisibility(char player, boolean visible) { //changes weather an image is visible or not
		playerToIV(player).setVisible(visible);
	}
	
	public void setMenuImage(Image img) { //Changes image used in menu
		menu.setImage(img);
	}
	
	public void setMenuShowing(boolean show) { //Changes visibility of menu
		menu.setVisible(show);
	}
	
	public void setTile(int x, int y, Image img) { //Changes image of tile
		((ImageView) map.getChildren().get(x*PlayingWithFire.BOARD_HEIGHT+y)).setImage(img);
	}
	
}
