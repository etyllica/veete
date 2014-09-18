package br.com.vite.map;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.colider.TileColider;
import br.com.vite.tile.drawer.TileDrawer;
import br.com.vite.tile.filler.TileFiller;
import br.com.vite.tile.generator.TileCreator;

public abstract class Map {

	protected int lines;

	protected int columns;

	protected int offsetX;

	protected int offsetY;

	protected int tileWidth;

	protected int tileHeight;

	protected Tile[][] tiles;

	//Helpers
	protected TileCreator creator;

	protected TileColider colider;

	protected TileDrawer drawer;

	protected TileFiller filler;

	public Map(int columns, int lines) {
		super();

		this.lines = lines;

		this.columns = columns;
	}

	public Map(int columns, int lines, int tileWidth, int tileHeight) {
		super();

		this.lines = lines;

		this.columns = columns;

		this.tileWidth = tileWidth;

		this.tileHeight = tileHeight;
	}

	public void createTiles() {

		tiles = new Tile[lines][columns]; 

		for(int j = 0; j < lines; j++) {

			for(int i = 0; i < columns; i++) {

				tiles[j][i] = creator.createTile(j, i);
			}
		}
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
	
	public void draw(Graphic g) {
		draw(g, 0, 0);
	}
	
	public void draw(Graphic g, int x, int y) {
		draw(g, x, y, columns, lines);
	}

	private void draw(Graphic g, int x, int y, int w, int h) {

		for(int j = y; j < h; j++) {

			for(int i = x; i < w; i++) {
				
				Tile tile = tiles[j][i];

				drawer.drawTile(tile, g, offsetX, offsetY);
			}
		}
	}
	
	public void drawFiller(Graphic g, Tile lastSelectedTile) {
		g.setAlpha(50);
		filler.drawFiller(lastSelectedTile, g, offsetX, offsetY);
		g.setAlpha(100);
	}

	public int getLines() {
		return lines;
	}

	public int getColumns() {
		return columns;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}
	
}
