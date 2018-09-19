package PlayingWithFire;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
	private char[][] charBoard;
	private Cell[][] board = new Cell[Variables.BOARD_HEIGHT][Variables.BOARD_WIDTH];
	private Map<Integer, int[]> spawns = new HashMap<>();

	
	public Board (String stage) {
		charBoard = Converter.stringToCharMatrix((FileHandler.load(stage)));
		for(int y = 0; y<charBoard.length;y++) {
			for(int x=0; x<charBoard[y].length; x++) {
				board[y][x] = new Cell(x,y,"Grass",true,false); //type,available,portal
				char c = charBoard[y][x];
				switch(c) {
				case '|':
					board[y][x].setType("Rock"); 
					board[y][x].setAvailable(false);
					break;
				case '?':
					if(new Random().nextInt(100)+1 <= Variables.KEG_CHANCE) {
						board[y][x].setType("Keg");
						board[y][x].setAvailable(false);
						break;
					} break;
				case '*':
					board[y][x].setType("Keg");
					board[y][x].setAvailable(false);
					break;
				case '-':
					board[y][x].setType("NeutralBang"); break;
				case 'a':
					board[y][x].setType("PortalA"); break;
				case 'b':
					board[y][x].setType("PortalB"); break;
				case '1':
					int[] coords1 = {x,y};
					spawns.put(1, coords1);
					break;
				case '2':
					int[] coords2 = {x,y};
					spawns.put(2, coords2);
					break;
				case '3':
					int[] coords3 = {x,y};
					spawns.put(3, coords3);
					break;
				case '4':
					int[] coords4 = {x,y};
					spawns.put(4, coords4);
					break;
				}
			}
		}
	}
	
	public Cell getCell(int x,int y) {
		return board[y][x];
	}
	public int[] getSpawns(int player) {
		return spawns.get(player);
	}
}

/* Algoritmer for å lage de ulike banene
private void generateField(int stageNumber) {
switch(stageNumber) {
case 0:
	for(int i = 0;i<Variables.BOARD_WIDTH;i++) {
		for(int j = 0;j<Variables.BOARD_HEIGHT;j++) {
			if(i%2==0 && j%2==0) {
				board[y][x].setType("Rock");
				board[y][x].setAvailable(false);
			}
		}
	} break;
case 1:
	for(int i = 0;i<Variables.BOARD_WIDTH;i++) {
		for(int j = 0;j<Variables.BOARD_HEIGHT;j++) {
			if(i>1 && i<Variables.BOARD_WIDTH-2 && j>1 && j<Variables.BOARD_HEIGHT-2) {
				if((i==2 && (j%2==1 || j==2 || j==Variables.BOARD_HEIGHT-3)) || (j==2 && (i%2==1 || i==Variables.BOARD_WIDTH-3)) || (i==Variables.BOARD_WIDTH-3 && j==Variables.BOARD_HEIGHT-3)) {
					board[y][x].setType("Rock");
					board[y][x].setAvailable(false);
				} else if((i==Variables.BOARD_WIDTH-3 && j%2==1) || (j==Variables.BOARD_HEIGHT-3 && i%2==1)) {
					board[y][x].setType("Rock");
					board[y][x].setAvailable(false);
				}
			}
			if(i>3 && i<Variables.BOARD_WIDTH-4 && j>3 && j<Variables.BOARD_HEIGHT-4) {
				if(i == 4 || j == 4 || i == Variables.BOARD_WIDTH-5 || j == Variables.BOARD_HEIGHT-5)
				board[y][x].setType("NeutralBang");
			}
			if((i==6 && j==6) || (i==6 && j==Variables.BOARD_HEIGHT-7) || (i==Variables.BOARD_WIDTH-7 && j==Variables.BOARD_HEIGHT-7) || (i==Variables.BOARD_WIDTH-7 && j==6)) {
				board[y][x].setType("Rock");
				board[y][x].setAvailable(false);
			}
		}
	} break;
case 2:
	for(int i = 0;i<Variables.BOARD_WIDTH;i++) {
		for(int j = 0;j<Variables.BOARD_HEIGHT;j++) {
			if(i%2==0 && j%2==0) {
				board[y][x].setType("Rock");
				board[y][x].setAvailable(false);
			} 
			if((i==3 && j==3) || (i==Variables.BOARD_WIDTH-4 && j==Variables.BOARD_HEIGHT-4)) {
				board[y][x].setType("PortalA");
			}
			if((i==3 && j==Variables.BOARD_HEIGHT-4) || (i==Variables.BOARD_WIDTH-4 && j==3)) {
				board[y][x].setType("PortalB");
			}
		}
	} break;
}
}
*/