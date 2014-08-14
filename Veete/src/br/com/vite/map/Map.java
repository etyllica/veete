package br.com.vite.map;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.colider.TileColider;
import br.com.vite.tile.drawer.TileDrawer;
import br.com.vite.tile.filler.TileFiller;
import br.com.vite.tile.generator.TileCreator;

public class Map {
	
	private int lines;
	
	private int columns;
	
	protected int offsetX;
	
	protected int offsetY;
	
	private Tile[][] tiles;
	
	//Helpers
	protected TileCreator creator;
	
	protected TileColider colider;
	
	protected TileDrawer drawer;
	
	protected TileFiller filler;
			
	public Map(int lines, int columns) {
		super();
		
		this.lines = lines;
		
		this.columns = columns;
	}
		
	public Tile[][] createTiles() {
				
		tiles = new Tile[lines][columns]; 
		
		for(int j = 0; j < lines; j++) {

			for(int i = 0; i < columns; i++) {
		
				tiles[j][i] = creator.createTile(j, i);
			}
		}
		
		return tiles;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
	public void setOffset(int offsetX, int offsetY) {
		this.setOffsetX(offsetX);
		this.setOffsetY(offsetY);
	}
	
	public void draw(Graphic g, int x, int y) {
		
		for(int j=y;j<lines;j++) {

			for(int i=x;i<columns;i++) {

				Tile tile = tiles[j][i];

				drawer.drawTile(tile, g, offsetX, offsetY);
			}
		}
	}
	
	public TileCreator getCreator() {
		return creator;
	}

	public void setCreator(TileCreator creator) {
		this.creator = creator;
	}

	public TileColider getColider() {
		return colider;
	}

	public void setColider(TileColider colider) {
		this.colider = colider;
	}

	public TileDrawer getDrawer() {
		return drawer;
	}

	public void setDrawer(TileDrawer drawer) {
		this.drawer = drawer;
	}

	public TileFiller getFiller() {
		return filler;
	}

	public void setFiller(TileFiller filler) {
		this.filler = filler;
	}
	
}
