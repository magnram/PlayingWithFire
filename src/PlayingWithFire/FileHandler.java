package PlayingWithFire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
	String source;
	
	public static String load(String src) {
		Scanner sc;
		String board = "";
		try {
			sc = new Scanner(new FileReader(src));
			while(sc.hasNext()) {
				board+= sc.nextLine();
				board+= "\n";
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return board;
	}
	
	public void save(List<List<Character>> list) {
		PrintWriter w;
		try {
			w = new PrintWriter("Boards/"+source+".txt");
			String str = "";
			for(int i = 0;i<list.size(); i++) {
				for(int j = 0;j<list.get(i).size(); j++) {
					str += list.get(i).get(j);
				}
				str += "\n";
			}
			w.print(str);
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> listFiles(String directory) {
		  List<String> textFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().endsWith((".txt"))) {
		      textFiles.add(directory+"/"+file.getName());
		    }
		  }
		  return textFiles;
		}
}
