package util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import data.GuestData;

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

	public ArrayList<GuestData> read() throws IOException {
		ArrayList<GuestData> dss = new ArrayList<>();
		String line;
		String[] params;
		while(br.ready()) {
			line = br.readLine();
			params = line.split(" ");
			if(params.length != 3) {
				throw new IOException("Falsch formattierter Inhalt");
			}
			dss.add(new GuestData(params[0],Time.fromString(params[1]), Time.fromString(params[2])));
		}
		return dss;
	}
}
