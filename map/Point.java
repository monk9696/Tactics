package map;
import java.util.Random;


public class Point {

	private int xCod;
	private int yCod;
	Random rand = new Random();
	
	
	public Point(int y, int x){
		xCod = x;
		yCod = y;
	}
	
	public void randPoint(int xbound, int ybound){
		xCod = rand.nextInt(xbound+1);
		yCod = rand.nextInt(ybound+1);
	}
	
	public String toString(){
		return "(" + xCod + " ," + yCod + ")";
	}
	
	public void shift(int x, int y){
		xCod += x;
		yCod += y;
	}
		
	public int getX(){
		return xCod;
	}
	
	public int getY(){
		return yCod;
	}
		
	public boolean equals(Object j) {
		if (j instanceof Point){
			Point p = (Point) j;
			return ((p.getX() == getX()) && (p.getY() == getY()));
		}
		return false;
	}
}
