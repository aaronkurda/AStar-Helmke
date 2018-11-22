package util;
import tree.AStar;
import tree.data.GuestEntityData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		FileDataReader fdr = new FileDataReader(new File("D://Uni//C++//AStar-Helmke//Badbelegungsplanung2"));
		List<GuestEntityData> dsis = fdr.read();

		/*System.out.println(dsis.toString());
		Tree<GuestEntityData> t = new Tree<GuestEntityData>(dsis);

		t.expandAll();
		List<Node<GuestEntityData>> list = t.toList();
		Node<GuestEntityData> minNode = null;
		int minCost = Integer.MAX_VALUE;
		for(Node<GuestEntityData> node: list) {
		    if(node.isLeaf() && node.getfCost() < minCost) {
		        minCost = node.getfCost();
		        minNode = node;
            }
            //System.out.println(node.getValue().getName());
        }

        System.out.println(t.toList());

        List<Node<GuestEntityData>> path = t.getPath(minNode);
		System.out.println(minNode.getfCost());
        for(Node<GuestEntityData> tn: path) {
            System.out.print(tn.getValue().getName() + "-->");
        }*/

		AStar<GuestEntityData> astar = new AStar<>(dsis);
		//System.out.println(astar.getMinCostLeafNode());
        System.out.println(astar.toString());
	}

}
