package tree;

import data.DataStructureInterface;
import data.GuestData;
import util.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree<T extends DataStructureInterface> {
    List<T> guestDatas;
	TreeNode<T> root;
	public Tree(List<T> guestDatas) {
	    this.guestDatas = guestDatas;
		root = new TreeNode<T>(null, (T) GuestData.ROOT, Time.ZERO, 0);
	}

    public TreeNode<T> getRoot() {
	    return root;
    }

    public TreeNode<T> expandAll() {
        Stack<TreeNode<T>> openStack = new Stack<>();
	    openStack.add(root);
        TreeNode<T> minTreeNode = root;
        List<TreeNode<T>> expandedNodes;
        TreeNode<T> currentNode;
        while(!openStack.isEmpty()) {
            currentNode = openStack.pop();
            if(currentNode.getfCost() < minTreeNode.getfCost()) {
                minTreeNode = currentNode;
            }
            expandedNodes = currentNode.expand(guestDatas);
            openStack.addAll(expandedNodes);
        }

        return minTreeNode;
    }

    public List<TreeNode<T>> toList() {
	    ArrayList<TreeNode<T>> list = new ArrayList<>();
	    root.iterate(list);
	    return list;
    }

    public Stack<TreeNode<T>> getPath(TreeNode<T> goal) {
	    Stack<TreeNode<T>> path = new Stack<>();
	    path.push(goal);
        TreeNode<T> current = path.peek().getParent();
	    while(current != null) {
            path.push(current);
            current = current.getParent();
        }
        return path;
    }

    @Override
    public String toString() {
	    return root.toString();
    }


}
