package tree;

import java.util.ArrayList;
import java.util.List;

import tree.data.EntityData;
import util.Time;


/**
 * Ein Node-Objekt eines Baums. Das Node Objekt besteht aus statischen Daten (T value) die das Objekt der realen Welt repräsentieren.
 * Kontextsensitive Daten wie fCost und currentTime werden direkt im Node Objekt verwaltet deren Werte von der Position der Node im Baum abhängig sind.
 * fCost und currentTime werden bei der Definition einer Node vom parent errechnet und können danach nicht mehr geändert werden.
 * @param <T> : Die Daten die im TreeObjekt gespeichert werden sollen.
 */
public class Node<T extends EntityData> {
	private ArrayList<Node<T>> children;
	private Node<T> parent;
	private T value;
	private int fCost;
	//Zeit nach abschluss(Anfang + duration) NO, SCHAU UNTERE ZEILE
    //Zeit zu der sich die Person in das Bad bewegen könnte (zu der das Flugzeug starten könnte)
	private Time currentTime;

	private List<T> ancestors;

    /**
     * Erstellt ein neues Node Objekt mit einer DataStructure als Inhalt. Alle Hier gesetzten Attribute können im nachhinein NICHT geändert werden. Sie sind also quasi final.
     * @param value : Den Datensatz den die Node beinhalten soll.
     * @param currentTime : Die Zeit, zu der die Person ins Bad darf. (NICHT die Zeit, zu der er fertig ist)
     *                    Für die currentTime eines Kindes gilt: child.currentTime = Time.add(this.currentTime,this.value.getDuration())
     *                    also die Zeit zu der der Eltern-Knoten aus dem Bad kommt.
     * @param fCost : die Gesamtkosten die bei der Traversierung bis zu diesem (einschließlich diesem) Knoten anfallen/angefallen sind.
     *              Für die fCost eines Kindes gilt: child.fCost = this.fCost + Time.deltaTime(child.currentTime,child.value.getTime()
     */
	Node(Node<T> parent, T value, Time currentTime, int fCost) {
	    this.children = new ArrayList<>();
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
    private Node<T> add (T childData) {
	    if(!ancestors.contains(childData)) {
            Time childCurrentTime = Time.add(currentTime, value.getDuration());
            Node<T> child;
            if (childData.getTime().compareTo(childCurrentTime) <= 0) { //child.getTime() < cchildCurrentTime
                child = new Node<>(this, childData, childCurrentTime, fCost + Time.deltaTime(childData.getTime(), childCurrentTime).toCost());
            } else {
                child = new Node<>(this, childData, childData.getTime(), fCost);
            }
            children.add(child);
            return child;
        }
        return null;
	}

    List<Node<T>> expand(List<T> childDatas) {
        ArrayList<Node<T>> children = new ArrayList<>();
        Node<T> child;
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
        ArrayList<T> ancestors = new ArrayList<>();
        ancestors.add(this.value);
        Node<T> ancestor = parent;
        while(ancestor != null) {
            ancestors.add(ancestor.getValue());
            ancestor = ancestor.parent;
        }
        return ancestors;
    }

    void iterate(List<Node<T>> list) {
        list.add(this);
        for(Node<T> child:children) {
            child.iterate(list);
        }
    }

	boolean isLeaf() {
		return children.size() <= 0;
	}

	//Getters
    public ArrayList<Node<T>> getChildren() {
        return children;
    }

    Node<T> getParent() {
        return parent;
    }

    T getValue() {
        return value;
    }

    int getfCost() {
        return fCost;
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    @Override
    public String toString() {
        return value.toString() + "|" + currentTime + "," + fCost;
    }

    String subtreeToString() {
        String r = "{" + value.toString() + "|" + currentTime + "," + fCost + "-->[";
        for(Node<T> child: children) {
            r += child.subtreeToString();
        }
        r+= "]}";
        return r;
    }
}
