package br.com.vite.pathfind;

import java.util.List;
import java.util.PriorityQueue;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.map.Map;

public abstract class PathFinder {

	protected Map map;
	protected int columns;
	protected int rows;
		
	protected PriorityQueue<Cell> open;
	protected boolean closed[][];
	
	public PathFinder(Map map) {
		super();
		
		this.columns = map.getColumns();
		this.rows = map.getRows();
		this.map = map;
	}
	
	public abstract void init();
	public abstract void checkAndUpdateCost(Map map, Cell current, Cell t, int cost);
	public abstract List<PointInt2D> calculate(int startX, int startY, int endX, int endY);
	public abstract PathContainer calculatePath(int startX, int startY, int endX, int endY);
	
}
