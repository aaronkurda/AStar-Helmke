package refactor.astar.guest.tree;

import refactor.astar.guest.data.GuestData;

import java.util.ArrayList;
import java.util.List;

public class GuestNode {
    GuestData entityData;
    List<GuestNode> children;
    GuestNode parent;
    int currentTime;
    int fCost;

    public GuestNode(GuestData entityData, GuestNode parent, int parentFinishTime, int parentFCost) {
        this.entityData = entityData;
        this.children = new ArrayList<>();
        this.parent = parent;
        if(parentFinishTime > this.entityData.getTime()) {
            this.currentTime = parentFinishTime;
            this.fCost = parentFCost + (parentFinishTime - this.entityData.getTime());
        } else {
            this.currentTime = this.entityData.getTime();
            this.fCost = parentFCost;
        }
    }

    public void addChild(GuestNode node) {
        this.children.add(node);
    }

    public void expand(List<GuestData> entityDatas) {
        List<GuestData> bloodLine = getBloodline();
        boolean skipEntity = false;
        for(GuestData ed : entityDatas) {
            for(GuestNode child: children) {
                if(child.entityData == ed) {
                    skipEntity = true;
                }
            }
            if(!bloodLine.contains(ed) && skipEntity == false) {
                this.addChild(new GuestNode(ed, this, this.currentTime + this.entityData.getDuration(), this.fCost));
            }
            skipEntity = false;
        }
    }

    public void isolate() {
        this.removeAllChildren();
        this.removeParent();
    }

    public void removeAllChildren() {
        this.children = new ArrayList<>();
    }

    public void removeParent() {
        this.parent = null;
    }

    public List<GuestNode> getChildren() {
        return children;
    }

    public int getFCost() {
        return fCost;
    }

    public boolean isLeaf() {
        if(this.parent != null && this.parent.getChildren().size() <= 1) {
            return true;
        }
        return false;
    }

    public GuestData getEntityData() {
        return entityData;
    }

    public List<GuestData> getBloodline() {
        List<GuestData> bloodline = new ArrayList<>();
        GuestNode current = this;
        while(current != null) {
            bloodline.add(current.entityData);
            current = current.parent;
        }
        return bloodline;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    @Override
    public String toString() {
        return "[" + this.entityData.toString() + "|" + this.currentTime + "," + this.fCost + "]";
    }

    public String subtreeToString() {
        String r = this.toString();
        if(children.size() >= 1) {
            r += "-->{";
        }
        for(GuestNode gn: children) {
            r += gn.subtreeToString();
        }
        r+= "}";
        return r;
    }

}
