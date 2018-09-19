package oldPlayingWithFire;
import java.util.Random;

public class PWFBoard {
	private char[][] board;
	
	public PWFBoard (int type) {
		board = new char[PlayingWithFire.BOARD_WIDTH][PlayingWithFire.BOARD_HEIGHT];
		switch(type) {
		case 1:
			for(int i = 0;i<board.length;i++) {
				for(int j = 0;j<board[0].length;j++) {
					if(i==0 || i==board.length-1 || j == 0 || j == board[0].length-1 || ((i%2) == 0) && ((j%2) == 0)) {
						board[i][j] = '|';
					} else if (((i==1 || i==board.length-2) && (j==1 || j==2 || j==board[0].length-3 || j==board[0].length-2)) || ((j==1 || j==board[0].length-2) && (i==2 || i==board.length-3))) {
						board[i][j] = ' ';
					} else {
						Random rand = new Random();
						int n = rand.nextInt(10)+1;
						if(n <= 7) {
							board[i][j] = '*';
						} else {
							board[i][j] = ' ';
						}
					}
				}
			}
			break;
		}
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public char getCell(int xPos,int yPos) {
		return board[xPos][yPos];
	}
	
	public void setCell(int xPos,int yPos,char change) {
		this.board[xPos][yPos] = change;
	}
	
	public String toString() {
		String output = "";
		for (int i=0; i<board[0].length; i++) {
			for (int j=0; j<board.length; j++) {
				output += board[j][i] + " ";
			} 
			output +="\n";
		}
		return output;
	}
}
