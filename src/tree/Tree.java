package tree;

import data.DataStructureInterface;
import util.Time;

public class Tree<T extends DataStructureInterface> {
	TreeNode<T> root;
	public Tree() {
		root = new TreeNode<T>(null, Time.ZERO, 0);
	}
}
