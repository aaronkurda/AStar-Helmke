package refactor.astar.guest;

import old.tree.Tree;
import refactor.astar.guest.data.GuestData;
import refactor.astar.guest.tree.GuestNode;
import refactor.astar.guest.tree.GuestTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GuestAStar {
    GuestData[] allEntities;
    List<GuestData> openList;
    List<GuestData> closedList;
    public List<GuestNode> path;

    GuestTree tree;

    public GuestAStar(final GuestData[] guestDatas) {
        allEntities = guestDatas;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        path = new ArrayList<>();

        tree = new GuestTree();
    }

    public void tsExpand(int take, int select) {
        for(GuestData gd: allEntities) {
            openList.add(gd);
        }
        openList.sort(new Comparator<GuestData>() {
            @Override
            public int compare(GuestData o1, GuestData o2) {
                if(o1.getTime() < o2.getTime()) {
                    return -1;
                } else if(o1.getTime() > o2.getTime()) {
                    return 1;
                }
                return 0;
            }
        });

        for(int i = 0; i < openList.size() && i < take; i++) {
            closedList.add(openList.get(i));
        }
        openList.removeAll(closedList);

        while(!closedList.isEmpty()) {
            System.out.println(closedList);
            //GuestNode minimum = this.lazyGreedyExpand(closedList);
            GuestNode minimum = this.lazyCutExpand(closedList);
            List<GuestNode> pathFraction = tree.getPathExcludingRoot(minimum);
            for(int i = 0; i < select && i < pathFraction.size(); i++) {
                GuestNode temp = pathFraction.get(i);
                if(temp != pathFraction.get(select < pathFraction.size() ? select: pathFraction.size() - 1)) {
                    temp.isolate();
                }
                this.path.add(temp);
                closedList.remove(temp.getEntityData());
                if(!openList.isEmpty()) {
                    closedList.add(openList.get(0));
                    openList.remove(0);
                }
            }
            GuestNode temp = path.get(path.size() - 1);
            //this.tree.setRoot(new GuestNode(temp.getEntityData(),null, temp.getCurrentTime(),temp.getFCost()));
            this.tree.setRoot(path.get(path.size() - 1));
        }
    }

    public GuestNode lazyGreedyExpand(List<GuestData> closedList) {
        List<GuestNode> nodesToExpand = new ArrayList<>();
        nodesToExpand.add(tree.getRoot());
        GuestNode current = null;
        while(!nodesToExpand.isEmpty()) {
            System.out.println(nodesToExpand.size());
            nodesToExpand.sort(new Comparator<GuestNode>() {
                @Override
                public int compare(GuestNode o1, GuestNode o2) {
                    if(o1.getFCost() < o2.getFCost()) {
                        return -1;
                    } else if(o1.getFCost() > o2.getFCost()) {
                        return 1;
                    }
                    return 0;
                }
            });
            current = nodesToExpand.get(0);
            nodesToExpand.remove(current);
            current.expand(closedList);
            nodesToExpand.addAll(current.getChildren());
            if(current.isLeaf()) {
                return current;
            }
        }
        return null;
    }

    public GuestNode lazyCutExpand(List<GuestData> closedList) {
        List<GuestNode> currentDepth = new ArrayList<>();
        currentDepth.add(tree.getRoot());
        while(!currentDepth.isEmpty()) {
            currentDepth = expandAll(currentDepth, closedList);
            currentDepth.sort(new Comparator<GuestNode>() {
                @Override
                public int compare(GuestNode o1, GuestNode o2) {
                    if(o1.getFCost() < o2.getFCost()) {
                        return -1;
                    } else if(o1.getFCost() > o2.getFCost()) {
                        return 1;
                    }
                    return 0;
                }
            });
            int n = getON(closedList.size());
            n /= 4;
            n = (n < Math.pow(closedList.size(),6)) ? (int) Math.pow(closedList.size(),6) : n;
            n = (n >= currentDepth.size() - 1) ? currentDepth.size() - 1 : n;
            System.out.println(n);
            currentDepth.subList(n,currentDepth.size() - 1).clear();
            if(currentDepth.get(0).isLeaf()) {
                return currentDepth.get(0);
            }
        }
        return null;
    }

    public List<GuestNode> expandAll(List<GuestNode> expand,List<GuestData> closedList) {
        List<GuestNode> r = new ArrayList<>();
        for(GuestNode gn: expand) {
            gn.expand(closedList);
            r.addAll(gn.getChildren());
        }
        return r;
    }

    public List<GuestNode> getPathExcludingRoot(GuestNode node) {
        return tree.getPathExcludingRoot(node);
    }

    public List<GuestNode> getPathIncludeingRoot(GuestNode node) {
        return tree.getPathIncludeingRoot(node);
    }


    public static int getON(int n) {
        int r = 0;
        for(int i = 1; i < n; i++) {
            r += i;
        }
        return r;


    }

}
