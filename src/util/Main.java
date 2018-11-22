package util;
import data.DataStructureInterface;
import data.GuestData;
import tree.Tree;
import tree.TreeNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		FileDataReader fdr = new FileDataReader(new File("D://Uni//C++//AStar-Helmke//Badbelegungsplanung2"));
		List<GuestData> dsis = fdr.read();

		System.out.println(dsis.toString());
		Tree<GuestData> t = new Tree<GuestData>(dsis);

		t.expandAll();
		TreeNode<DataStructureInterface> penis;
		List<TreeNode<GuestData>> list = t.toList();
		TreeNode<GuestData> minNode = null;
		int minCost = Integer.MAX_VALUE;
		for(TreeNode<GuestData> node: list) {
		    if(node.isLeaf() && node.getfCost() < minCost) {
		        minCost = node.getfCost();
		        minNode = node;
            }
            //System.out.println(node.getValue().getName());
        }

        List<TreeNode<GuestData>> path = t.getPath(minNode);
		System.out.println(minNode.getfCost());
        for(TreeNode<GuestData> tn: path) {
            System.out.print(tn.getValue().getName() + "-->");
        }
	}

}
