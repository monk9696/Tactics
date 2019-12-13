package map;

import java.util.ArrayList;
import java.util.Random;

public class Map {

	private Node[][] map;
	private ArrayList<Point> units = new ArrayList<Point>();
	
	public Map(){
		map = new Node[9][9];
		units.add(new Point(0,0));
		units.add(new Point(1,1));
		units.add(new Point(4,4));
		units.add(new Point(1,2));
		generateMap();
	}
	
	public Map(ArrayList<Point> units) {
		map = new Node[9][9];
		this.units = units;
		generateMap();
	}
	
	
	public void generateMap(){
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map[y].length; x++){
				if(y<map.length && x < map[y].length){
					map[y][x] = generateTile(y,x);
					if(map[y][x].getDepth() > 9){
						map[y][x].setDepth(9);
					}else if(map[y][x].getDepth() < 0){
						map[y][x].setDepth(0);
					}
				}
			}
		}
		linkMap();
	}
	
	public Node generateTile(int y, int x){
		Random rand = new Random();
		int p = 1;
		
		if(y > 0 && x > 0){
			int tempx = map[y][x-1].getDepth();
			int tempy = map[y-1][x].getDepth();
			return new Node(y,x,((tempx+tempy)/2)+rand.nextInt(1+(2*p))-p);
		}else if (x > 0){
			int temp = map[y][x-1].getDepth();
			return new Node(y,x,temp+rand.nextInt(1+(2*p))-p);
		}else if (y > 0){
			int temp = map[y-1][x].getDepth();		
			return new Node(y,x,temp+rand.nextInt(1+(2*p))-p);
		}else{
			return new Node(y,x);
		}
	}
	
	public void linkMap() {
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[y].length; x++) {
				if(x > 0) {
					map[y][x].addAdjacent(map[y][x-1]);
				}
				if(y > 0) {
					map[y][x].addAdjacent(map[y-1][x]);
				}
				if(y < map.length-1) {
					map[y][x].addAdjacent(map[y+1][x]);
				}
				if(x < map[y].length-1) {
					map[y][x].addAdjacent(map[y][x+1]);
				}
			}
			
		}
	}
	
	public void printMap(){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j<map[i].length; j++){
				System.out.print(map[j][i].getDepth());
			}
			System.out.println();
		}
	}
	
	public void showRange(Point unit){
		showRange(unit.getY(), unit.getX());
	}
	
	public void showRange(int y, int x){
		int depth = map[y][x].getDepth();
		System.out.println(depth);
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(i == x && j == y){
					System.out.print("P");
				}else if((Math.abs(j - y) + Math.abs(i - x) + (map[j][i].getDepth() - depth) - 2) <= 0){
					if(units.contains(new Point(j,i))){
						System.out.print("X");
					}else{
						System.out.print("O");
					}
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}	
	public void printAdjacent() {
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j].printAdjacent();
			}
		}
	}
	
	public void printRange() {
		for(Point unit: units){
			System.out.println();
			showRange(unit);
		}

	}
}


