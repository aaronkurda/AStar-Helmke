package refactor.astar.guest.tree;

import refactor.astar.guest.data.GuestData;

import java.util.ArrayList;
import java.util.List;

public class GuestTree {
    GuestNode root;

    public GuestTree() {
        this.root = new GuestNode(new GuestData("ROOT",0,0),null, 0 ,0);
    }

    public void expand(List<GuestData> entityList) {
        root.expand(entityList);
    }

    public List<GuestNode> getChildren() {
        return root.children;
    }

    public void setRoot(GuestNode newRoot) {
        for(GuestNode child: root.children) {
            child.parent = null;
        }
        if(root.parent != null) {
            root.parent.children.remove(this);
        }
        root.children = null;
        root.parent = null;
        root = newRoot;
        if(newRoot.parent != null)
            newRoot.parent.children.remove(newRoot);
        newRoot.parent = null;
    }

    public GuestNode getRoot() {
        return  root;
    }

    public List<GuestNode> getPathExcludingRoot(GuestNode node) {
        List<GuestNode> path = new ArrayList<>();
        GuestNode current = node;
        while(current != null && current != root) {
            path.add(0,current);
            current = current.parent;
        }
        return path;
    }

    public List<GuestNode> getPathIncludeingRoot(GuestNode node) {
        List<GuestNode> path = new ArrayList<>();
        GuestNode current = node;
        while(current != null) {
            path.add(0,current);
            current = current.parent;
        }
        return path;
    }
}
