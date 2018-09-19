package PlayingWithFire;

public class Cell {
	int x;
	int y;
	private String type;
	private boolean available;
	private boolean portal;
	
	public Cell(int x, int y, String type, boolean available, boolean portal) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.available = available;
		this.portal = portal;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public boolean isPortal() {
		return portal;
	}
	public void setPortal(boolean portal) {
		this.portal = portal;
	}
}
