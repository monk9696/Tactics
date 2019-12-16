package map;

import java.util.ArrayList;

public class Node {

	private int x;
	private int y;
	private ArrayList<Node> adjacent;
	private int depth;
	
	public Node() {
		depth = 0;
		x=0;
		y=0;
		adjacent = new ArrayList<Node>();
	}
	
	public Node(int y, int x){
		depth = 0;
		this.x = x;
		this.y = y;
		adjacent = new ArrayList<Node>();
	}

	public Node(int y, int x, int depth){
		this.depth = depth;
		this.x = x;
		this.y = y;
		adjacent = new ArrayList<Node>();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public void addAdjacent(Node n) {
		if(!adjacent.contains(n)) {
			adjacent.add(n);
			n.addAdjacent(this);
		}		
	}
	
	public void printAdjacent() {
		for(Node n: adjacent) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
	
	public Point getPoint() {
		return new Point(y,x);
	}
	
	public String toString() {
		return "x: " + getX() + " y: " + getY() + "-----";
	}
}
