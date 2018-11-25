package refactor.main;

import refactor.astar.guest.data.GuestData;

import java.io.*;
import java.util.ArrayList;

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

	public GuestData[] read() throws IOException {
		ArrayList<GuestData> dss = new ArrayList<>();
		String line;
		String[] params;
		while(br.ready()) {
			line = br.readLine().replaceAll("\\s{2,}"," ");
			params = line.split(" ");
			if(params.length != 3) {
				throw new IOException("Falsch formattierter Inhalt");
			}
			dss.add(new GuestData(params[0], Time.timeToInt(params[1]), Integer.parseInt(params[2])));
		}
		GuestData[] r = new GuestData[dss.size()];
		dss.toArray(r);
		return r;
	}
}
