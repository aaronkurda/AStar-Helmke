package tree;
import java.util.List;

import tree.data.EntityData;

public class AStar<T extends EntityData> {
	private List<T> data;
	private Tree<T> tree;
	
	public AStar(List<T> data) {
		this.data = data;
		tree = new Tree<>(data);
		tree.expandAll();
	}

	public List<Node<T>> getAllNodes() {
	    return tree.toList();
    }

    public List<Node<T>> getLeafNodes() {
        List<Node<T>> nodes = getAllNodes();
        for(int i = nodes.size() - 1; i >= 0; i--) {
            if(!nodes.get(i).isLeaf()) {
                nodes.remove(i);
            }
        }
        return nodes;
    }

    public Node<T> getMinCostLeafNode() {
        List<Node<T>> leafs = getLeafNodes();
        Node<T> currentMinLeaf = leafs.get(0);
        for(Node<T> leaf: leafs) {
            if(currentMinLeaf.getfCost() > leaf.getfCost()) {
                currentMinLeaf = leaf;
            }
        }
        return currentMinLeaf;
    }

    public List<Node<T>> getMinCostPath() {
	    return tree.getPathToRoot(getMinCostLeafNode());
    }

    @Override
    public String toString() {
	    return tree.toString() + "\n" + getMinCostPath();

    }
}
