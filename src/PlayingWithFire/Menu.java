package PlayingWithFire;

import java.util.List;

public class Menu {
	private PlayingWithFire PWF;
	List<String> defaultStages;
	private int defaultStagesIndex = 0; 
	List<String> customStages;
	private int customStagesIndex; 
	private boolean isCustomStages = false;
	private int players = 1;
	private int bots = 1;

	
	Menu(PlayingWithFire PWF) {
		this.PWF = PWF;
		defaultStages = FileHandler.listFiles("src/PlayingWithFire/defaultStages");
		customStages = FileHandler.listFiles("src/PlayingWithFire/customStages");
	}
	
	public void buttonPressed(String button) { //Calls the appropriate function based on what button is pressed
		switch(button) {
		case "1Player":
			setPlayers(1); break;
		case "2Player":
			setPlayers(2); break;
		case "0Bots":
			setBots(0); break;
		case "1Bot":
			setBots(1); break;
		case "2Bots":
			setBots(2); break;
		case "3Bots":
			setBots(3); break;
		case "Play":
			play(); break;
		case "Settings":
			help(); break;
		case "Exit":
			exit(); break;
		case "AdvancedSettings":
			advancedSettings(); break;
		case "DefaultStages":
			setStages(false); break;
		case "CustomStages":
			setStages(true); break;
		case "NextStage":
			changeDefaultStage(true); break;
		case "PreviousStage":
			changeDefaultStage(false); break;
		case "Load":
			load(); break;
		case "OpenStageBuilder":
			openStageBuilder(); break;
		}
	}

	private void setPlayers(int players) { //Updates amount of players
		if(players == 1 && this.players != 1) {
			PWF.getController().addClass(PWF.getController().getOnePlayerButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getTwoPlayerButton(),"selected");
			PWF.getController().disable(PWF.getController().getZeroBotButton(),true);
			PWF.getController().disable(PWF.getController().getThreeBotButton(),false);
			if(bots==0) {
				setBots(1);
			}
		} else if (players == 2 && this.players != 2) {
			PWF.getController().addClass(PWF.getController().getTwoPlayerButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getOnePlayerButton(),"selected");
			PWF.getController().disable(PWF.getController().getZeroBotButton(),false);
			PWF.getController().disable(PWF.getController().getThreeBotButton(),true);
			if(bots==3) {
				setBots(2);
			}
		}
		this.players = players;
	}
	
	private void setBots(int bots) { //Updates amount of bots
		if (bots==0 && this.bots != 0) {
			PWF.getController().addClass(PWF.getController().getZeroBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getOneBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getTwoBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getThreeBotButton(),"selected");
		} else if (bots==1 && this.bots != 1) {
			PWF.getController().removeClass(PWF.getController().getZeroBotButton(),"selected");
			PWF.getController().addClass(PWF.getController().getOneBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getTwoBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getThreeBotButton(),"selected");
		} else if (bots==2 && this.bots != 2) {
			PWF.getController().removeClass(PWF.getController().getZeroBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getOneBotButton(),"selected");
			PWF.getController().addClass(PWF.getController().getTwoBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getThreeBotButton(),"selected");
		} else if (bots==3 && this.bots != 3) {
			PWF.getController().removeClass(PWF.getController().getZeroBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getOneBotButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getTwoBotButton(),"selected");
			PWF.getController().addClass(PWF.getController().getThreeBotButton(),"selected");
		}
		this.bots = bots;
	}
	
	private void play() {//Starts gameloop and switches to game pane
		PWF.getController().setImage(PWF.getController().getMenuImageView(), PWF.getImages().gameMenu);
		PWF.getController().setVisibility(PWF.getController().getMapGrid(), true);
		PWF.getController().setVisibility(PWF.getController().getBombPane(), true);
		PWF.getController().setVisibility(PWF.getController().getBangGrid(), true);
		PWF.getController().setVisibility(PWF.getController().getMenuPane(), false);
		PWF.getController().setVisibility(PWF.getController().getMapGrid(), true);
		PWF.getController().setVisibility(PWF.getController().getInterfacePane(), true);
		if(isCustomStages) {
			PWF.startGame(customStages.get(customStagesIndex), players, bots);
		} else {
			PWF.startGame(defaultStages.get(defaultStagesIndex), players, bots);
		}
	}
	
	private void help() {
		System.out.println("Help!");
	}

	private void exit() { //Stops gameloop and switches to menu pane
		PWF.getController().setImage(PWF.getController().getMenuImageView(), PWF.getImages().blank);
		PWF.getController().setVisibility(PWF.getController().getMapGrid(), false);
		PWF.getController().setVisibility(PWF.getController().getBombPane(), true);
		PWF.getController().setVisibility(PWF.getController().getBangGrid(), false);
		PWF.getController().setVisibility(PWF.getController().getMenuPane(), true);
		PWF.getController().setVisibility(PWF.getController().getMapGrid(), false);
		PWF.getController().setVisibility(PWF.getController().getInterfacePane(), false);
		PWF.endGame();
	}

	private void advancedSettings() {
		System.out.println("Advanced indeed");
	}
	
	private void setStages(boolean custom) { //Switches between showing the default and custom stage menu
		if(custom) {
			isCustomStages = true;
			PWF.getController().addClass(PWF.getController().getCustomStagesButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getDefaultStagesButton(),"selected");
			PWF.getController().getCustomStagesPane().setVisible(true);
			PWF.getController().getDefaultStagesPane().setVisible(false);
		} else {
			isCustomStages = false;
			PWF.getController().addClass(PWF.getController().getDefaultStagesButton(),"selected");
			PWF.getController().removeClass(PWF.getController().getCustomStagesButton(),"selected");
			PWF.getController().getCustomStagesPane().setVisible(false);
			PWF.getController().getDefaultStagesPane().setVisible(true);
		}
	}
	
	private void changeDefaultStage(boolean right) { //Implements an image reel to display the current default stage
		if (right) {
			PWF.getController().setImage(PWF.getController().getStageImageView(), PWF.getImages().previews[++defaultStagesIndex]);
		} else {
			PWF.getController().setImage(PWF.getController().getStageImageView(), PWF.getImages().previews[--defaultStagesIndex]);
		}
		if(defaultStagesIndex == 0) {
			PWF.getController().disable(PWF.getController().getStageLeftButton(), true);
			PWF.getController().disable(PWF.getController().getStageRightButton(), false);
		} else if(defaultStagesIndex == PWF.getImages().previews.length-1) {
			PWF.getController().disable(PWF.getController().getStageLeftButton(), false);
			PWF.getController().disable(PWF.getController().getStageRightButton(), true);
		} else {
			PWF.getController().disable(PWF.getController().getStageLeftButton(), false);
			PWF.getController().disable(PWF.getController().getStageRightButton(), false);
		}
	}

	private void load() { //Checks if input is valid, and looks for the requested stage
		String src = PWF.getController().getLoadTextField().getText();
		if(src.length()<=15) {
			if(customStages.contains("src/PlayingWithFire/customStages"+src+".txt")) {
				PWF.getController().setText(PWF.getController().getLoadText(), "Successful load\n"+src);
				customStagesIndex = customStages.indexOf("src/PlayingWithFire/customStages"+src+".txt");
			} else {
				PWF.getController().setText(PWF.getController().getLoadText(), "Load failed\nFile not found");
			}
		} else {
			PWF.getController().setText(PWF.getController().getLoadText(), "Load failed\nInput too long");
		}
	}
	
	private void openStageBuilder() {
		System.out.println("StageBuilder, not SceneBuilder");
	}
}