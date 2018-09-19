package PlayingWithFire;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class PlayingWithFire implements Runnable {
	final Images img = new Images(); //All images
	private Menu menu; //Menu-logic
	private Controller controller; //Controls the FXML and key-inputs
	private Controls controls = new Controls();
	
	private boolean isRunning = false; //Is the loop running?
	private Thread gameThread; //Something with threads and loops and stuff
	private Player p1,p2,p3,p4; //All the players, may be null
	
	private Board board; //The board
	//private int timer = Variables.GAME_TIME; //The game-timer
	private Map<String,Boolean> pressedKeys = new HashMap<>();
	
	PlayingWithFire() { 
		pressedKeys.put("p1left", false);
		pressedKeys.put("p1right", false);
		pressedKeys.put("p1up", false);
		pressedKeys.put("p1down", false);
		pressedKeys.put("p2left", false);
		pressedKeys.put("p2right", false);
		pressedKeys.put("p2up", false);
		pressedKeys.put("p2down", false);
	}
	

	public void keyDown(KeyCode code) { //KeyDown -> pressedKeys
    	if(isRunning) {
    		if(code == controls.p1left) {
    			pressedKeys.put("p1left", true);
        	} else if(code == controls.p1right) {
    			pressedKeys.put("p1right", true);
        	} else if(code == controls.p1up) {
    			pressedKeys.put("p1up", true);
        	} else if(code == controls.p1down) {
    			pressedKeys.put("p1down", true);
        	} else if(code == controls.p1bomb) {
        		placeBomb(p1);
        	} else if(code == controls.p2left) {
    			pressedKeys.put("p2left", true);
        	} else if(code == controls.p2right) {
    			pressedKeys.put("p2right", true);
        	} else if(code == controls.p2up) {
    			pressedKeys.put("p2up", true);
        	} else if(code == controls.p2down) {
    			pressedKeys.put("p2down", true);
        	} else if(code == controls.p2bomb) {
        		placeBomb(p2);
        	}
    	}
	}
	public void keyUp(KeyCode code) { //KeyUp -> pressedKeys
    	if(isRunning) {
    		if(code == controls.p1left) {
    			pressedKeys.put("p1left", false);
        	} else if(code == controls.p1right) {
    			pressedKeys.put("p1right", false);
        	} else if(code == controls.p1up) {
    			pressedKeys.put("p1up", false);
        	} else if(code == controls.p1down) {
    			pressedKeys.put("p1down", false);
        	} else if(code == controls.p2left) {
    			pressedKeys.put("p2left", false);
        	} else if(code == controls.p2right) {
    			pressedKeys.put("p2right", false);
        	} else if(code == controls.p2up) {
    			pressedKeys.put("p2up", false);
        	} else if(code == controls.p2down) {
    			pressedKeys.put("p2down", false);
        	}
    	}
	}
	
	public void update() { //updateMapGrid(),updatePlayerMovement()
		updateMapGrid();
		updatePlayerMovement();
	}
	public void updateMapGrid() { //Board -> GridPane
		for(int y = 0;y<Variables.BOARD_HEIGHT;y++) {
			for(int x = 0;x<Variables.BOARD_WIDTH;x++) {
				Image img;
				switch(board.getCell(x,y).getType()) {
				case "Rock":
					img = this.img.rock; break;
				case "Keg":
					img = this.img.keg; break;
				case "Grass":
					img = this.img.grass; break;
				case "Speed":
					img = this.img.speed; break;
				case "BiggerBlasts":
					img = this.img.biggerblasts; break;
				case "MoreBombs":
					img = this.img.morebombs; break;
				case "KickBombs":
					img = this.img.kickbombs; break; 
				case "ThrowBombs":
					img = this.img.throwbombs; break;
				case "PortalA":
					img = this.img.kickbombs; break;
				case "PortalB":
					img = this.img.throwbombs; break;
				default:
					img = this.img.grass;
				}
				controller.setMapTile(x,y,img);
			}
		}
	}
	public void updatePlayerMovement() { //pressedKeys -> attemptMove
		if(pressedKeys.get("p1left")) {
			attemptMove(p1,"Left");
		} else if(pressedKeys.get("p1right")) {
			attemptMove(p1,"Right");
		} else if(pressedKeys.get("p1up")) {
			attemptMove(p1,"Up");
		} else if(pressedKeys.get("p1down")) {
			attemptMove(p1,"Down");
		} else {
			p1.setDirection("None");
		}
		if(p2.isControllable()) {
			if(pressedKeys.get("p2left")) {
				attemptMove(p2,"Left");
			} else if(pressedKeys.get("p2right")) {
				attemptMove(p2,"Right");
			} else if(pressedKeys.get("p2up")) {
				attemptMove(p2,"Up");
			} else if(pressedKeys.get("p2down")) {
				attemptMove(p2,"Down");
			} else {
				p2.setDirection("None");
			}
		}
	}
	/*
	int realPositionX = player.getX();
	int realPositionY = player.getY();
	*/
	
	//Movement:
	public void attemptMove(Player player, String direction) {
		player.setDirection(direction);
		int distance = validMoveDistance(player,true);
		if(distance!=0) {
			movePlayer(player,distance);
		} else {
			moveAssist(player);
		}
	}
	public int validMoveDistance(Player player, boolean newTile) {
		if(isCentered(player)) {
			Cell nextCell = nextCell(player); //Skurrer
			int distance = Math.abs(nextCell.getX()-player.getX())+Math.abs(nextCell.getY()-player.getY()); //Skurrer
			int speed = player.getSpeed();
			if(distance>speed) {
				return speed;
			} else if(nextCell.isAvailable() && newTile) {
				updateCell(player,nextCell);
				return speed;
			}
			return distance;
		}
		return 0;
	}
	public Cell nextCell(Player player) {
		Cell cell;
		int boardPositionX = (int)Math.round(player.getX()/(double)Variables.TILE_SIZE);
		int boardPositionY = (int)Math.round(player.getX()/(double)Variables.TILE_SIZE);
		switch(player.getDirection()) {
		case "Left":
			cell = board.getCell(boardPositionX-1, boardPositionY); break;
		case "Right":
			cell = board.getCell(boardPositionX+1, boardPositionY); break;
		case "Up":
			cell = board.getCell(boardPositionX, boardPositionY-1); break;
		case "Down":
			cell = board.getCell(boardPositionX, boardPositionY+1); break;
		default: 
			cell = board.getCell(0,0);
		}
		return cell;
	}
	public boolean isCentered(Player player) {
		switch(player.getDirection()) {
		case "Left":
		case "Right":
			return (player.getY()%Variables.TILE_SIZE == 0);
		case "Up":
		case "Down":
			return (player.getX()%Variables.TILE_SIZE == 0);
		}
		return false;
	}

	public void updateCell(Player player, Cell cell) { //POWERUPS, TELEPORTERS AND SUCH
		
	}
	public void movePlayer(Player p, int distance) {
		int x=0;
		int y=0;
		switch(p.getDirection()) {
		case "Left":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.lefts[p.getPlayerNumber()-1]);
			x=p.getX()-distance; y=p.getY(); break;
		case "Right":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.rights[p.getPlayerNumber()-1]);
			x=p.getX()+distance; y=p.getY(); break;
		case "Up":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.backs[p.getPlayerNumber()-1]);
			x=p.getX(); y=p.getY()-distance; break;
		case "Down":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.fronts[p.getPlayerNumber()-1]);
			x=p.getX(); y=p.getY()+distance; break;
		}
		p.setX(x); p.setY(y);
		controller.moveTo(Converter.playerToImageView(controller, p.getPlayerNumber()), x, y);
	}
	
	public void moveAssist(Player player,Cell nextCell) {
		if(nextCell.isAvailable()) {
			
		}
	}
	
	/*
	 * IF centered in other axis:
	 * * IF distance to next square is less than speed AND next square is not available:
	 * * * Move distance to next square towards target direction
	 * * ELSE
	 * * * Move speed towards target direction
	 * ELSE
	 * * IF closest square in target direction is available AND distance to the square next to it is less than thresh-hold:
	 * * 
	Attempt to move in the requested direction, if not centered in the other direction, and the distance until centered 
	is less than the thresh-hold, then move towards the center, until centered. Then proceed to move in the requested direction.
	*/
	//Movement
	/*
	public void attemptMove(Player player, String direction, boolean mainDirection) {
		int distance = legalMoveSize(player,direction);
		if(distance!=0 && isValidMove(player,direction)) {
			if(hitsNewTile(player,direction)) {
				Cell cell;
				switch(direction) {
				case "Left":
					cell = board.getCell((player.getX()/Variables.TILE_SIZE)-1, player.getY()/Variables.TILE_SIZE); break;
				case "Right":
					cell = board.getCell((player.getX()/Variables.TILE_SIZE)+1, player.getY()/Variables.TILE_SIZE); break;
				case "Up":
					cell = board.getCell(player.getX()/Variables.TILE_SIZE, (player.getY()/Variables.TILE_SIZE)-1); break;
				case "Down":
					cell = board.getCell(player.getX()/Variables.TILE_SIZE, (player.getY()/Variables.TILE_SIZE)+1); break;
				default: 
					cell = board.getCell((player.getX()/Variables.TILE_SIZE)-1, player.getY()/Variables.TILE_SIZE); break;
				}
				switch(cell.getType()) {
				case "Speed":
					cell.setType("Grass");
					player.setSpeed(player.getSpeed()+1);
					break;
				case "BiggerBlasts":
					cell.setType("Grass");
					player.setBombSize(player.getBombSize()+1);
					break;
				case "MoreBombs":
					cell.setType("Grass");
					player.setBombAmount(player.getBombAmount()+1);
					break;
				case "KickBombs":
					cell.setType("Grass");
					player.setBombMover(true);
					break; 
				case "ThrowBombs":
					cell.setType("Grass");
					player.setBombThrower(true);
					break;
				case "PortalA":
					break;
				case "PortalB":
					break;	
				}
			}
			movePlayer(player,direction,distance);
		} else if(mainDirection){
			assistMove(player,direction);
		}
	}
	public boolean hitsNewTile(Player p, String direction) {
		switch(direction) {
		case "Left":
			return p.getX()%Variables.TILE_SIZE < p.getSpeed();
		case "Right":
			return (Variables.TILE_SIZE-p.getX())%Variables.TILE_SIZE < p.getSpeed();
		case "Up":
			return p.getY()%Variables.TILE_SIZE < p.getSpeed();
		case "Down":
			return (Variables.TILE_SIZE-p.getY())%Variables.TILE_SIZE < p.getSpeed();
		}
		return false;
	}
	public int legalMoveSize(Player p, String direction) {
		switch(direction) {
		case "Left":
			//If distance to next square is less than speed AND the new square to the Left is NOT available
			if(hitsNewTile(p, direction) && !board.getCell((p.getX()/Variables.TILE_SIZE)-1, p.getY()/Variables.TILE_SIZE).isAvailable()) { 
				return p.getX()%Variables.TILE_SIZE;
			} else {
				return p.getSpeed();
			}
		case "Right":
			if(hitsNewTile(p, direction) && !board.getCell((p.getX()/Variables.TILE_SIZE)+1, p.getY()/Variables.TILE_SIZE).isAvailable()) { 
				return (Variables.TILE_SIZE-p.getX())%Variables.TILE_SIZE;
			} else {
				return p.getSpeed();
			}
		case "Up":
			if(hitsNewTile(p, direction) && !board.getCell(p.getX()/Variables.TILE_SIZE, (p.getY()/Variables.TILE_SIZE)-1).isAvailable()) { 
				return p.getY()%Variables.TILE_SIZE;
			} else {
				return p.getSpeed();
			}
		case "Down":
			if(hitsNewTile(p, direction) && !board.getCell(p.getX()/Variables.TILE_SIZE, (p.getY()/Variables.TILE_SIZE)+1).isAvailable()) { 
				return (Variables.TILE_SIZE-p.getY())%Variables.TILE_SIZE;
			} else {
				return p.getSpeed();
			}
		}
		return 0;
	}
	public boolean isValidMove(Player p, String direction) {
		switch(direction) {
		case "Left":
		case "Right":
			return (p.getY()%Variables.TILE_SIZE == 0);
		case "Up":
		case "Down":
			return (p.getX()%Variables.TILE_SIZE == 0);
		}
		return false;
	}
	public void movePlayer(Player p, String direction, int distance) {
		int x=0;
		int y=0;
		int dist = distance;
		if(Math.abs(dist)>p.getSpeed()) {
			dist = p.getSpeed();
		}
		switch(direction) {
		case "Left":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.lefts[p.getPlayerNumber()-1]);
			x=p.getX()-dist; y=p.getY(); break;
		case "Right":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.rights[p.getPlayerNumber()-1]);
			x=p.getX()+dist; y=p.getY(); break;
		case "Up":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.backs[p.getPlayerNumber()-1]);
			x=p.getX(); y=p.getY()-dist; break;
		case "Down":
			controller.setImage(Converter.playerToImageView(controller, p.getPlayerNumber()), img.fronts[p.getPlayerNumber()-1]);
			x=p.getX(); y=p.getY()+dist; break;
		}
		p.setX(x); p.setY(y);
		controller.moveTo(Converter.playerToImageView(controller, p.getPlayerNumber()), x, y);
	}
	public void assistMove(Player p, String direction) {
		switch(direction) {
		case "Left":
			if(board.getCell(p.getX()/Variables.TILE_SIZE-1, p.getY()/Variables.TILE_SIZE).isAvailable() && p.getY()%Variables.TILE_SIZE<Variables.MAXIMUM_OFFSET) {
					attemptMove(p,"Up",false);
			} else if(board.getCell(p.getX()/Variables.TILE_SIZE-1, p.getY()/Variables.TILE_SIZE+1).isAvailable() && (Variables.TILE_SIZE-p.getY()%Variables.TILE_SIZE)<Variables.MAXIMUM_OFFSET) {
					attemptMove(p,"Down",false);
			} break;
		case "Right":
			if(board.getCell(p.getX()/Variables.TILE_SIZE+1, p.getY()/Variables.TILE_SIZE).isAvailable() && p.getY()%Variables.TILE_SIZE<Variables.MAXIMUM_OFFSET) {
				attemptMove(p,"Up",false);
			} else if(board.getCell(p.getX()/Variables.TILE_SIZE+1, p.getY()/Variables.TILE_SIZE+1).isAvailable() && (Variables.TILE_SIZE-p.getY()%Variables.TILE_SIZE)<Variables.MAXIMUM_OFFSET) {
				attemptMove(p,"Down",false);
			} break;
		case "Up":
			if(board.getCell(p.getX()/Variables.TILE_SIZE, p.getY()/Variables.TILE_SIZE-1).isAvailable() && p.getX()%Variables.TILE_SIZE<Variables.MAXIMUM_OFFSET) {
				attemptMove(p,"Left",false);
			} else if(board.getCell(p.getX()/Variables.TILE_SIZE+1, p.getY()/Variables.TILE_SIZE-1).isAvailable() && (Variables.TILE_SIZE-p.getX()%Variables.TILE_SIZE)<Variables.MAXIMUM_OFFSET) {
				attemptMove(p,"Right",false);
			} break;
		case "Down":
			if(board.getCell(p.getX()/Variables.TILE_SIZE, p.getY()/Variables.TILE_SIZE+1).isAvailable() && p.getX()%Variables.TILE_SIZE<Variables.MAXIMUM_OFFSET) {
				attemptMove(p,"Left",false);
			} else if(board.getCell(p.getX()/Variables.TILE_SIZE+1, p.getY()/Variables.TILE_SIZE+1).isAvailable() && (Variables.TILE_SIZE-p.getX()%Variables.TILE_SIZE)<Variables.MAXIMUM_OFFSET) {
				attemptMove(p,"Right",false);
			} break;
		}
	}
	*/
	
	//Bombs
	public void placeBomb(Player player) {
		if(player.getBombAmount() > 0) {
			int boardPositionX = (int)Math.round(player.getX()/(double)Variables.TILE_SIZE);
			int boardPositionY = (int)Math.round(player.getY()/(double)Variables.TILE_SIZE);
			player.setBombAmount(player.getBombAmount()-1); //Reduce players bomb amount by one
			board.getCell(boardPositionX, boardPositionY).setAvailable(false);
			ImageView iv = controller.bombStack.pop();
			controller.moveTo(iv, boardPositionX*Variables.TILE_SIZE, boardPositionY*Variables.TILE_SIZE);
			controller.setImage(iv,img.bombs[player.getPlayerNumber()-1]);
			setTimeout(() -> explode(boardPositionX, boardPositionY, player, iv),3000);	
		}
	}
	private void explode(int boardPositionX, int boardPositionY, Player player, ImageView iv) {
		if(isRunning) {
			player.setBombAmount(player.getBombAmount()+1);
			int bx = boardPositionX;
			int by = boardPositionY;
			iv.setImage(null);
			controller.bombStack.add(iv);
			board.getCell(bx, by).setAvailable(true);
			bang(player,bx,by);
			for(int i=0;i<4;i++) {
				loop: for(int j=1;j<=player.getBombSize();j++) {
					switch(i) {
					case 0:
						bx = boardPositionX-j;
						by = boardPositionY;
						break;
					case 1:
						bx = boardPositionX+j;
						by = boardPositionY;
						break;
					case 2:
						bx = boardPositionX;
						by = boardPositionY-j;
						break;
					case 3:
						bx = boardPositionX;
						by = boardPositionY+j;
						break;
					}
					switch(board.getCell(bx, by).getType()) {
					case "Grass":
						bang(player,bx,by); break;
					case "Keg":
						bang(player,bx,by); break loop;
					default:
						break loop;
					}
				}
			}

		}
	}
	public void bang(Player player, int boardPositionX, int boardPositionY) {
		board.getCell(boardPositionX,boardPositionY).setAvailable(true);
		controller.setBangTile(boardPositionX, boardPositionY, img.bangs[player.getPlayerNumber()-1]);
		if(board.getCell(boardPositionX, boardPositionY).getType().equals("Keg")) {
			board.getCell(boardPositionX, boardPositionY).setType(randomPowerUp());
		}
		setTimeout(() -> clearBang(boardPositionX,boardPositionY),1000);

	}
	private String randomPowerUp() {
		String alt = "";
		Random rand = new Random();
		int n = rand.nextInt(100);
		if(n < Variables.SPEED_CHANCE) {
			alt = "Speed";
		} else if(n < Variables.SPEED_CHANCE+Variables.BIGGERBLASTS_CHANCE) {
			alt = "BiggerBlasts";
		} else if(n < Variables.SPEED_CHANCE+Variables.BIGGERBLASTS_CHANCE+Variables.MOREBOMBS_CHANCE) {
			alt = "MoreBombs";
		} else if(n < Variables.SPEED_CHANCE+Variables.BIGGERBLASTS_CHANCE+Variables.MOREBOMBS_CHANCE+Variables.KICKBOMBS_CHANCE) {
			alt = "KickBombs";
		} else if(n < Variables.SPEED_CHANCE+Variables.BIGGERBLASTS_CHANCE+Variables.MOREBOMBS_CHANCE+Variables.KICKBOMBS_CHANCE+Variables.THROWBOMBS_CHANCE) {
			alt = "ThrowBombs";
		} else {
			alt = "Grass";
		}
		return alt;
	}
	public void clearBang(int boardPositionX, int boardPositionY) {
		controller.setBangTile(boardPositionX, boardPositionY, null);
	}
	
	public void start() {
		menu = new Menu(this);		
	}
	
	@Override
	public void run() {
	    synchronized (this) {
            while (isRunning) {
                update();
                try {
                    wait(Variables.TICKSPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    isRunning = false;
                }
            }
	    }
	}
	
	void startGame(String stage, int players, int bots) {
		board = new Board(stage);
        updateMapGrid();
		generatePlayers(players,bots);
        gameThread = new Thread(this);
        gameThread.setDaemon(true);
        gameThread.start();
        isRunning = true;
	}
	void endGame() { //removes all bombs, all players, the board and turns off gameloop
		removePlayers();
		board = null;
		isRunning = false;
	}
	

	private void generatePlayers(int players, int bots) { //Creates the proper amount of players and bots
			generatePlayer(board.getSpawns(1)[0], board.getSpawns(1)[1], true, 1);
			generatePlayer(board.getSpawns(2)[0], board.getSpawns(2)[1], (players == 2), 2);
			switch(bots+players) {
			case 3:
				generatePlayer(board.getSpawns(3)[0], board.getSpawns(3)[1], false, 3);
				break;
			case 4:
				generatePlayer(board.getSpawns(3)[0], board.getSpawns(3)[1], false, 3);
				generatePlayer(board.getSpawns(4)[0], board.getSpawns(4)[1], false, 4);
				break;
			}
	}
	private void removePlayers() { //removes all the player objects, and hides all the player images
		p1=null;
		controller.setVisibility(controller.getP1ImageView(), false);
		controller.setVisibility(controller.getP1InterfacePane(), false);
		p2=null;
		controller.setVisibility(controller.getP2ImageView(), false);
		controller.setVisibility(controller.getP2InterfacePane(), false);
		p3=null;
		controller.setVisibility(controller.getP3ImageView(), false);
		controller.setVisibility(controller.getP3InterfacePane(), false);
		p4=null;
		controller.setVisibility(controller.getP4ImageView(), false);
		controller.setVisibility(controller.getP4InterfacePane(), false);
	}
	private void generatePlayer(int x,int y, boolean controllable, int playerNumber) { //Creates player object, shows the player image and updates the image position
		switch(playerNumber) {
		case 1:
			p1 = new Player(x,y,controllable,playerNumber);
			controller.setVisibility(controller.getP1ImageView(), true);
			controller.setVisibility(controller.getP1InterfacePane(), true);
			controller.moveTo(controller.getP1ImageView(),p1.getX(), p1.getY());
			break;
		case 2:
			p2 = new Player(x,y,controllable,playerNumber);
			controller.setVisibility(controller.getP2ImageView(), true);
			controller.setVisibility(controller.getP2InterfacePane(), true);
			controller.moveTo(controller.getP2ImageView(),p2.getX(), p2.getY());
			break;
		case 3:
			p3 = new Player(x,y,controllable,playerNumber);
			controller.setVisibility(controller.getP3ImageView(), true);
			controller.setVisibility(controller.getP3InterfacePane(), true);
			controller.moveTo(controller.getP3ImageView(),p3.getX(), p3.getY());
			break;
		case 4:
			p4 = new Player(x,y,controllable,playerNumber);
			controller.setVisibility(controller.getP4ImageView(), true);
			controller.setVisibility(controller.getP4InterfacePane(), true);
			controller.moveTo(controller.getP4ImageView(),p4.getX(), p4.getY());
			break;
		}

	}
	
	
	//Getters and setters:
	public Images getImages() {
		return img;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public Controller getController() {
		return controller;
	}
	public Board getBoard() {
		return board;
	}
	public Player getPlayer(int p) {
		switch(p) {
		case 1:
			return p1;
		case 2:
			return p2;
		case 3:
			return p3;
		case 4:
			return p4;
		default:
			return p1;
		}
	}

	//Timeouts:
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