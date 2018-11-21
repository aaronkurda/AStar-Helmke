package tree;

import data.DataStructureInterface;
import data.GuestData;
import util.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree<T extends DataStructureInterface> {
    List<T> guestDatas;
	TreeNode root;
	public Tree(List<T> guestDatas) {
	    this.guestDatas = guestDatas;
		root = new TreeNode<T>(null, (T) DataStructureInterface.ROOT, Time.ZERO, 0);
	}

    public TreeNode<T> getRoot() {
	    return root;
    }

    public void expandAll() {
        Stack<TreeNode<T>> openStack = new Stack<>();
        List<TreeNode<T>> openList = new ArrayList<>();
	    openStack.add(root);
        List<TreeNode<T>> expandedNodes;
        while(!openStack.isEmpty()) {
            expandedNodes = openStack.pop().expand(guestDatas);
            openStack.addAll(expandedNodes);
        }
    }

    @Override
    public String toString() {
	    return root.toString();
    }


}
