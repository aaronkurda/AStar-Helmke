package tree;
import java.util.*;

import tree.data.EntityData;

public class AStar<T extends EntityData> {
    public static final Node MAXIMUM_COST = new Node(null, null, 0, Integer.MAX_VALUE);

	private List<T> data;
	private Tree<T> tree;
	private Node<T> MinLeaf;
	
	public AStar(List<T> data) {
	    data.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(o1.getTime() < o2.getTime()) {
                    return -1;
                } else if(o1.getTime() > o2.getTime()) {
                    return 1;
                }
                return 0;
            }
        });
		this.data = data;
		tree = new Tree<>(data);
		lazyExpand();
	}

	public void lazyExpand() {
        List<Node<T>> knoten = new ArrayList<>();
        knoten.addAll(tree.getRoot().expand(data));

        Node<T> currentMin = MAXIMUM_COST;
        int tiefe = 2;
        while(!knoten.isEmpty()) {
            knoten = expandAll(knoten);
            knoten.sort(new Comparator<Node<T>>() {
                @Override
                public int compare(Node<T> o1, Node<T> o2) {
                    if(o1.getfCost() < o2.getfCost()) {
                        return -1;
                    } else if(o1.getfCost() > o2.getfCost()) {
                        return 1;
                    }
                    return 0;
                }
            });
            knoten.subList(data.size() * (data.size() - 1) -1, knoten.size()-1).clear();
            tiefe *= 2;
            currentMin = knoten.get(0);
            if(currentMin.getParent().getChildren().size() == 1) {
                MinLeaf = currentMin;
                return;
            }
        }
        return;
    }

    private List<Node<T>> expandAll(List<Node<T>> knoten) {
        List<Node<T>> children = new ArrayList<>();
	    for(Node<T> t: knoten) {
	        children.addAll(t.expand(data));
        }
        return children;
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
        Node<T> currentMinLeaf =MinLeaf;
        for(Node<T> leaf: leafs) {
            if(currentMinLeaf.getfCost() > leaf.getfCost()) {
                currentMinLeaf = leaf;
            }
        }
        return currentMinLeaf;
    }

    public List<Node<T>> getMinCostPath() {
	    return tree.getPathToRoot(MinLeaf);
    }

    @Override
    public String toString() {
	    return tree.toString() + "\n" + getMinCostPath();

    }
}
