package tree;

import data.DataStructureInterface;
import data.GuestData;
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
 * Ein T-Objekt muss das DataStructureInterface implementieren welches die Grundfunktionen bereit stellt, die für die Kostenberechnung eines Pfades erforderlich sind.
 * Diese beinhalten: die frühst mögliche Startzeit (Badproblem: Aufstehzeit), die Dauer der aktivität (benötigte Zeit im Bad)
 *
 * @param <T>
 */
public class Tree<T extends DataStructureInterface> {
    List<T> entities;
	TreeNode<T> root;
	public Tree(final List<T> entities) {
	    this.entities = entities;
		root = new TreeNode<T>(null,(T) GuestData.ROOT, Time.ZERO, 0);
	}

    public TreeNode<T> getRoot() {
	    return root;
    }

    /**
     * Expandiert alle Knoten des Baumes. Folgendes gilt zur komplexität des Baumes:
     * Anzahl der TreeNodes im Baum = entities.size!
     * Höhe des Baumes = guestData.size
     */
    public void expandAll() {
        Stack<TreeNode<T>> openStack = new Stack<>();
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

    /**
     * Konvertiert den Baum in eine Liste von TreeNodes. Die Liste ist nicht sortiert.
     * @return Alle Elemente des Baumes als Liste von TreeNodes
     */
    public List<TreeNode<T>> toList() {
        ArrayList<TreeNode<T>> list = new ArrayList<>();
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
