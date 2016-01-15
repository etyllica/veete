package br.com.vite.pathfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.map.Map;

/**
 * Based on: http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html
 */
public class AStar extends PathFinder implements Comparator<Cell> {

	public static final int DIAGONAL_COST = 10;
	public static final int V_H_COST = 10;

	public AStar(Map map) {
		super(map);
	}

	public void init() {
		closed = new boolean[rows][columns];
		open = new PriorityQueue<Cell>(16, this);
	}

	public PathContainer calculatePath(int startX, int startY, int endX, int endY) {
		Cell[][] grid = initGrid(startX, startY, endX, endY);
		List<PointInt2D> path = calculate(grid, startX, startY, endX, endY); 
		return new PathContainer(path, grid);
	}

	public List<PointInt2D> calculate(int startX, int startY, int endX, int endY) {
		Cell[][] grid = initGrid(startX, startY, endX, endY);
		return calculate(grid, startX, startY, endX, endY);
	}

	private List<PointInt2D> calculate(Cell[][] grid, int startX, int startY, int endX, int endY) {

		Cell current;		

		//add the start location to open list.
		open.add(grid[startY][startX]);

		List<PointInt2D> path = new ArrayList<>();

		while (true) {
			current = open.poll();
			if (current == null) {
				break;
			}

			closed[current.i][current.j] = true;

			if (current.equals(grid[endY][endX])) {
				break;
			}

			Cell t;  
			if(current.i-1>=0){
				t = grid[current.i-1][current.j];
				checkAndUpdateCost(map, current, t, current.finalCost+V_H_COST); 

				if(current.j-1>=0){                      
					t = grid[current.i-1][current.j-1];
					checkAndUpdateCost(map, current, t, current.finalCost+DIAGONAL_COST); 
				}

				if(current.j+1<grid[0].length){
					t = grid[current.i-1][current.j+1];
					checkAndUpdateCost(map, current, t, current.finalCost+DIAGONAL_COST); 
				}
			} 

			if(current.j-1>=0){
				t = grid[current.i][current.j-1];
				checkAndUpdateCost(map, current, t, current.finalCost+V_H_COST); 
			}

			if(current.j+1<grid[0].length){
				t = grid[current.i][current.j+1];
				checkAndUpdateCost(map, current, t, current.finalCost+V_H_COST); 
			}

			if(current.i+1<grid.length){
				t = grid[current.i+1][current.j];
				checkAndUpdateCost(map, current, t, current.finalCost+V_H_COST); 

				if(current.j-1>=0){
					t = grid[current.i+1][current.j-1];
					checkAndUpdateCost(map, current, t, current.finalCost+DIAGONAL_COST); 
				}

				if(current.j+1<grid[0].length){
					t = grid[current.i+1][current.j+1];
					checkAndUpdateCost(map, current, t, current.finalCost+DIAGONAL_COST); 
				}  
			}
		}

		if (closed[endY][endX]) {
			//Trace back the path 
			Cell cell = grid[endY][endX];
			path.add(new PointInt2D(cell.j, cell.i));

			while(cell.parent!=null){
				path.add(new PointInt2D(cell.parent.j, cell.parent.i));
				cell = cell.parent;
			}

			Collections.reverse(path);
		}

		return path;
	}

	private Cell[][] initGrid(int startX, int startY, int endX, int endY) {
		Cell[][] grid = new Cell[rows][columns];
				
		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				grid[j][i] = new Cell(j, i);
				grid[j][i].heuristicCost = Math.abs(j-endY)+Math.abs(i-endX);
			}
		}
		
		if (startX < 0 || startX > grid[0].length || startY < 0) {
			return grid;
		}

		grid[startY][startX].finalCost = 0;
		return grid;
	}

	public void checkAndUpdateCost(Map map, Cell current, Cell t, int cost) {
		if(isBlock(t) || closed[t.i][t.j])
			return;

		int t_final_cost = t.heuristicCost+cost;

		boolean inOpen = open.contains(t);
		if(!inOpen || t_final_cost<t.finalCost){
			t.finalCost = t_final_cost;
			t.parent = current;
			if(!inOpen)open.add(t);
		}
	}

	private boolean isBlock(Cell t) {
		return map.isBlock(map.getTiles()[t.i][t.j]);
	}

	@Override
	public int compare(Cell c1, Cell c2) {
		return c1.finalCost < c2.finalCost ? -1:
			c1.finalCost > c2.finalCost ? 1 : 0;
	}

}
