package br.com.vite.pathfind;

import java.util.List;

import br.com.etyllica.core.linear.PointInt2D;

public class PathContainer {

	List<PointInt2D> path;
	Cell[][] grid;
	
	public PathContainer(List<PointInt2D> path, Cell[][] grid) {
		super();
		this.path = path;
		this.grid = grid;
	}	

	public List<PointInt2D> getPath() {
		return path;
	}
	
	public void setPath(List<PointInt2D> path) {
		this.path = path;
	}
	
	public Cell[][] getGrid() {
		return grid;
	}
	
	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
		
}
