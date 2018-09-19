package PlayingWithFire;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter {
	String source;
	public FileWriter(String source) {
		this.source = source;
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
}
