package tree;

import java.util.ArrayList;

import data.DataStructureInterface;
import util.Time;

public class TreeNode<T extends DataStructureInterface> {
	ArrayList<TreeNode> children;
	T value;
	int fCost;
	//Zeit nach abschluss(Anfang + duration)
	Time currentTime;
	
	public TreeNode(T value,Time currentTime, int fCost) { 
		this.value = value;
	}
	
	public int getFCost(int fCost) {
		return fCost;
	}
	
	public T getValue() {
		return value;
	}
	
	public void add (T childData) {
		Time childCurrentTime = Time.add(currentTime, value.getDuration());
		TreeNode<T> child;
		if(childData.getTime().compareTo(currentTime) == -1) {
			child = new TreeNode<T>(childData, childCurrentTime, fCost + Time.deltaTime(childData.getTime(),currentTime).toCost());
		} else {
			child = new TreeNode<T>(childData, childCurrentTime, fCost);
		}
		children.add(child);
	}
	
	public boolean isLeaf() {
		return children.size() <= 0 ? true : false;
	}
}
