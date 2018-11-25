package old.tree;
import java.util.*;

import old.tree.data.EntityData;

public class AStar<T extends EntityData> {
    public static final Node MAXIMUM_COST = new Node(null, null, 0, Integer.MAX_VALUE);

	private List<T> entities;

	private Tree<T> tree;

	private List<T> openSet;
    private List<T> closedSet;
    private List<Node<T>> path;

    private Node<T> minLeaf;
	
	public AStar(final List<T> data) {
        this.entities = data;
	    entities.sort(new Comparator<T>() {
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

		tree = new Tree<>();

		openSet = new ArrayList<>();
		for(T t: entities) {
		    openSet.add(t);
        }   //Copied all Elements from entities to openSet

        closedSet = new ArrayList<>();
		path = new ArrayList<>();
		//fracExpand(11,2);
	}

	public List<Node<T>> fracExpand(int n, int k) {
        for(int i = 0; i < openSet.size() && i < n; i++) {
            closedSet.add(openSet.get(i));
            openSet.remove(i);
        }
        while(!closedSet.isEmpty()) {
            Node<T> currentMin = fracLazyExpand(closedSet);
            List<Node<T>> pathFraction = tree.getNodePathToRoot(currentMin);
            for(int i = 0; i < pathFraction.size() && i < k; i++) {
                if(pathFraction.get(i) != currentMin) {
                    pathFraction.get(i).isolate();
                }
                path.add(pathFraction.get(i));
                closedSet.remove(pathFraction.get(i).getValue());
                if(openSet.size() > 0) {
                    closedSet.add(openSet.get(0));
                    openSet.remove(0);
                }
            }
            tree.setNewRoot(path.get(path.size() - 1));
            currentMin.unsetAsChild();
            currentMin.unsetParent();
        }
        return path;
    }

    public Node<T> fracLazyExpand(List<T> closedSet) {
        List<Node<T>> knoten = new ArrayList<>();
        knoten.addAll(tree.getRoot().expand(closedSet));
        Node<T> currentMin = MAXIMUM_COST;
        while(!knoten.isEmpty()) {
            knoten = fracExpandAll(knoten,closedSet);
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
            System.out.println( closedSet.size() * (closedSet.size() - 1) -1);
            knoten.subList(closedSet.size() * (closedSet.size() - 1) -1 , knoten.size()-1).clear();
            currentMin = knoten.get(0);
            if(currentMin.getParent().getChildren().size() == 1) {
                return currentMin;
            }
        }
        return null;
    }

	public void lazyExpand() {
        List<Node<T>> knoten = new ArrayList<>();
        knoten.addAll(tree.getRoot().expand(entities));

        Node<T> currentMin = MAXIMUM_COST;
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
            knoten.subList(entities.size() * (entities.size() - 1) -1, knoten.size()-1).clear();
            currentMin = knoten.get(0);
            if(currentMin.getParent().getChildren().size() == 1) {
                minLeaf = currentMin;
                return;
            }
        }
        return;
    }

    private List<Node<T>> expandAll(List<Node<T>> knoten) {
        List<Node<T>> children = new ArrayList<>();
	    for(Node<T> t: knoten) {
	        children.addAll(t.expand(entities));
        }
        return children;
    }

    private List<Node<T>> fracExpandAll(List<Node<T>> knoten, List<T> closedList) {
        List<Node<T>> children = new ArrayList<>();
        for(Node<T> t: knoten) {
            children.addAll(t.expand(closedList));
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

    public Tree<T> getTree() {
	    return tree;
    }
    public List<Node<T>> getMinCostPath() {
	    return tree.getNodePathToRoot(minLeaf);
    }

    @Override
    public String toString() {
	    return tree.toString() + "\n" + getMinCostPath();

    }
}
