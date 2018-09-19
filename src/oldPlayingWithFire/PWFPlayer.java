package oldPlayingWithFire;

public class PWFPlayer {
	private int xPos;
	private int yPos;
	private char playerNumber;
	private boolean controllable;
	private boolean invincible;
	
	private int lives;
	private int speed;
	private int bombSize;
	private int bombAmount;
	private boolean bombMover;
	private boolean bombThrower;
	
	private char direction;
	private boolean moving;
	
	public PWFPlayer(int xPos, int yPos, char playerNumber, boolean controllable, int lives, int speed, int bombSize, int bombAmount, boolean bombMover, boolean bombThrower) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.playerNumber = playerNumber;
		this.controllable = controllable;
		this.lives = lives;
		this.speed = speed;
		this.bombSize = bombSize;
		this.bombAmount = bombAmount;
		this.bombMover = bombMover;
		this.bombThrower = bombThrower;
		this.direction = 'n';
		this.moving = false;
		this.invincible = false;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public char getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(char playerNumber) {
		this.playerNumber = playerNumber;
	}

	public boolean isControllable() {
		return controllable;
	}

	public void setControllable(boolean controllable) {
		this.controllable = controllable;
	}
	
	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBombSize() {
		return bombSize;
	}

	public void setBombSize(int bombSize) {
		this.bombSize = bombSize;
	}

	public int getBombAmount() {
		return bombAmount;
	}

	public void setBombAmount(int bombAmount) {
		this.bombAmount = bombAmount;
	}

	public boolean isBombMover() {
		return bombMover;
	}

	public void setBombMover(boolean bombMover) {
		this.bombMover = bombMover;
	}
	public boolean isBombThrower() {
		return bombThrower;
	}

	public void setBombThrower(boolean bombThrower) {
		this.bombThrower = bombThrower;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
