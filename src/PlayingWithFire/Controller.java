package PlayingWithFire;

import java.util.Stack;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;

public class Controller {
	@FXML private Text p1healthText,p1bombCountText,p2healthText,p2bombCountText,p3healthText,p3bombCountText,p4healthText,p4bombCountText,timerText;
	@FXML private ImageView menuImageView,stageImageView,p1ImageView,p2ImageView,p3ImageView,p4ImageView;
	@FXML private Button onePlayerButton,twoPlayerButton,zeroBotButton,oneBotButton,twoBotButton,threeBotButton,stageRightButton,stageLeftButton,playButton,helpButton,exitButton,advancedSettingsButton,defaultStagesButton,customStagesButton,loadButton,openStageBuilderButton;
	@FXML private TextField loadTextField;
	@FXML private Text loadText;
	@FXML private AnchorPane windowPane,menuPane,defaultStagesPane,customStagesPane,playerPane,bombPane,interfacePane,p1InterfacePane,p2InterfacePane,p3InterfacePane,p4InterfacePane;
	@FXML private GridPane mapGrid,bangGrid;
	Stack<ImageView> bombStack = new Stack<>();
	
	private PlayingWithFire game = new PlayingWithFire();
	
	//Initializes controller:
	@FXML
	void initialize() { //Fills the already excisting GridPane with ImageViews, and sets this class to be the controller of the PlayingWithFire-class
		for(int i = 0;i<Variables.BOARD_WIDTH;i++) {
			for(int j = 0;j<Variables.BOARD_HEIGHT;j++) {
				newImageView(mapGrid,i,j);
				newImageView(bangGrid,i,j);
			}
		}
		for(int i = 0; i<Variables.MAXIMUM_BOMBS; i++) {
			newImageView(bombPane);
		}
		setImageViewSizes();
		game.setController(this);
		game.start();
	}
	private void setImageViewSizes() {
		p1ImageView.setFitWidth(Variables.TILE_SIZE);
		p1ImageView.setFitHeight(Variables.TILE_SIZE);
		p2ImageView.setFitWidth(Variables.TILE_SIZE);
		p2ImageView.setFitHeight(Variables.TILE_SIZE);
		p3ImageView.setFitWidth(Variables.TILE_SIZE);
		p3ImageView.setFitHeight(Variables.TILE_SIZE);
		p4ImageView.setFitWidth(Variables.TILE_SIZE);
		p4ImageView.setFitHeight(Variables.TILE_SIZE);
		bombPane.setPrefWidth(Variables.TILE_SIZE*Variables.BOARD_WIDTH);
		bombPane.setPrefHeight(Variables.TILE_SIZE*Variables.BOARD_HEIGHT);
	}
	private void newImageView(GridPane e,int i, int j) {
		ImageView iv = new ImageView();
		iv.setFitWidth(Variables.TILE_SIZE); //Sets size of the imageview
        iv.setFitHeight(Variables.TILE_SIZE); //Sets size of the imageview
        iv.setSmooth(true); //Preformance and looks
        iv.setCache(true);  //Preformance and looks
        e.add(iv,i,j);
	}
	private void newImageView(AnchorPane e) {
		ImageView iv = new ImageView();
		iv.setFitWidth(Variables.TILE_SIZE); //Sets size of the imageview
        iv.setFitHeight(Variables.TILE_SIZE); //Sets size of the imageview
        iv.setSmooth(true); //Preformance and looks
        iv.setCache(true);  //Preformance and looks
        iv.setImage(game.img.blank);
        e.getChildren().add(iv);
        bombStack.push(iv);
	}

	//Changes element:
	public void setText(Text element, String content) { //Sets text of a textfield
		element.setText(content);
	}
	public void setMapTile(int x, int y, Image img) { //Changes image of a map-tile
		((ImageView) mapGrid.getChildren().get(x*Variables.BOARD_HEIGHT+y)).setImage(img);
	}
	public void setBangTile(int x, int y, Image img) { //Changes image of a bang-tile
		((ImageView) bangGrid.getChildren().get(x*Variables.BOARD_HEIGHT+y)).setImage(img);
	}
	public void setImage(ImageView iv, Image image) { //Sets the image of an ImageView
		iv.setImage(image);
	}
	public void disable(Node element, boolean state) { //Disables element
		element.setDisable(state);
	}
	public void setVisibility(Node element, boolean state) { //Sets visibility of a node
		element.setVisible(state);
	}
	public void addClass(Node element, String classname) {
		element.getStyleClass().add(classname);
	}
	public void removeClass(Node element, String classname) {
		element.getStyleClass().remove(classname);
	}
	public void moveTo(Node element, double x, double y) { //Moves element to target location
		element.setLayoutX(x);
		element.setLayoutY(y);
	}
	
	//KeyHandlers:
    public void keyDown(KeyEvent event){ //KeyEvent when key is pressed
    	game.keyDown(event.getCode());
    }
    public void keyUp(KeyEvent event){ //KeyEvent when key is pressed
    	game.keyUp(event.getCode());
    }

	
	//Buttons:
	@FXML public void onePlayer() {
		game.getMenu().buttonPressed("1Player");
	}
	@FXML public void twoPlayer() {
		game.getMenu().buttonPressed("2Player");
	}
	@FXML public void zeroBot() {
		game.getMenu().buttonPressed("0Bots");
	}
	@FXML public void oneBot() {
		game.getMenu().buttonPressed("1Bot");
	}
	@FXML public void twoBot() {
		game.getMenu().buttonPressed("2Bots");
	}
	@FXML public void threeBot() {
		game.getMenu().buttonPressed("3Bots");
	}
	@FXML public void stageLeft() {
		game.getMenu().buttonPressed("PreviousStage");
	}
	@FXML public void stageRight() {
		game.getMenu().buttonPressed("NextStage");
	}
	@FXML public void play() {
		game.getMenu().buttonPressed("Play");
	}
	@FXML public void settings() {
		game.getMenu().buttonPressed("Settings");
	}
	@FXML public void exit() {
		game.getMenu().buttonPressed("Exit");
	}
	@FXML public void advancedSettings() {
		game.getMenu().buttonPressed("AdvancedSettings");
	}	
	@FXML public void defaultStages() {
		game.getMenu().buttonPressed("DefaultStages");
	}
	@FXML public void customStages() {
		game.getMenu().buttonPressed("CustomStages");
	}
	@FXML public void load() {
		game.getMenu().buttonPressed("Load");
	}
	@FXML public void openStageBuilder() {
		game.getMenu().buttonPressed("OpenStageBuilder");
	}
	
	//Getters:
	public Text getP1healthText() {
		return p1healthText;
	}
	public Text getP1bombCountText() {
		return p1bombCountText;
	}
	public Text getP2healthText() {
		return p2healthText;
	}
	public Text getP2bombCountText() {
		return p2bombCountText;
	}
	public Text getP3healthText() {
		return p3healthText;
	}
	public Text getP3bombCountText() {
		return p3bombCountText;
	}
	public Text getP4healthText() {
		return p4healthText;
	}
	public Text getP4bombCountText() {
		return p4bombCountText;
	}
	public Text getTimerText() {
		return timerText;
	}
	public ImageView getMenuImageView() {
		return menuImageView;
	}
	public ImageView getStageImageView() {
		return stageImageView;
	}
	public ImageView getP1ImageView() {
		return p1ImageView;
	}
	public ImageView getP2ImageView() {
		return p2ImageView;
	}
	public ImageView getP3ImageView() {
		return p3ImageView;
	}
	public ImageView getP4ImageView() {
		return p4ImageView;
	}
	public Button getOnePlayerButton() {
		return onePlayerButton;
	}
	public Button getTwoPlayerButton() {
		return twoPlayerButton;
	}
	public Button getZeroBotButton() {
		return zeroBotButton;
	}
	public Button getOneBotButton() {
		return oneBotButton;
	}
	public Button getTwoBotButton() {
		return twoBotButton;
	}
	public Button getThreeBotButton() {
		return threeBotButton;
	}
	public Button getStageRightButton() {
		return stageRightButton;
	}
	public Button getStageLeftButton() {
		return stageLeftButton;
	}
	public Button getPlayButton() {
		return playButton;
	}
	public Button getHelpButton() {
		return helpButton;
	}
	public Button getExitButton() {
		return exitButton;
	}
	public Button getDefaultStagesButton() {
		return defaultStagesButton;
	}
	public Button getCustomStagesButton() {
		return customStagesButton;
	}
	public TextField getLoadTextField() {
		return loadTextField; 
	}
	public Text getLoadText() {
		return loadText;
	}
	public AnchorPane getWindowPane() {
		return windowPane;
	}
	public AnchorPane getMenuPane() {
		return menuPane;
	}
	public AnchorPane getDefaultStagesPane() {
		return defaultStagesPane;
	}
	public AnchorPane getCustomStagesPane() {
		return customStagesPane;
	}
	public AnchorPane getPlayerPane() {
		return playerPane;
	}
	public AnchorPane getInterfacePane() {
		return interfacePane;
	}
	public AnchorPane getBombPane() {
		return bombPane;
	}
	public AnchorPane getP1InterfacePane() {
		return p1InterfacePane;
	}
	public AnchorPane getP2InterfacePane() {
		return p2InterfacePane;
	}
	public AnchorPane getP3InterfacePane() {
		return p3InterfacePane;
	}
	public AnchorPane getP4InterfacePane() {
		return p4InterfacePane;
	}
	public GridPane getMapGrid() {
		return mapGrid;
	}	
	public GridPane getBangGrid() {
		return bangGrid;
	}



}
