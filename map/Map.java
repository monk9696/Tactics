package map;

import java.util.ArrayList;
import java.util.Random;

public class Map {

	private Node[][] map;
	private ArrayList<Point> units = new ArrayList<Point>();
	
	int[] count = {0,0,0};
	
	public Map(){
		map = new Node[5][5];
		units.add(new Point(0,0));
		units.add(new Point(1,1));
		units.add(new Point(4,4));
		units.add(new Point(1,2));
		
		setMap();
		
		generateMap(0,0,4,4);
	}
	
	public Map(ArrayList<Point> units) {
		map = new Node[9][9];
		this.units = units;
		generateMap();
	}
	
	public void setMap() {
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map[y].length; x++){
				map[y][x] = new Node(y,x);
			}
		}
		map[0][0] = new Node(0,0,1);
		map[0][4] = new Node(0,4,1);
		map[4][0] = new Node(4,0,8);
		map[4][4] = new Node(4,4,8);
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
		System.out.println(count[0] + " " + count[1] + " " + count[2]);
	}
	
	
	public void generateMap(int yMin,int xMin, int yMax, int xMax) {

		int yMid = yMin + ((yMax - yMin)/2);
		int xMid = xMin + ((xMax - xMin)/2);
		
		map[yMid][xMid].setDepth((map[yMin][xMin].getDepth() + map[yMax][xMin].getDepth() + 
				map[yMin][xMax].getDepth() + map[yMax][xMax].getDepth())/4);
		
		map[yMin][xMid].setDepth((map[yMid][xMid].getDepth() + 
				map[yMin][xMin].getDepth() + map[yMin][xMax].getDepth())/3);

		map[yMax][xMid].setDepth((map[yMid][xMid].getDepth() + 
				map[yMax][xMin].getDepth() + map[yMax][xMax].getDepth())/3);
		
		map[yMid][xMin].setDepth((map[yMid][xMid].getDepth() + 
				map[yMin][xMin].getDepth() + map[yMin][xMax].getDepth())/3);
		
		map[yMid][xMax].setDepth((map[yMid][xMid].getDepth() + 
				map[yMax][xMin].getDepth() + map[yMax][xMax].getDepth())/3);
		
		if(yMax != 2 && xMax != 2 && yMin != 2 && xMin != 2) {
			generateMap(yMin, xMin, yMid, xMid);
			generateMap(yMin, xMid, yMid, xMax);
			generateMap(yMid, xMin, yMax, xMid);
			generateMap(yMid, xMid, yMax, xMax);
		}
	}
	
	
	
	public Node generateTile(int y, int x){
		Random rand = new Random();
		int p = 1;
		int tempo;
			
		
		if(y > 0 && x > 0){
			int tempx = map[y][x-1].getDepth();
			int tempy = map[y-1][x].getDepth();
			tempo = rand.nextInt(1+(2*p));
			count[tempo]++;
			return new Node(y,x,((tempx+tempy)/2)+tempo-p);
		}else if (x > 0){
			int temp = map[y][x-1].getDepth();
			tempo = rand.nextInt(1+(2*p));
			count[tempo]++;
			return new Node(y,x,temp+tempo-p);
		}else if (y > 0){
			int temp = map[y-1][x].getDepth();
			tempo = rand.nextInt(1+(2*p));
			count[tempo]++;
			return new Node(y,x,temp+tempo-p);
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
				}else if((Math.abs(j - y) + Math.abs(i - x) + (map[j][i].getDepth() - depth) - 3) <= 0){
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


