package util;
import data.DataStructureInterface;
import data.GuestData;
import tree.Tree;
import tree.TreeNode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		FileDataReader fdr = new FileDataReader(new File("D://Uni//C++//AStar-Helmke//Badbelegungsplanung2"));
		List<GuestData> dsis = fdr.read();

		System.out.println(dsis.toString());
		Tree<GuestData> t = new Tree<GuestData>(dsis);

		TreeNode<GuestData> minNode = t.expandAll();
        Stack<TreeNode<GuestData>> path = t.getPath(minNode);
        System.out.println(path.size());
		for(TreeNode<GuestData> p: path) {
		    System.out.println(p.getValue().getName());
        }
		//System.out.println(t.getRoot().toString());
	}

}
