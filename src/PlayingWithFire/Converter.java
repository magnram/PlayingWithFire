package PlayingWithFire;

import java.util.List;

import javafx.scene.image.ImageView;

public class Converter {
	
	public static ImageView playerToImageView(Controller controller, int p) {
		switch(p) {
		case 1:
			return controller.getP1ImageView();
		case 2:
			return controller.getP2ImageView();
		case 3:
			return controller.getP3ImageView();
		case 4:
			return controller.getP4ImageView();
		}
		return controller.getP1ImageView();
	}
	
	public static char[][] stringToCharMatrix(String string) {
		char[][] matrix = new char[Variables.BOARD_HEIGHT][Variables.BOARD_WIDTH];
		String[] lines = string.split("\n");
		for (int i = 0; i<Variables.BOARD_HEIGHT; i++) {
			matrix[i] = lines[i].toCharArray();
		}
		return matrix;
	}
	public static String charMatrixToString(List<List<Character>> list) {
		String s = "";
		for(int i = 0; i<list.size();i++) {
			for(int j = 0; j<list.get(i).size();j++) {
				s+=list.get(j);
			}
			s+="\n";
		}
		return s;
	}

}
