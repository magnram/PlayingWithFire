package oldPlayingWithFire;

public class PWFBomb {
	int centerX;
	int centerY;
	int size;
	PWFBoard board;
	PWFPlayer p;

	public PWFBomb(PWFPlayer player) {
		p = player;
		centerX = p.getxPos();
		centerY = p.getyPos();
		this.size = p.getBombSize();
		//board.setCell(centerX, centerY, owner); do this somewhere else
	}
	
	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSize() {
		return size;
	}

	public PWFPlayer getOwner() {
		return p;
	}
}
