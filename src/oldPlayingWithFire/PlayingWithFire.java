package oldPlayingWithFire;

import java.util.ArrayList;
import java.util.Random;

import PlayingWithFire.Board;
import PlayingWithFire.Controller;
import javafx.scene.image.Image;

public class PlayingWithFire implements Runnable {
	public final static int TILE_SIZE = 40;
	public final static int BOARD_WIDTH = 15;
	public final static int BOARD_HEIGHT = 13;
	public static final int TICKSPEED = 10;
	
	private boolean isRunning;
	private Thread gameThread;
	private PWFController controller;
	
	private PWFPlayer p1,p2,p3,p4;
	// xPos, yPos, playerNumber, lives, speed, bombSize, bombAmount, bombMover
	private PWFBoard board;
	private int menuState = 1;
	private imgLoader imgLoader;
	private int timer;
	
	private ArrayList<String> bangCoords = new ArrayList<String>();
	private ArrayList<Integer> bangs = new ArrayList<Integer>();

	private boolean[] keys = new boolean[10];
	// Order: ←→↑↓,adws(space)
	
	public String msToMinString(int ms) { //(new Class?) Converts milliseconds to the format mm:ss
		return String.format("%02d:%02d", (ms/1000)/60,(ms/1000)%60);
	}
	
	public PlayingWithFire() {
		imgLoader = new imgLoader();
	}
	
	public void buttonPressed(char buttonName) { // (new Class?) Menu-system, what each button(not key) does
		switch(menuState) {
		case 1: //if in first menu
			switch(buttonName) {
			case '1':
				createPlayer(true,1);
				controller.setMenuImage(imgLoader.mm21);
				menuState = 21;
				break;
			case '2':
				createPlayer(true,1);
				createPlayer(true,2);
				controller.setMenuImage(imgLoader.mm21);
				menuState = 21;
				break;
			case '3':
				controller.setMenuImage(imgLoader.mm22);
				menuState = 22;
				break;
			}
			break;
		case 21: //if in 2nd main menu
			switch(buttonName) {
			case '1':
				if(p2 == null) {
					createPlayer(false,2);
				}
				controller.setMenuImage(imgLoader.mm31);
				menuState = 31;
				break;
			case '2':
				if(p2 == null) {
					createPlayer(false,2);
				} else {
					createPlayer(false,3);
				}
				controller.setMenuImage(imgLoader.mm31);
				menuState = 31;
				break;
			case '3':
				if(p2 == null) {
					createPlayer(false,2);
					createPlayer(false,3);
				} else {
					createPlayer(false,3);
					createPlayer(false,4);
				}
				controller.setMenuImage(imgLoader.mm31);
				menuState = 31;
				break;
			case '4':
				removePlayer('1');
				removePlayer('2');
				controller.setMenuImage(imgLoader.mm1);
				menuState = 1;
				break;
			}
			break;
		case 22: //if in first instruction menu
			switch(buttonName) {
			case 'b':
				controller.setMenuImage(imgLoader.mm1);
				menuState = 1;
				break;
			case 'f':
				controller.setMenuImage(imgLoader.mm32);
				menuState = 32;
				break;
			}
			break;
		case 32: //if in second instruction menu
			switch(buttonName) {
			case 'b':
				controller.setMenuImage(imgLoader.mm22);
				menuState = 22;
				break;
			case 'f':
				controller.setMenuImage(imgLoader.mm1);
				menuState = 1;
				break;
			}
			break;
		case 31: // if in third main menu
			switch(buttonName) {
			case '1':
			case '2':
			case '3':
				controller.setVisibility(controller.menu, false);
				controller.setImage(controller.getMenuImageView(), img.window);
				board = new Board(1);
		        gameThread = new Thread(this);
		        gameThread.setDaemon(true);
		        gameThread.start();
		        isRunning = true;
			case '4':
				controller.setMenuImage(imgLoader.mm21);
				menuState = 21;
				if(!p2.isControllable()) {
					removePlayer('2');
				}
				removePlayer('3');
				removePlayer('4');
			}
			break;
		case 4: //winner-screen
		case 0: //ingame
			if (buttonName == 'e') {
				menuState = 1;
				controller.setMenuShowing(true);
				controller.setMenuImage(imgLoader.mm1);
				controller.reset();
				board = null;
				isRunning = false;
				removePlayer('1');
				removePlayer('2');
				removePlayer('3');
				removePlayer('4');
			}
			break;
		}
	}
	
	void createPlayer(boolean controllable, int player) { //Makes new PWFPlayer, sets image position and image to visible
		switch(player) {
		case 1:
			p1 = new PWFPlayer(1, 1, '1', controllable, 3, 1, 2, 1, false, false);
			controller.setPlayerImageCoords('1', 40, 40);
			controller.setPlayerImageVisibility(p1.getPlayerNumber(), true);
			break;
		case 2:
			p2 = new PWFPlayer(BOARD_WIDTH-2, BOARD_HEIGHT-2, '2', controllable, 3, 1, 2, 1, false, false);
			controller.setPlayerImageCoords('2', 520, 440);
			controller.setPlayerImageVisibility(p2.getPlayerNumber(), true);
			break;
		case 3:
			p3 = new PWFPlayer(1, BOARD_HEIGHT-2, '3', controllable, 3, 1, 2, 1, false, false);
			controller.setPlayerImageCoords('3', 40, 440);
			controller.setPlayerImageVisibility(p3.getPlayerNumber(), true);
			break;
		case 4:
			p4 = new PWFPlayer(BOARD_WIDTH-2, 1, '4', controllable, 3, 1, 2, 1, false, false);
			controller.setPlayerImageCoords('4', 520, 40);
			controller.setPlayerImageVisibility(p4.getPlayerNumber(), true);
			break;
		}
	}
	
	void removePlayer(char player) {
		switch(player) {
		case '1':
			controller.setPlayerImageVisibility(p1.getPlayerNumber(), true);
			p1 = null;
			break;
		case '2':
			if(p2 != null) {
				controller.setPlayerImageVisibility(p2.getPlayerNumber(), true);
				p2 = null;
			}
			break;
		case '3':
			if(p3 != null) {
				controller.setPlayerImageVisibility(p3.getPlayerNumber(), true);
				p3 = null;
			}
			break;
		case '4':
			if(p4 != null) {
				controller.setPlayerImageVisibility(p4.getPlayerNumber(), true);
				p4 = null;
			}
			break;
		}
	}

	void startGameLoop(){ //Begins the game-loop
	    if(gameThread == null){
	        gameThread = new Thread(this);
	        gameThread.setDaemon(true);
	        gameThread.start();
	        isRunning = true;
	    }
	}
	
	@Override
	public void run() { //The game loop
	    synchronized (this) {
            while (isRunning) {
                update();
                try {
                    wait(TICKSPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    isRunning = false;
                }
            }
	    }
	}
	
	void updateKeys() { //Does actions based on each pressed key
		if(p1 != null) {
			if(keys[0] && p1.isControllable()) {
				p1.setDirection('l');
			} else if(keys[1] && p1.isControllable()) {
				p1.setDirection('r');
			} else if(keys[2] && p1.isControllable()) {
				p1.setDirection('u');
			} else if(keys[3] && p1.isControllable()) {
				p1.setDirection('d');
			} else {
				p1.setDirection('n');
			}
			if(keys[4] && p1.isControllable()) {
				placeBomb(p1);
			}
		}
		if(p2 != null) {
			if(keys[5] && p2.isControllable()) {
				p2.setDirection('l');
			} else if(keys[6] && p2.isControllable()) {
				p2.setDirection('r');
			} else if(keys[7] && p2.isControllable()) {
				p2.setDirection('u');
			} else if(keys[8] && p2.isControllable()) {
				p2.setDirection('d');
			} else {
				p2.setDirection('n');
			}
			if(keys[9] && p2.isControllable()) {
				placeBomb(p2);
			}
		}
	}
	
	void placeBomb(PWFPlayer p) { //if tile is available 
		if(p != null && p.getBombAmount()>0 && board.getCell(p.getxPos(), p.getyPos()) == ' ') {
			p.setBombAmount(p.getBombAmount()-1);
			PWFBomb b = new PWFBomb(p);
			board.setCell(b.centerX, b.centerY, p.getPlayerNumber());
			setTimeout(() -> explode(b),3000);
			setTimeout(() -> cleanBang(b),3500);
		}
	}
	
	void explode(PWFBomb b) {
		int c = 0;
		int bx = b.getCenterX();
		int by = b.getCenterY();
		c += bang(bx,by,false);
		for(int i=0;i<4;i++) {
			loop: for(int j=1;j<=b.size;j++) {
				switch(i) {
				case 0:
					bx = b.getCenterX()-j;
					by = b.getCenterY();
					break;
				case 1:
					bx = b.getCenterX()+j;
					by = b.getCenterY();
					break;
				case 2:
					bx = b.getCenterX();
					by = b.getCenterY()-j;
					break;
				case 3:
					bx = b.getCenterX();
					by = b.getCenterY()+j;
					break;
				default:
					bx = b.getCenterX()-j;
					by = b.getCenterY();
				}
				switch(board.getCell(bx, by)) {
				case '|':
					break loop;
				case '*':
					c += bang(bx,by,true);
					break loop;
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'x':
				case ' ':
					c += bang(bx,by,false);
					break;
				}
			}
		}
		bangs.add(c);
	}
	
	int bang(int x, int y, boolean wasKeg) {
		if(wasKeg) {
			board.setCell(x, y, 'x');
			bangCoords.add(x+","+y+",?");
			return 1;
		} else {
			board.setCell(x, y, 'x');
			bangCoords.add(x+","+y+", ");
			return 1;
		}
	}
	
	char randomPowerUp(int chance) {
		char alt = 'n';
		Random rand = new Random();
		int n = rand.nextInt(chance);
		switch(n) {
		case 0:
			alt = 'a';
			break;
		case 1:
			alt = 'b';
			break;
		case 2:
			alt = 'c';
			break;
		case 3:
			alt = 'd';
			break;
		case 4:
			alt = 'e';
			break;
		default:
			alt = ' ';
			break;
		}
		return alt;
	}
	
	void cleanBang(PWFBomb b) {
		int x;
		int y;
		char alt;
		for(int i=0;i<bangs.get(0);i++) {
			for(int j=1;j<bangCoords.size();j++) { //Don't do anything if there are multiple bangs in the coordinate
				if(bangCoords.get(0) == bangCoords.get(j)) {
					bangCoords.remove(0);
					break;
				}
			}
			x = Integer.parseInt(bangCoords.get(0).split(",")[0]);
			y = Integer.parseInt(bangCoords.get(0).split(",")[1]);
			if(bangCoords.get(0).split(",")[2].equals("?")) {
				alt = randomPowerUp(8);
			} else {
				alt = ' ';
			}
			board.setCell(x, y, alt);
			bangCoords.remove(0);
		}
		bangs.remove(0);
		if(b.getOwner() != null) {
			b.getOwner().setBombAmount(b.getOwner().getBombAmount()+1);
		}
	}
	
	void update() {
		timer -= 10;
		if(p1 != null) {
			usePlayer(p1);
			updateLives(p1);
			updatePlayerFaceDmg(p1);
		}
		if(p2 != null) {
			usePlayer(p2);
			updateLives(p2);
			updatePlayerFaceDmg(p2);
		}

		if(p3 != null) {
			usePlayer(p3);
			updateLives(p3);
			updatePlayerFaceDmg(p3);
		}
		if(p4 != null) {
			usePlayer(p4);
			updateLives(p4);
			updatePlayerFaceDmg(p4);
		}
		updateKeys();
		updateLayout();
		updateBoard();
		checkForWinner();
	}
	
	void checkForWinner() {
		if(timer == 0) {
			controller.setMenuShowing(true);
			menuState = 4;
			controller.setMenuImage(imgLoader.mm4);
			controller.setText(5, "Time's up!");
			controller.winner();
			isRunning = false;	
		}
		
		if(p4 != null) {
			if(p1.getLives()>0 && p2.getLives()==0 && p3.getLives()==0 && p4.getLives()==0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 1 wins");
				controller.winner();
				isRunning = false;			
			} else if(p1.getLives()==0 && p2.getLives()>0 && p3.getLives()==0 && p4.getLives()==0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 2 wins");
				controller.winner();
				isRunning = false;
			} else if(p1.getLives()==0 && p2.getLives()==0 && p3.getLives()>0 && p4.getLives()==0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 3 wins");
				controller.winner();
				isRunning = false;
			} else if(p1.getLives()==0 && p2.getLives()==0 && p3.getLives()==0 && p4.getLives()>0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 4 wins");
				controller.winner();
				isRunning = false;
			}
		} else if(p3 != null) {
			if(p1.getLives()>0 && p2.getLives()==0 && p3.getLives()==0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 1 wins");
				controller.winner();
				isRunning = false;		
			} else if(p1.getLives()==0 && p2.getLives()>0 && p3.getLives()==0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 2 wins");
				controller.winner();
				isRunning = false;
			} else if(p1.getLives()==0 && p2.getLives()==0 && p3.getLives()>0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 3 wins");
				controller.winner();
				isRunning = false;
			}
		} else {
			if(p1.getLives()>0 && p2.getLives()==0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 1 wins");
				controller.winner();
				isRunning = false;			
			} else if(p1.getLives()==0 && p2.getLives()>0) {
				controller.setMenuShowing(true);
				menuState = 4;
				controller.setMenuImage(imgLoader.mm4);
				controller.setText(5, "Player 2 wins");
				controller.winner();
				isRunning = false;
			}
		}
	}
	
	void updatePlayerFaceDmg(PWFPlayer p) {
		if(p.isInvincible() && p.getLives()>0) {
			controller.setPlayerImage(p.getPlayerNumber(), imgLoader.ghost);
		} else if(p.isInvincible()) {
			controller.setPlayerImage(p.getPlayerNumber(), imgLoader.burned);
			p.setControllable(false);
			setTimeout(() -> controller.setPlayerImageVisibility(p.getPlayerNumber(), false),500);
		}
	}
	
	void updatePlayerFace(PWFPlayer p) {
		if(!p.isInvincible() || p.getLives()<=0) {
			controller.setPlayerImage(p.getPlayerNumber(),playerToIMG(p.getPlayerNumber(),p.getDirection()));
		}
	}
	
	Image playerToIMG(char player, char direction) {
		Image img = imgLoader.p1front;
		switch(player) {
		case '1':
			switch(direction) {
			case 'l':
				img = imgLoader.p1left; break; 
			case 'r':
				img = imgLoader.p1right; break; 
			case 'u':
				img = imgLoader.p1back; break; 
			case 'd':
				img = imgLoader.p1front; break; 
			}
			break;
		case '2':
			switch(direction) {
			case 'l':
				img = imgLoader.p2left; break; 
			case 'r':
				img = imgLoader.p2right; break; 
			case 'u':
				img = imgLoader.p2back; break; 
			case 'd':
				img = imgLoader.p2front; break; 
			}
			break;
		case '3':
			switch(direction) {
			case 'l':
				img = imgLoader.p3left; break; 
			case 'r':
				img = imgLoader.p3right; break; 
			case 'u':
				img = imgLoader.p3back; break; 
			case 'd':
				img = imgLoader.p3front; break; 
			}
			break;
		case '4':
			switch(direction) {
			case 'l':
				img = imgLoader.p4left; break; 
			case 'r':
				img = imgLoader.p4right; break; 
			case 'u':
				img = imgLoader.p4back; break; 
			case 'd':
				img = imgLoader.p4front; break; 
			}
			break;
		}
		return img;
	}
	
	void updateLayout() {
		if(p1 != null) {
			controller.setText(1, p1.getLives()+"");
		}
		if(p2 != null) {
			controller.setText(2, p2.getLives()+"");
		}
		if(p3 != null) {
			controller.setText(3, p3.getLives()+"");
		}
		if(p4 != null) {
			controller.setText(4, p4.getLives()+"");
		}
		controller.setText(0, msToMinString(timer));
	}
	
	void updateLives(PWFPlayer p) {
		if(board.getCell(p.getxPos(), p.getyPos()) == 'x' && !p.isInvincible()) {
			controller.animateLifeLoss(p.getPlayerNumber());
			p.setLives(p.getLives()-1);
			p.setInvincible(true);
			setTimeout(() -> p.setInvincible(false),1000);
		}
	}
	
	void usePlayer(PWFPlayer p) {
		if(isValidMove(p) && p != null) {
			int animationTime = (int) (1000-410.56*Math.pow(p.getSpeed(),0.29));
			switch(p.getDirection()) {
			case 'l':
				p.setxPos(p.getxPos()-1);
		        p.setMoving(true);
		        controller.animate(p.getPlayerNumber(), p.getxPos()*40, true, animationTime);
		        updatePlayerFace(p);
				setTimeout(() -> p.setMoving(false), animationTime);
				pickPossiblePowerUp(p);
		        break;
			case 'r':
				p.setxPos(p.getxPos()+1);
		        p.setMoving(true);
		        controller.animate(p.getPlayerNumber(), p.getxPos()*40, true, animationTime);
		        updatePlayerFace(p);
				setTimeout(() -> p.setMoving(false), animationTime);
				pickPossiblePowerUp(p);
		        break;
			case 'u':
				p.setyPos(p.getyPos()-1);
		        p.setMoving(true);
		        controller.animate(p.getPlayerNumber(), p.getyPos()*40, false, animationTime);
		        updatePlayerFace(p);
				setTimeout(() -> p.setMoving(false), animationTime);
				pickPossiblePowerUp(p);
		        break;
			case 'd':
				p.setyPos(p.getyPos()+1);
		        p.setMoving(true);
		        controller.animate(p.getPlayerNumber(), p.getyPos()*40, false, animationTime);
		        updatePlayerFace(p);
				setTimeout(() -> p.setMoving(false), animationTime);
				pickPossiblePowerUp(p);
		        break;
			}

		}
	}

	public void updateBoard() {
		for(int i = 0;i<board.getBoard().length;i++) {
			for(int j = 0;j<board.getBoard()[0].length;j++) {
				Image img;
				switch(board.getCell(i, j)) {
				case '|':
					img = imgLoader.rock; break;
				case '*':
					img = imgLoader.keg; break;
				case 'x':
					img = imgLoader.bomb; break;
				case ' ':
					img = imgLoader.grass; break;
				case '1':
					img = imgLoader.p1bomb; break;
				case '2':
					img = imgLoader.p2bomb; break;
				case '3':
					img = imgLoader.p3bomb; break;
				case '4':
					img = imgLoader.p4bomb; break;
				case 'a':
					img = imgLoader.speed; break;
				case 'b':
					img = imgLoader.biggerblasts; break;
				case 'c':
					img = imgLoader.morebombs; break;
				case 'd':
					img = imgLoader.movebombs; break; 
				case 'e':
					img = imgLoader.throwbombs; break;
				default:
					img = imgLoader.rock;
				}
				controller.setTile(i,j,img);
			}
		}
	}

	public void addKey(int index) {
		keys[index] = true;
	}
	
	public void removeKey(int index) {
		keys[index] = false;
	}

	void pickPossiblePowerUp(PWFPlayer p) {
		switch(board.getBoard()[p.getxPos()][p.getyPos()]) {
		case 'a':
			p.setSpeed(p.getSpeed()+1);
			board.setCell(p.getxPos(), p.getyPos(), ' ');
			break;
		case 'b':
			p.setBombSize(p.getBombSize()+1);
			board.setCell(p.getxPos(), p.getyPos(), ' ');
			break;
		case 'c':
			p.setBombAmount(p.getBombAmount()+1);
			board.setCell(p.getxPos(), p.getyPos(), ' ');
			break;
		case 'd':
			p.setBombMover(true);
			board.setCell(p.getxPos(), p.getyPos(), ' ');
			break;
		case 'e':
			p.setBombThrower(true);
			board.setCell(p.getxPos(), p.getyPos(), ' ');
			break;
		}
	}
	
	public boolean isValidMove(PWFPlayer p) {
		if(p.isMoving()) {
			return false;
		}
		switch(p.getDirection()) {
		case 'u':
			switch(board.getBoard()[p.getxPos()][p.getyPos()-1]) {
			case ' ':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'x':
				return true;
			}
			break;
		case 'd':
			switch(board.getBoard()[p.getxPos()][p.getyPos()+1]) {
			case ' ':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'x':
				return true;
			}
			break;
		case 'l':
			switch(board.getBoard()[p.getxPos()-1][p.getyPos()]) {
			case ' ':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'x':
				return true;
			}
			break;
		case 'r':
			switch(board.getBoard()[p.getxPos()+1][p.getyPos()]) {
			case ' ':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'x':
				return true;
			}
			break;
		}
		return false;
	}
	
	public PWFBoard getBoard() {
		return board;
	}
	
	public void setController(PWFController controller) {
		this.controller = controller;
	}
	
	public static void setTimeout(Runnable runnable, int delay){
	    new Thread(() -> {
	        try {
	            Thread.sleep(delay);
	            runnable.run();
	        }
	        catch (Exception e){
	            System.err.println(e);
	        }
	    }).start();
	}
}
