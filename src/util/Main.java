package util;
import data.DataStructureInterface;
import data.GuestData;
import tree.Tree;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		FileDataReader fdr = new FileDataReader(new File("D://Uni//C++//AStar-Helmke//Badbelegungsplanung2"));
		List<GuestData> dsis = fdr.read();

		System.out.println(dsis.toString());
		Tree<GuestData> t = new Tree<GuestData>(dsis);
		t.expandAll();
		System.out.println(t.getRoot().toString());
	}

}
