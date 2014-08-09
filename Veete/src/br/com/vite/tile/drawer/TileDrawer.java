package br.com.vite.tile.drawer;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileDrawer extends TileHelper {
	
	protected boolean drawGrid = true;

	public TileDrawer(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
		// TODO Auto-generated constructor stub
	}
	
	public void drawTile(Tile tile, Graphic g) {
		
		drawFloor(tile, g);

		if(drawGrid)
			drawGrid(tile, g);
		
		drawObject(tile, g);
		
	}
	
	protected abstract void drawFloor(Tile tile, Graphic g);
	
	protected abstract void drawGrid(Tile tile, Graphic g);
	
	protected abstract void drawObject(Tile tile, Graphic g);

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
		
}
