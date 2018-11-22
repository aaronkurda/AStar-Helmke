package tree;

import tree.data.EntityData;
import tree.data.GuestEntityData;
import util.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Ein Baum zur Darstellung aller kombinatorischen Reihenfolgen einer Liste von Entitys.
 * Die Datenstruktur hält eine Liste von Stammdaten zu n Objekten und generiert aus diesen eine Liste nach dem Urnenmodell ohne zurücklegen.
 * Die Struktur des Baumes ähnelt die eines B-Baums. Jeder Knoten hat 0..n kinder, weopbei die Anzahl der Kinder mit zunehmender Tiefe des Knotens monoton abnimmt.
 * Jeder Kind-Knoten kennt ein Elternteil und ein Eltern-Knoten kennt 0..n Kinder. (bidirektional).
 * Auf einem Pfad von Knoten zur Worzel des Baumes befindet sich ein T-Entity nur ein mal. Ist der Knoten ein Blatt befindet sich jedes T-Entity genau ein mal auf diesem Pfad.
 * Die Anzahl der Knoten des vollständig expandierten Baumes beträgt n! wobei gilt: n = entity.size. Für die Höhe des Baumes gilt. h = n
 * Ein T-Objekt muss das EntityData implementieren welches die Grundfunktionen bereit stellt, die für die Kostenberechnung eines Pfades erforderlich sind.
 * Diese beinhalten: die frühst mögliche Startzeit (Badproblem: Aufstehzeit), die Dauer der aktivität (benötigte Zeit im Bad)
 *
 * @param <T>
 */
public class Tree<T extends EntityData> {
    private List<T> entities;
	private Node<T> root;
	Tree(final List<T> entities) {
	    this.entities = entities;
		root = new Node<T>(null,(T) GuestEntityData.ROOT, Time.ZERO, 0);
	}

    public Node<T> getRoot() {
	    return root;
    }

    /**
     * Expandiert alle Knoten des Baumes. Folgendes gilt zur komplexität des Baumes:
     * Anzahl der TreeNodes im Baum = entities.size!
     * Höhe des Baumes = guestData.size
     */
    void expandAll() {
        Stack<Node<T>> openStack = new Stack<>();
	    openStack.add(root);
        while(!openStack.isEmpty()) {
            openStack.addAll(openStack.pop().expand(entities));
        }
    }

    /**
     * Gibt alle parent-Knoten von goal bis zu ROOT aus.
     * @param goal : Der Startpunkt der Pfadberechnung.
     * @return Einen Pfad von goal zum root-Knoten des Baumes (und umgekehrt)
     */
    List<Node<T>> getPathToRoot(Node<T> goal) {
	    List<Node<T>> path = new ArrayList<>();
	    path.add(0,goal);
        Node<T> current = path.get(0).getParent();
	    while(current != null) {
            path.add(0,current);
            current = current.getParent();
        }
        return path;
    }

    /**
     * Konvertiert den Baum in eine Liste von TreeNodes. Die Liste ist nicht sortiert.
     * @return Alle Elemente des Baumes als Liste von TreeNodes
     */
    List<Node<T>> toList() {
        ArrayList<Node<T>> list = new ArrayList<>();
        root.iterate(list);
        return list;
    }

    /**
     * Gibt eine String repräsentation des Baums zurück.
     * @return eine String-repräsentation des Baums.
     */
    @Override
    public String toString() {
	    return root.subtreeToString();
    }




}
