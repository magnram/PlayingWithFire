package PlayingWithFire;


public class Player {
	private int x,y,playerNumber,lives = 3,speed = 1,bombSize = 2,bombAmount = 1;
	private boolean controllable,invincible = false,bombMover = false,bombThrower = false,moving = false;
	private String direction = "None";
	
	public Player(int x,int y, boolean controllable, int playerNumber) {
		this.x = x*Variables.TILE_SIZE;
		this.y = y*Variables.TILE_SIZE;
		this.controllable = controllable;
		this.playerNumber = playerNumber;
	}
	
	public Player(int x, int y, char playerNumber, boolean controllable, int lives, int speed, int bombSize, int bombAmount, boolean bombMover, boolean bombThrower) {
		this.x = x;
		this.x = y;
		this.playerNumber = playerNumber;
		this.controllable = controllable;
		this.lives = lives;
		this.speed = speed;
		this.bombSize = bombSize;
		this.bombAmount = bombAmount;
		this.bombMover = bombMover;
		this.bombThrower = bombThrower;
		this.moving = false;
		this.invincible = false;
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
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

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
