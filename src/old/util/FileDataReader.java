package old.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import old.tree.data.GuestEntityData;

public class FileDataReader {
	BufferedReader br;
	public FileDataReader(File file) {
	    try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<GuestEntityData> read() throws IOException {
		ArrayList<GuestEntityData> dss = new ArrayList<>();
		String line;
		String[] params;
		while(br.ready()) {
			line = br.readLine().replaceAll("\\s{2,}"," ");
			params = line.split(" ");
			if(params.length != 3) {
				throw new IOException("Falsch formattierter Inhalt");
			}
			dss.add(new GuestEntityData(params[0], Time.timeToInt(params[1]), Integer.parseInt(params[2])));
		}
		return dss;
	}
}
