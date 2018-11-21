package tree;

import java.util.ArrayList;
import java.util.List;

import data.DataStructureInterface;
import util.Time;


public class TreeNode<T extends DataStructureInterface> {

	private ArrayList<TreeNode<T>> children;
	private TreeNode<T> parent;
	private T value;
	private int fCost;
	//Zeit nach abschluss(Anfang + duration) NO, SCHAU UNTERE ZEILE
    //Zeit zu der sich die Person in das Bad bewegen könnte (zu der das Flugzeug starten könnte)
	private Time currentTime;

	private List<T> ancestors;

    /**
     * Erstellt ein neues TreeNode Objekt mit einer DataStructure als Inhalt. Alle Hier gesetzten Attribute können im nachhinein NICHT geändert werden. Sie sind also quasi final.
     * @param value : Den Datensatz den die TreeNode beinhalten soll.
     * @param currentTime : Die Zeit, zu der die Person ins Bad darf. (NICHT die Zeit, zu der er fertig ist)
     *                    Für die currentTime eines Kindes gilt: child.currentTime = Time.add(this.currentTime,this.value.getDuration())
     *                    also die Zeit zu der der Eltern-Knoten aus dem Bad kommt.
     * @param fCost : die Gesamtkosten die bei der Traversierung bis zu diesem (einschließlich diesem) Knoten anfallen/angefallen sind.
     *              Für die fCost eines Kindes gilt: child.fCost = this.fCost + Time.deltaTime(child.currentTime,child.value.getTime()
     */
	public TreeNode(TreeNode<T> parent, T value,Time currentTime, int fCost) {
	    this.children = new ArrayList<TreeNode<T>>();
	    this.parent = parent;
		this.value = value;
		this.currentTime = currentTime;
		this.fCost = fCost;
        ancestors = getAllAncestors();
	}

    /**
     * Fügt ein neies Kind mit dem Datensatz childData ein. Das Kind wird nur eingefügt, wenn der Datensatz auf diesem Pfad noch nicht vorhanden ist.
     * Es gilt: getAllAncestors.contains(childData) == False
     * @param childData : Der Datensatz der im Kind gespeichert werden soll.
     */
	public TreeNode<T> add (T childData) {
	    if(!ancestors.contains(childData)) {
            Time childCurrentTime = Time.add(currentTime, value.getDuration());
            TreeNode<T> child;
            if (childData.getTime().compareTo(childCurrentTime) <= 0) { //child.getTime() < cchildCurrentTime
                child = new TreeNode<T>(this, childData, childCurrentTime, fCost + Time.deltaTime(childData.getTime(), childCurrentTime).toCost());
            } else {
                child = new TreeNode<T>(this, childData, childData.getTime(), fCost);
            }
            children.add(child);
            return child;
        }
        return null;
	}

    public List<TreeNode<T>> expand(List<T> childDatas) {
        ArrayList<TreeNode<T>> children = new ArrayList<TreeNode<T>>();
        TreeNode<T> child;
        for(T childData: childDatas) {
            child = add(childData);
            if(child != null)
                children.add(child);
        }
        return children;
    }

    /**
     * Gibt eines Liste aller T-Objekte von (inklusive) diesem Knoten bis zum root-Knoten zurück.
     * @return
     */
    private List<T> getAllAncestors() {
        ArrayList<T> ancestors = new ArrayList<T>();
        ancestors.add(this.value);
        TreeNode<T> ancestor = parent;
        while(ancestor != null) {
            ancestors.add(ancestor.getValue());
            ancestor = ancestor.parent;
        }
        return ancestors;
    }

    public void iterate(List<TreeNode<T>> list) {
        list.add(this);
        for(TreeNode<T> child:children) {
            child.iterate(list);
        }
    }

	public boolean isLeaf() {
		return children.size() <= 0 ? true : false;
	}


	//Getters
    public ArrayList<TreeNode<T>> getChildren() {
        return children;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public T getValue() {
        return value;
    }

    public int getfCost() {
        return fCost;
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    @Override
    public String toString() {
        String r = "{" + value.toString() + "|" + currentTime + "," + fCost + "-->" + children.toString() + "}";
        return r;
    }
}
